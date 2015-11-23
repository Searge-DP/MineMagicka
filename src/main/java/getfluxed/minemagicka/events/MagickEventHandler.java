package getfluxed.minemagicka.events;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.handlers.SpellHandler;
import getfluxed.minemagicka.items.MMItems;
import getfluxed.minemagicka.network.PacketHandler;
import getfluxed.minemagicka.network.messages.MessageSelectElement;
import getfluxed.minemagicka.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

public class MagickEventHandler {
	public int selectedElement = 0;
	public int currentElement = 0;

	public MagickEventHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}

	@SubscribeEvent
	public void renderGUI(RenderGameOverlayEvent.Text e) {
		if ((e.type == RenderGameOverlayEvent.ElementType.TEXT)) {
			EntityPlayer player = MineMagicka.proxy.getPlayer();
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().isItemEqual(new ItemStack(MMItems.staff))) {

				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4d(1, 1, 1, 1);
//				GL11.glBlendFunc(768, 771);
				GL11.glPushMatrix();
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.modid, "textures/gui/guiElements.png"));
				int y = 0;
				for (int i = 0; i < ElementRegistry.getElements().size(); i++) {

					Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((i % 4) * 24, y, 0, 0, 24, 24);
					if (i % 4 == 3) {
						y += 24;
					}
				}
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				int selEmX = ((selectedElement % 4) * 24);
				int selEmY = 0;
				if (selectedElement > 3) {
					selEmY = 24;
				} else {
					selEmY = 0;
				}
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.modid, "textures/gui/guiElements.png"));
				Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(selEmX, selEmY, 0, 24, 24, 24);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				int[] xCoords = new int[] { 0, 24, 48, 72 };
				int xCount = 0;
				int elY = 0;
				for (int i = 0; i < ElementRegistry.getElements().size(); i++) {
					IElement el = ElementRegistry.getElements().get(i);
					el.render(Minecraft.getMinecraft().ingameGUI, xCoords[xCount++], elY);
					if (xCount > 3) {
						xCount = 0;
						elY = 24;
					}
				}
				for (int i = 0; i < SpellHandler.currentElements.size(); i++) {
					IElement el = SpellHandler.currentElements.get(i);
					el.render(Minecraft.getMinecraft().ingameGUI, xCoords[xCount++], 24 + elY);
					if (xCount > 3) {
						xCount = 0;
						elY += 24;
					}
				}

				GL11.glPopMatrix();
				GL11.glDisable(GL11.GL_BLEND);
			}
		}
	}

	@SubscribeEvent
	public void mouse(MouseEvent e) {
		boolean cancel = false;
		if (Keyboard.isCreated()) {
			if (MineMagicka.proxy.getPlayer().getCurrentEquippedItem() != null && MineMagicka.proxy.getPlayer().getCurrentEquippedItem().isItemEqual(new ItemStack(MMItems.staff)) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				if (e.dwheel != 0) {
					cancel = true;
					if (e.dwheel > 0 && selectedElement > 0) {
						selectedElement--;
					} else if (e.dwheel < 0 && selectedElement < 7) {
						selectedElement++;
					}
					PacketHandler.INSTANCE.sendTo(new MessageSelectElement(MineMagicka.proxy.getPlayer(), selectedElement), (EntityPlayerMP) MineMagicka.proxy.getPlayer());
					
				}
				if (e.button == 0 && e.buttonstate) {
					SpellHandler.addElement(ElementRegistry.getElements().get(selectedElement));
				} else if (e.button == 1 && e.buttonstate) {
					SpellHandler.currentElements.clear();
				}
				cancel = true;
			}
		}
		e.setCanceled(cancel);
	}
}

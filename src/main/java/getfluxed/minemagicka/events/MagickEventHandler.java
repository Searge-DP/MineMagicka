package getfluxed.minemagicka.events;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.blocks.MMBlocks;
import getfluxed.minemagicka.handlers.SpellHandler;
import getfluxed.minemagicka.items.ItemStaff;
import getfluxed.minemagicka.items.MMItems;
import getfluxed.minemagicka.network.PacketHandler;
import getfluxed.minemagicka.network.messages.MessageSelectElement;
import getfluxed.minemagicka.network.messages.spells.MessageAddElement;
import getfluxed.minemagicka.network.messages.spells.MessageCastSpell;
import getfluxed.minemagicka.network.messages.spells.MessageClearElements;
import getfluxed.minemagicka.reference.Reference;
import getfluxed.minemagicka.util.SpellHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MagickEventHandler {

    public MagickEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @EventHandler
    public void toolTip(ItemTooltipEvent e) {
        if (e.itemStack.getTagCompound().getInteger("MMItemTime") > 0) {
            e.toolTip.add("mm.item.countTime" + e.itemStack.getTagCompound().getInteger("MMItemTime"));
        }
    }

    @SubscribeEvent
    public void renderGUI(RenderGameOverlayEvent.Text e) {
        if ((e.type == RenderGameOverlayEvent.ElementType.TEXT)) {
            EntityPlayer player = MineMagicka.proxy.getPlayer();
            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().isItemEqual(new ItemStack(MMItems.staff))) {
                ItemStack staffStack = MineMagicka.proxy.getPlayer().getCurrentEquippedItem();
                ItemStaff staff = (ItemStaff) staffStack.getItem();
                int selectedElement = staff.getSelectedElement(staffStack);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glColor4d(1, 1, 1, 1);
                // GL11.glBlendFunc(768, 771);
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
                ISpell spell = SpellRegistry.getSpellFromElements(SpellHandler.getElements(staffStack));
                if (spell != null) {
                    Minecraft.getMinecraft().ingameGUI.drawString(Minecraft.getMinecraft().fontRendererObj, spell.getName(), 6, elY + 28, 0xFFFFFF);
                }
                // System.out.println(SpellHandler.getElements(staffStack));
//                System.out.println(SpellHandler.getElements(staffStack).getElements().length);
//                System.out.println(SpellHandler.getElements(staffStack).size());

                if (SpellHandler.getElements(staffStack).size() > 0) {
                    for (IElement el : SpellHandler.getElements(staffStack).getElements()) {
                        el.render(Minecraft.getMinecraft().ingameGUI, xCoords[xCount++], 36 + elY);
                        if (xCount > 3) {
                            xCount = 0;
                            elY += 24;
                        }
                    }
                    for (IElement el : SpellHandler.getElements(staffStack).getElements()) {
                        el.render(Minecraft.getMinecraft().ingameGUI, xCoords[xCount++], 36 + elY);
                        if (xCount > 3) {
                            xCount = 0;
                            elY += 24;
                        }
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
                ItemStack staffStack = MineMagicka.proxy.getPlayer().getCurrentEquippedItem();
                ItemStaff staff = (ItemStaff) staffStack.getItem();
                int selectedElement = staff.getSelectedElement(staffStack);
                if (e.dwheel != 0) {
                    cancel = true;
                    if (e.dwheel > 0 && selectedElement > 0) {
                        selectedElement--;
                    } else if (e.dwheel < 0 && selectedElement < 7) {
                        selectedElement++;
                    }
                    PacketHandler.INSTANCE.sendToServer(new MessageSelectElement(selectedElement));
                }
                if (e.button == 0 && e.buttonstate) {
                    System.out.println(ElementRegistry.getElements().get(selectedElement));
                    SpellHandler.addElement(staffStack, ElementRegistry.getElements().get(selectedElement));
                    PacketHandler.INSTANCE.sendToServer(new MessageAddElement(ElementRegistry.getElements().get(selectedElement)));
                    System.out.println(SpellHandler.getElements(staffStack).size());
                } else if (e.button == 1 && e.buttonstate) {
                    SpellHandler.clearElements(staffStack);
                    PacketHandler.INSTANCE.sendToServer(new MessageClearElements());
                }
                cancel = true;
            }
            if (MineMagicka.proxy.getPlayer().getCurrentEquippedItem() != null && MineMagicka.proxy.getPlayer().getCurrentEquippedItem().isItemEqual(new ItemStack(MMItems.staff)) && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && e.button == 1 && e.buttonstate) {
                ISpell spell = SpellRegistry.getSpellFromElements(SpellHandler.getElements(MineMagicka.proxy.getPlayer().getCurrentEquippedItem()));
                if (spell != null) {
                    PacketHandler.INSTANCE.sendToServer(new MessageCastSpell(spell, MineMagicka.proxy.getPlayer().posX, MineMagicka.proxy.getPlayer().posY, MineMagicka.proxy.getPlayer().posZ));
                }
            }
        }
        e.setCanceled(cancel);
    }

    @SubscribeEvent
    public void bucketFill(FillBucketEvent evt) {
        if (evt.current.getItem() == Items.bucket && evt.target.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos pos = evt.target.getBlockPos();

            if (evt.entityPlayer != null && !evt.entityPlayer.canPlayerEdit(pos, evt.target.sideHit, evt.current)) {
                return;
            }

            Block bID = evt.world.getBlockState(pos).getBlock();
            if (bID == MMBlocks.blockLiquidMagick) {
                if (evt.entityPlayer.capabilities.isCreativeMode) {
                    evt.world.setBlockToAir(pos);
                } else {
                    evt.world.setBlockToAir(pos);

                    evt.setResult(Result.ALLOW);
                    evt.result = new ItemStack(MMItems.bucketMagickLiquid);
                }
            }
        }
    }
}

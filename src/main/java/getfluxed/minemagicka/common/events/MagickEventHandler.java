package getfluxed.minemagicka.common.events;

import getfluxed.minemagicka.client.render.radial.ElementRadial;
import getfluxed.minemagicka.client.render.radial.RadialGUIHandler;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.events.SelectElementEvent;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.blocks.MMBlocks;
import getfluxed.minemagicka.common.handlers.SpellHandler;
import getfluxed.minemagicka.common.items.ItemStaff;
import getfluxed.minemagicka.common.items.MMItems;
import getfluxed.minemagicka.common.network.PacketHandler;
import getfluxed.minemagicka.common.network.messages.MessageSelectElement;
import getfluxed.minemagicka.common.network.messages.spells.MessageAddElement;
import getfluxed.minemagicka.common.network.messages.spells.MessageCastSpell;
import getfluxed.minemagicka.common.reference.ElementReference;
import getfluxed.minemagicka.common.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class MagickEventHandler {

    public MagickEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
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
            GlStateManager.pushAttrib();
            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().isItemEqual(new ItemStack(MMItems.staff))) {
                if (!RadialGUIHandler.getGuiState() && player.isUsingItem())
                    RadialGUIHandler.enableGui(new ElementRadial());
                else if (!player.isUsingItem())
                    RadialGUIHandler.disableGui();

                ItemStack staffStack = MineMagicka.proxy.getPlayer().getCurrentEquippedItem();
                ItemStaff staff = (ItemStaff) staffStack.getItem();
                int selectedElement = staff.getSelectedElement(staffStack);

                GL11.glEnable(GL11.GL_BLEND);
                GL11.glColor4d(1, 1, 1, 1);
                GL11.glBlendFunc(770, 771);
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
                }
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.modid, "textures/gui/guiElements.png"));
                Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(selEmX, selEmY, 0, 24, 24, 24);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                int[] xCoords = new int[] { 0, 24, 48, 72 };
                int xCount = 0;
                int elY = 0;
                // Renders the spell bar
                for (IElement el : ElementRegistry.getElements().values()) {
                    el.render(Minecraft.getMinecraft().ingameGUI, xCoords[xCount++], elY, 24, 24, null);
                    if (xCount > 3) {
                        xCount = 0;
                        elY = 24;
                    }
                }
                // Renders the spell name
                ISpell spell = SpellRegistry.getSpellFromElements(SpellHandler.getElements(staffStack));
                if (spell != null) {
                    Minecraft.getMinecraft().ingameGUI.drawString(Minecraft.getMinecraft().fontRendererObj, spell.getName(), 6, elY + 28, 0xFFFFFF);
                }
                // renders the currently selected elements
                if (SpellHandler.getElements(staffStack).getElements().length > 0) {
                    if (Minecraft.getMinecraft().ingameGUI != null) {
                        for (IElement el : SpellHandler.getElements(staffStack).getElements()) {
                            for (int i = 0; i < SpellHandler.getElements(staffStack).getAmount(el); i++) {
                                if (el != null)
                                    el.render(Minecraft.getMinecraft().ingameGUI, xCoords[xCount++], 36 + elY, 24, 24, null);
                                if (xCount > 3) {
                                    xCount = 0;
                                    elY += 24;
                                }
                            }
                        }
                    }
                    if (SpellHandler.getElements(staffStack).getModifierElements().length > 0)
                        if (Minecraft.getMinecraft().ingameGUI != null) {
                            for (IElement el : SpellHandler.getElements(staffStack).getModifierElements()) {
                                for (int i = 0; i < SpellHandler.getElements(staffStack).getModifierAmount(el); i++) {
                                    if (el != null)
                                        el.render(Minecraft.getMinecraft().ingameGUI, xCoords[xCount++], 36 + elY, 24, 24, Color.black);
                                    if (xCount > 3) {
                                        xCount = 0;
                                        elY += 24;
                                    }
                                }
                            }
                        }
                }
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();

            }
            GlStateManager.popAttrib();
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
                    e.setCanceled(true);
                    if (e.dwheel > 0 && selectedElement > 0) {
                        selectedElement--;
                    } else if (e.dwheel < 0 && selectedElement < 7) {
                        selectedElement++;
                    }
                    PacketHandler.INSTANCE.sendToServer(new MessageSelectElement(selectedElement));
                }
                if (e.button == 0 && e.buttonstate) {
                    SpellHandler.addElement(staffStack, ElementRegistry.getElementList()[selectedElement]);
                    PacketHandler.INSTANCE.sendToServer(new MessageAddElement(ElementRegistry.getElementList()[selectedElement], false));
                } else if (e.button == 1 && e.buttonstate) {
                    SpellHandler.addModifierElement(staffStack, ElementRegistry.getElementList()[selectedElement]);
                    PacketHandler.INSTANCE.sendToServer(new MessageAddElement(ElementRegistry.getElementList()[selectedElement], true));
                    // SpellHandler.clearElements(staffStack);
                    // PacketHandler.INSTANCE.sendToServer(new
                    // MessageClearElements());
                }
                cancel = true;
            }
            if (MineMagicka.proxy.getPlayer().getCurrentEquippedItem() != null && MineMagicka.proxy.getPlayer().getCurrentEquippedItem().isItemEqual(new ItemStack(MMItems.staff)) && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && e.button == 1 && e.buttonstate) {
                ISpell spell = SpellRegistry.getSpellFromElements(SpellHandler.getElements(MineMagicka.proxy.getPlayer().getCurrentEquippedItem()));
                if (spell != null) {
                    PacketHandler.INSTANCE.sendToServer(new MessageCastSpell(spell, MineMagicka.proxy.getPlayer().posX, MineMagicka.proxy.getPlayer().posY, MineMagicka.proxy.getPlayer().posZ, SpellHandler.getElements(MineMagicka.proxy.getPlayer().getCurrentEquippedItem())));
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
                if (evt.entityPlayer != null && evt.entityPlayer.capabilities.isCreativeMode) {
                    evt.world.setBlockToAir(pos);
                } else {
                    evt.world.setBlockToAir(pos);

                    evt.setResult(Result.ALLOW);
                    evt.result = new ItemStack(MMItems.bucketMagickLiquid);
                }
            }
        }
    }

    @SubscribeEvent
    public void cast(SelectElementEvent e) {
        if (e.element.equals(ElementReference.arcane)) {
            System.out.println("goodbye");
        }
    }
}

package getfluxed.minemagicka.common.events;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.spells.ICasterItem;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.handlers.SpellHandler;
import getfluxed.minemagicka.common.items.ItemStaff;
import getfluxed.minemagicka.common.items.MMItems;
import getfluxed.minemagicka.common.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.util.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author WireSegal
 *         Created at 2:33 PM on 2/15/16.
 */
public class StaffEventHandler {
    public StaffEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void toolTip(ItemTooltipEvent e) {
        if (e.itemStack.hasTagCompound() && e.itemStack.getTagCompound().getInteger("MMItemTime") > 0) {
            e.toolTip.add("mm.item.countTime" + e.itemStack.getTagCompound().getInteger("MMItemTime"));
        }
    }

    Pattern casting = Pattern.compile("([a-z]+\\s+)*([a-z]+)(,\\s+([a-z]+\\s+)*([a-z]+))?!");
    Pattern empty = Pattern.compile("\\s*");

    @SubscribeEvent
    public void onChat(ServerChatEvent e) {
        String msg = e.message.trim().toLowerCase();
        EntityPlayer player = e.player;
        ItemStack held = player.getCurrentEquippedItem();

        if (held != null && held.getItem() instanceof ICasterItem) {
            if (casting.matcher(msg).matches()) {
                if (((ICasterItem) held.getItem()).isActive(held, player)) {
                    e.setCanceled(true);

                    String trimmed = msg.substring(0, msg.length() - 1);
                    String[] split = trimmed.split(",");
                    String[] elements = split[0].split(" ");
                    String[] modifiers = new String[]{};
                    if (split.length > 1) {
                        modifiers = split[1].split(" ");
                    }
                    ElementCompound comp = new ElementCompound();
                    for (String el : elements) {
                        if (empty.matcher(el).matches()) continue;
                        IElement element = ElementRegistry.getElementFromName(el);
                        if (element != null) {
                            comp.add(element, 1);
                        } else {
                            IChatComponent elComp = new ChatComponentText(el);
                            elComp.getChatStyle().setItalic(true);
                            player.addChatComponentMessage(new ChatComponentTranslation("mm.spellcasting.failed.badelement", elComp));
                            return;
                        }
                    }
                    for (String el : modifiers) {
                        if (empty.matcher(el).matches()) continue;
                        IElement element = ElementRegistry.getElementFromName(el);
                        if (element != null) {
                            comp.addModifier(element, 1);
                        } else {
                            IChatComponent elComp = new ChatComponentText(el);
                            elComp.getChatStyle().setItalic(true);
                            player.addChatComponentMessage(new ChatComponentTranslation("mm.spellcasting.failed.badelement", elComp));
                            return;
                        }
                    }

                    ISpell spell = SpellRegistry.getSpellFromElements(comp);

                    if (spell != null) {
                        if (((ICasterItem) held.getItem()).canCast(held, player, comp)) {
                            ((ICasterItem) held.getItem()).onCast(held, player, comp);
                            spell.cast(player.worldObj, player, comp, player.posX, player.posY, player.posZ);

                            IChatComponent greenComp = new ChatComponentTranslation("mm.spellcasting.success");
                            greenComp.getChatStyle().setColor(EnumChatFormatting.GREEN);
                            S45PacketTitle titlePacket = new S45PacketTitle(S45PacketTitle.Type.TITLE, greenComp, 10, 60, 20);
                            ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(titlePacket);
                            titlePacket = new S45PacketTitle(S45PacketTitle.Type.SUBTITLE, new ChatComponentText(spell.getName()+"!"), 10, 60, 20);
                            ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(titlePacket);
                        } else {
                            IChatComponent nameComp = new ChatComponentText(spell.getName());
                            nameComp.getChatStyle().setItalic(true);
                            player.addChatComponentMessage(new ChatComponentTranslation("mm.spellcasting.failed.channelfailed", nameComp, held.getChatComponent()));
                        }
                    }
                } else {
                    player.addChatComponentMessage(new ChatComponentTranslation("mm.spellcasting.failed.sleepingstaff", held.getChatComponent()));
                }
            }
        }
    }
}

package getfluxed.minemagicka.common.events;

import getfluxed.minemagicka.api.spells.IMultiCasterItem;
import getfluxed.minemagicka.api.spells.ISingleCasterItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Pattern;

/**
 * @author WireSegal
 *         Created at 2:33 PM on 2/15/16.
 */
public class SingleCasterEventHandler {
    Pattern casting = Pattern.compile(".+!");

    public SingleCasterEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChat(ServerChatEvent e) {
        String msg = e.message.trim().toLowerCase();
        EntityPlayer player = e.player;

        if (casting.matcher(msg).matches()) {
            msg = msg.substring(0, msg.length() - 1);

            IInventory mainInv = player.inventory;

            int invSize = mainInv.getSizeInventory();

            for (int i = 0; i < invSize; i++) {
                ItemStack stackInSlot = mainInv.getStackInSlot(i);

                if (stackInSlot != null && stackInSlot.getItem() instanceof ISingleCasterItem) {
                    castWithStack(e, stackInSlot, player, msg);
                } else if (stackInSlot != null && stackInSlot.getItem() instanceof IMultiCasterItem) {
                    castWithStack(e, ((IMultiCasterItem) stackInSlot.getItem()).getCasterStack(stackInSlot, player, msg), player, msg);
                }
            }
        }
    }

    public static void castWithStack(ServerChatEvent e, ItemStack stack, EntityPlayer player, String msg) {
        if (stack == null || !(stack.getItem() instanceof ISingleCasterItem)) return;

        if (((ISingleCasterItem) stack.getItem()).canCast(stack, player, msg)) {
            e.setCanceled(true);
            if (((ISingleCasterItem) stack.getItem()).isActive(stack, player)) {
                StaffEventHandler.cast(((ISingleCasterItem) stack.getItem()).getElements(stack, player), stack, player);
            } else {
                player.addChatComponentMessage(new ChatComponentTranslation("mm.spellcasting.failed.sleepingstaff", stack.getChatComponent()));
            }
        }
    }
}

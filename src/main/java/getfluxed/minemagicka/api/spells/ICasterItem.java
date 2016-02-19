package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.elements.ElementCompound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author WireSegal
 *         Created at 2:41 PM on 2/15/16.
 */
public interface ICasterItem {
    boolean isActive(ItemStack stack, EntityPlayer player);

    boolean canCast(ItemStack stack, EntityPlayer player, ElementCompound comp);

    void onCast(ItemStack stack, EntityPlayer player, ElementCompound comp);

    int getPurity(ItemStack stack, EntityPlayer player);
}

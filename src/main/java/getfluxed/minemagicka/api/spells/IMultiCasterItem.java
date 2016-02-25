package getfluxed.minemagicka.api.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author WireSegal
 *         Created at 1:01 PM on 2/24/16.
 */
public interface IMultiCasterItem extends ICasterItem {

    boolean canCast(ItemStack stack, EntityPlayer player, String phrase);

    ItemStack getCasterStack(ItemStack stack, EntityPlayer player, String phrase);

}

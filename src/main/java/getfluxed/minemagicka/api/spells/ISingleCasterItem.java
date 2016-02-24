package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.elements.ElementCompound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author WireSegal
 *         Created at 12:56 PM on 2/24/16.
 */
public interface ISingleCasterItem extends ICasterItem {

    ElementCompound getElements(ItemStack stack, EntityPlayer player);

    boolean canCast(ItemStack stack, EntityPlayer player, String phrase);

    @Override
    default boolean canCast(ItemStack stack, EntityPlayer player, ElementCompound comp) {
        ElementCompound elements = getElements(stack, player);
        return comp.getElementList().equals(elements.getElementList()) &&
               comp.getModifierElementList().equals(elements.getModifierElementList());
    }
}

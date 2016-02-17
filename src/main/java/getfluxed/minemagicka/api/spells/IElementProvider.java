package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.elements.ElementList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author WireSegal
 *         Created at 3:28 PM on 2/17/16.
 */
public interface IElementProvider {
    ElementList getElements(EntityPlayer player, ItemStack stack);

    ItemStack consumeElements(EntityPlayer player, ItemStack stack, ElementList list, boolean doit);
}

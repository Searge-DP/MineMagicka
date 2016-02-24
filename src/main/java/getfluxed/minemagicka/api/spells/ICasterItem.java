package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.elements.ElementCompound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author WireSegal
 *         Created at 2:41 PM on 2/15/16.
 */
public interface ICasterItem {
    boolean isActive(ItemStack stack, EntityPlayer player);

    default boolean canCast(ItemStack stack, EntityPlayer player, ElementCompound comp) {
        ISpell spell = SpellRegistry.getSpellFromElements(comp);
        int purityRequired = 0;
        if (spell != null) {
            purityRequired = spell.getPurity();
        }
        return (getPurity(stack, player) == -1 || purityRequired < getPurity(stack, player)) &&
                ElementProviderHelper.requestElements(player, comp, purityRequired, false);
    }

    default void onCast(ItemStack stack, EntityPlayer player, ElementCompound comp) {
        ISpell spell = SpellRegistry.getSpellFromElements(comp);
        int purityRequired = 0;
        if (spell != null) {
            purityRequired = spell.getPurity();
        }
        if (getPurity(stack, player) != -1 && purityRequired > getPurity(stack, player)) return;
        ElementProviderHelper.requestElements(player, comp, purityRequired, true);
    }

    int getPurity(ItemStack stack, EntityPlayer player);
}

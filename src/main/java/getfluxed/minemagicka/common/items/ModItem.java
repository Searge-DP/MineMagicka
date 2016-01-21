package getfluxed.minemagicka.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author WireSegal
 *         Created at 2:10 PM on 1/18/16.
 */
public class ModItem extends Item {
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack).replaceAll("item\\.", "item.mm.");
    }
}

package getfluxed.minemagicka.common.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * @author WireSegal
 *         Created at 2:15 PM on 1/18/16.
 */
public class ItemBlockMod extends ItemBlock {

    public ItemBlockMod(Block block) {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack).replaceAll("tile\\.", "tile.mm.");
    }
}

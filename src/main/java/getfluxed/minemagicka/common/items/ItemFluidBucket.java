package getfluxed.minemagicka.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

public class ItemFluidBucket extends ItemBucket {

    public Block set;

    public ItemFluidBucket(Block set) {
        super(set);
        this.set = set;

    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack).replaceAll("item\\.", "item.mm.");
    }
}

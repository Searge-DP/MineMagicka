package getfluxed.minemagicka.items;

import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidBase;

public class ItemFluidBucket extends ItemBucket {

    public BlockFluidBase set;

    public ItemFluidBucket(BlockFluidBase set) {
        super(set);
        this.set = set;

    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack).replaceAll("item\\.", "item.mm.");
    }
}

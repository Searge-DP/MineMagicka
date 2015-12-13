package getfluxed.minemagicka.items;

import net.minecraft.item.ItemBucket;
import net.minecraftforge.fluids.BlockFluidBase;

public class ItemFluidBucket extends ItemBucket {

	public BlockFluidBase set;

	public ItemFluidBucket(BlockFluidBase set) {
		super(set);
		this.set = set;
	}

}

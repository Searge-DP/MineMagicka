package getfluxed.minemagicka.items;

import getfluxed.minemagicka.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.fluids.BlockFluidBase;

public class ItemFluidBucket extends ItemBucket {

	public BlockFluidBase set;

	public ItemFluidBucket(BlockFluidBase set) {
		super(set);
		this.set = set;
	}

	@Override
	public void registerIcons(IIconRegister icon) {
		super.registerIcons(icon);
		this.itemIcon = icon.registerIcon(Reference.modid + ":bucketmagickliquid");
	}

}

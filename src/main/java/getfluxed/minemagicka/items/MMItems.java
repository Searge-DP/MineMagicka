package getfluxed.minemagicka.items;

import cpw.mods.fml.common.registry.GameRegistry;
import getfluxed.minemagicka.blocks.MMBlocks;
import getfluxed.minemagicka.reference.Reference;
import getfluxed.minemagicka.util.CreativeTabMM;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.fluids.BlockFluidBase;

public class MMItems {
	public static CreativeTabs tab = new CreativeTabMM();;

	public static Item staff = new ItemStaff();
	public static Item bucketMagickLiquid = new ItemFluidBucket((BlockFluidBase) MMBlocks.blockLiquidMagick);
	public static Item ingotEmbrane = new ItemIngot();

	public static void preInit() {
		registerItem(bucketMagickLiquid, "bucketMagickLiquid", "bucketmagickliquid", "bucketmagickliquid");
		registerItem(ingotEmbrane, "embraneIngot", "embrane_ingot");
		registerItemNoTexture(staff, "staff", "staff");
	}

	public static void registerItem(Item item, String name, String key) {
		item.setUnlocalizedName(key).setTextureName(Reference.modid + ":" + key).setCreativeTab(tab);
		GameRegistry.registerItem(item, name);
	}

	public static void registerItem(Item item, String name, String key, String texture) {
		item.setUnlocalizedName(key).setTextureName(Reference.modid + ":" + texture).setCreativeTab(tab);
		GameRegistry.registerItem(item, name);
	}

	public static void registerItemNoTexture(Item item, String name, String key) {
		item.setUnlocalizedName(key).setCreativeTab(tab);
		GameRegistry.registerItem(item, name);
	}
}

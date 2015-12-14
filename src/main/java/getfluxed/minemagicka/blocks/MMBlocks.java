package getfluxed.minemagicka.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import getfluxed.minemagicka.items.MMItems;
import getfluxed.minemagicka.liquids.MMLiquids;
import getfluxed.minemagicka.reference.Reference;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

public class MMBlocks {
	public static Block blockLiquidMagick = new BlockLiquidMagick();
	public static Block magickInfuser = new BlockMagickInfuser();
	public static Block bricksEmbrane = new BlockEmbraneBricks();

	public static void preInit() {
		registerBlock(blockLiquidMagick, "blockLiquidMagick", "liquidMagick");
		registerBlock(magickInfuser, "magickInfuser", "magickInfuser", TileEntityMagickInfuser.class);
		registerBlock(bricksEmbrane, "embraneBricks", "bricksEmbrane", "bricks");
	}

	private static void registerBlock(Block block, String name, String key) {
		block.setBlockName(name).setBlockTextureName(Reference.modid + ":" + key).setCreativeTab(MMItems.tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerBlock(Block block, String name, String key, Class tile) {
		block.setBlockName(name).setBlockTextureName(Reference.modid + ":" + key).setCreativeTab(MMItems.tab);
		GameRegistry.registerBlock(block, key);
		GameRegistry.registerTileEntity(tile, key);
	}

	@SuppressWarnings("unused")
	private static void registerBlockNoTexture(Block block, String name, String key) {
		block.setBlockName(name).setCreativeTab(MMItems.tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerBlock(Block block, String name, String key, String texture) {
		block.setBlockName(name).setBlockTextureName(Reference.modid + ":" + texture).setCreativeTab(MMItems.tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerBlock(Block block, String name, String key, String texture, CreativeTabs tab) {
		block.setBlockName(name).setBlockTextureName(Reference.modid + ":" + texture).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}

	@SuppressWarnings("unused")
	private static void registerBlock(Block block, String name, String key, CreativeTabs tab) {
		block.setBlockName(name).setBlockTextureName(Reference.modid + ":" + key).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerItemBlockNoTexture(Block block, String name, String key, Class<? extends ItemBlock> clazz) {
		block.setBlockName(name).setCreativeTab(MMItems.tab);
		GameRegistry.registerBlock(block, clazz, key);
	}
}

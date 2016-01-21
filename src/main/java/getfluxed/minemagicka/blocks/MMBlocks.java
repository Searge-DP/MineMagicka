package getfluxed.minemagicka.blocks;

import getfluxed.minemagicka.blocks.misc.BlockMagickBlock;
import getfluxed.minemagicka.blocks.researchtable.BlockResearchTableBook;
import getfluxed.minemagicka.blocks.trees.magick.BlockMagicLog;
import getfluxed.minemagicka.blocks.trees.magick.BlockMagickLeaves;
import getfluxed.minemagicka.items.MMItems;
import getfluxed.minemagicka.items.blocks.ItemBlockMod;
import getfluxed.minemagicka.reference.Reference;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import getfluxed.minemagicka.tileentities.researchtable.TileEntityResearchTableBook;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MMBlocks {
    public static Block blockLiquidMagick = new BlockFluidMagick();
    public static Block magickInfuser = new BlockMagickInfuser();
    public static Block magickInfuserOn = new BlockMagickInfuser();

    public static Block bricksEmbrane = new BlockEmbraneBricks();
    public static Block embraneOre = new BlockEmbraneBricks();

    public static Block researchTableBook = new BlockResearchTableBook();

    public static Block logMagick = new BlockMagicLog();
    public static Block leavesMagick = new BlockMagickLeaves();

    public static Block magickBlock = new BlockMagickBlock();

    public static Map<String, Block> renderMap = new HashMap<>();
    public static Map<String, Block> renderFluidMap = new HashMap<>();

    public static void preInit() {
        registerBlockFluid(blockLiquidMagick, "liquidMagick");
        registerBlock(magickInfuser, "magickInfuser", TileEntityMagickInfuser.class);
        registerBlock(magickInfuserOn, "magickInfuserOn", TileEntityMagickInfuser.class, null);
        registerBlock(bricksEmbrane, "bricksEmbrane");
        registerBlock(embraneOre, "embrane_ore");
        registerBlock(researchTableBook, "researchTableBook", TileEntityResearchTableBook.class);
        registerBlock(logMagick, "magick_log");
        registerBlock(magickBlock, "magick_block");
        registerBlock(leavesMagick, "magick_leaves");


    }

    public static void init() {

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        for (Map.Entry<String, Block> ent : renderMap.entrySet()) {
            renderItem.getItemModelMesher().register(Item.getItemFromBlock(ent.getValue()), 0, new ModelResourceLocation(Reference.modid + ":" + ent.getKey(), "inventory"));
        }

        for (Entry<String, Block> ent : renderFluidMap.entrySet()) {
            final Block toRender = ent.getValue();
            ModelBakery.registerItemVariants(Item.getItemFromBlock(toRender));
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(toRender), stack -> new ModelResourceLocation(Reference.modid + ":" + toRender.getClass().getSimpleName(), "fluid"));
            ModelLoader.setCustomStateMapper(toRender, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(Reference.modid + ":" + toRender.getClass().getSimpleName(), "fluid");
                }
            });
        }
    }

    private static void registerBlockFluid(Block block, String key) {
        block.setUnlocalizedName(key).setCreativeTab(MMItems.tab);
        renderFluidMap.put(key, block);
        GameRegistry.registerBlock(block, ItemBlockMod.class, key);
    }

    private static void registerBlock(Block block, String key) {
        block.setUnlocalizedName(key).setCreativeTab(MMItems.tab);
        renderMap.put(key, block);
        GameRegistry.registerBlock(block, ItemBlockMod.class, key);
    }

    private static void registerBlock(Block block, String key, Class tile) {
        registerBlock(block, key);
        GameRegistry.registerTileEntity(tile, key);
    }

    private static void registerBlock(Block block, String key, Class tile, CreativeTabs tab) {
        block.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, block);
        GameRegistry.registerBlock(block, ItemBlockMod.class, key);
        GameRegistry.registerTileEntity(tile, key);
    }

}

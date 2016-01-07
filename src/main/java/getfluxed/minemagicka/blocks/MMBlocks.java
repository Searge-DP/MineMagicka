package getfluxed.minemagicka.blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import getfluxed.minemagicka.blocks.researchtable.BlockResearchTableBook;
import getfluxed.minemagicka.items.MMItems;
import getfluxed.minemagicka.reference.Reference;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import getfluxed.minemagicka.tileentities.researchtable.TileEntityResearchTableBook;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MMBlocks {
    public static Block blockLiquidMagick = new BlockFluidMagick();
    public static Block magickInfuser = new BlockMagickInfuser();
    public static Block magickInfuserOn = new BlockMagickInfuser();

    public static Block bricksEmbrane = new BlockEmbraneBricks();
    public static Block embraneOre = new BlockEmbraneBricks();

    public static Block researchTableBook = new BlockResearchTableBook();

    public static Map<String, Block> renderMap = new HashMap<String, Block>();
    public static Map<String, Block> renderFluidMap = new HashMap<String, Block>();

    public static void preInit() {
        registerBlockFluid(blockLiquidMagick, "blockLiquidMagick", "liquidMagick");
        registerBlock(magickInfuser, "magickInfuser", "magickInfuser", TileEntityMagickInfuser.class);
        registerBlock(magickInfuserOn, "magickInfuserOn", "magickInfuserOn", TileEntityMagickInfuser.class, null);
        registerBlock(bricksEmbrane, "embraneBricks", "bricksEmbrane");
        registerBlock(embraneOre, "embraneOre", "embrane_ore");
        registerBlock(researchTableBook, "researchTableBook", "researchTableBook", TileEntityResearchTableBook.class);

    }

    public static void init() {

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        for (Map.Entry<String, Block> ent : renderMap.entrySet()) {
            renderItem.getItemModelMesher().register(Item.getItemFromBlock(ent.getValue()), 0, new ModelResourceLocation(Reference.modid + ":" + ent.getKey(), "inventory"));
        }

        for (Entry<String, Block> ent : renderFluidMap.entrySet()) {
            final Block toRender = ent.getValue();
            ModelBakery.addVariantName(Item.getItemFromBlock(toRender));
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(toRender), new ItemMeshDefinition() {
                @Override
                public ModelResourceLocation getModelLocation(ItemStack stack) {
                    return new ModelResourceLocation(Reference.modid + ":" + toRender.getClass().getSimpleName(), "fluid");
                }
            });
            ModelLoader.setCustomStateMapper(toRender, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(Reference.modid + ":" + toRender.getClass().getSimpleName(), "fluid");
                }
            });
        }
    }

    public void fluidRender(Block block) {

        final Block toRender = block;

    }

    private static void registerBlockFluid(Block block, String name, String key) {
        block.setUnlocalizedName(key).setCreativeTab(MMItems.tab);
        renderFluidMap.put(key, block);
        GameRegistry.registerBlock(block, key);
    }

    private static void registerBlock(Block block, String name, String key) {
        block.setUnlocalizedName(key).setCreativeTab(MMItems.tab);
        renderMap.put(key, block);
        GameRegistry.registerBlock(block, key);
    }

    private static void registerBlock(Block block, String name, String key, Class tile) {
        registerBlock(block, name, key);
        GameRegistry.registerTileEntity(tile, key);
    }

    private static void registerBlock(Block block, String name, String key, Class tile, CreativeTabs tab) {
        block.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, block);
        GameRegistry.registerBlock(block, key);
        GameRegistry.registerTileEntity(tile, key);
    }

}

package getfluxed.minemagicka.common.items;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.nature.TreeSap;
import getfluxed.minemagicka.common.blocks.MMBlocks;
import getfluxed.minemagicka.common.items.magick.ItemMagickSolid;
import getfluxed.minemagicka.common.items.nature.ItemSapExtractor;
import getfluxed.minemagicka.common.items.nature.ItemTreeSap;
import getfluxed.minemagicka.common.items.pages.ItemPage;
import getfluxed.minemagicka.common.items.pages.ItemPageLocked;
import getfluxed.minemagicka.common.reference.Reference;
import getfluxed.minemagicka.common.util.CreativeTabMM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class MMItems {
    public static CreativeTabs tab = new CreativeTabMM();

    public static Item staff = new ItemStaff();
    public static Item bucketMagickLiquid = new ItemFluidBucket(MMBlocks.blockLiquidMagick);
    public static Item ingotEmbrane = new ItemIngot();
    public static Item bookMagick = new ItemMagickBook();
    public static Item page = new ItemPage();
    public static Item pageLocked = new ItemPageLocked();
    public static Item treeSap = new ItemTreeSap(new TreeSap("Tree Sap", 0xFFFFFF));
    public static Item treeSapMagick = new ItemTreeSap(new TreeSap("Magick Sap", 0xFF55FF));

    public static Item sapExtractor = new ItemSapExtractor();

    public static Item magickSolid = new ItemMagickSolid();

    public static Item magicResource = new ItemMagicResource();

    public static Map<String, Item> renderMap = new HashMap<String, Item>();

    public static void preInit() {
        registerItem(bucketMagickLiquid, "bucketMagickLiquid", "bucketmagickliquid");
        registerItem(bookMagick, "bookMagick", "bookMagick");
        registerItem(ingotEmbrane, "embraneIngot", "embrane_ingot");
        registerItem(staff, "staff", "staff");
        registerItem(page, "page", "page_magick");
        registerItem(pageLocked, "pageLocked", "page_magick_locked");
        registerItem(treeSap, "treeSap", "tree_sap");
        registerItem(treeSapMagick, "treeSapMagick", "tree_sap_magick");
        registerItemMeta(sapExtractor, "sapExtractor", "sap_extractor");
        registerItem(magickSolid, "magick_solid", "magick_solid");
        registerItem(magicResource, "magic_resource", "magic_resource");

    }

    public static void init() {

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        for (Map.Entry<String, Item> ent : renderMap.entrySet()) {
            renderItem.getItemModelMesher().register(ent.getValue(), 0, new ModelResourceLocation(Reference.modid + ":" + ent.getKey(), "inventory"));
        }
        renderItem.getItemModelMesher().register(sapExtractor, 0, new ModelResourceLocation(Reference.modid + ":" + "sap_extractor", "inventory"));
        for (int i = 0; i < ElementRegistry.getElementList().length; i++)
            renderItem.getItemModelMesher().register(magickSolid, i, new ModelResourceLocation(Reference.modid + ":" + "magick_solid", "inventory"));

    }

    public static void registerItem(Item item, String name, String key) {
        if (MineMagicka.isDevEnv)
            writeFile(item, key);
        item.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, item);

        GameRegistry.registerItem(item, key);
    }

    public static void registerItemMeta(Item item, String name, String key) {
        if (MineMagicka.isDevEnv)
            writeFile(item, key);
        item.setCreativeTab(tab);
        renderMap.put(key, item);

        GameRegistry.registerItem(item, key);
    }

    public static void registerItem(Item item, String name, String key, String texture) {
        writeFile(item, key);
        item.setUnlocalizedName(key).setCreativeTab(tab);

        GameRegistry.registerItem(item, key);
    }

    public static void writeFile(Item item, String key) {
        try {
            File f = new File(System.getProperty("user.home") + "/getFluxed/" + key + ".json");
            if (System.getProperty("user.home").endsWith("Jared")) {
                f = new File(System.getProperty("user.home") + "/Documents/Github/MineMagicka/src/main/resources/assets/minemagicka/models/item/" + key + ".json");
            }
            if (!f.exists()) {
                f.createNewFile();
                File base = new File(System.getProperty("user.home") + "/getFluxed/base.json");
                Scanner scan = new Scanner(base);
                List<String> content = new ArrayList<String>();
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    if (line.contains("%modid%")) {
                        line = line.replace("%modid%", Reference.modid);
                    }
                    if (line.contains("%key%")) {
                        line = line.replace("%key%", key);
                    }
                    content.add(line);
                }
                scan.close();
                FileWriter write = new FileWriter(f);
                for (String s : content) {
                    write.write(s + "\n");
                }
                write.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

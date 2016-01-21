package getfluxed.minemagicka.items;

import getfluxed.minemagicka.blocks.MMBlocks;
import getfluxed.minemagicka.items.pages.ItemPage;
import getfluxed.minemagicka.items.pages.ItemPageLocked;
import getfluxed.minemagicka.reference.Reference;
import getfluxed.minemagicka.util.CreativeTabMM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class MMItems {
    public static CreativeTabs tab = new CreativeTabMM();

    public static Item staff = new ItemStaff();
    public static Item bucketMagickLiquid = new ItemFluidBucket((BlockFluidBase) MMBlocks.blockLiquidMagick);
    public static Item ingotEmbrane = new ItemIngot();
    public static Item bookMagick = new ItemMagickBook();

    public static Item page = new ItemPage();
    public static Item pageLocked = new ItemPageLocked();

    public static Map<String, Item> renderMap = new HashMap<String, Item>();

    public static void preInit() {
        registerItem(bucketMagickLiquid, "bucketMagickLiquid", "bucketmagickliquid");
        registerItem(bookMagick, "bookMagick", "bookMagick");
        registerItem(ingotEmbrane, "embraneIngot", "embrane_ingot");
        registerItem(staff, "staff", "staff");
        registerItem(page, "page", "page_magick");
        registerItem(pageLocked, "pageLocked", "page_magick_locked");

    }

    public static void init() {

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        for (Map.Entry<String, Item> ent : renderMap.entrySet()) {
            renderItem.getItemModelMesher().register(ent.getValue(), 0, new ModelResourceLocation(Reference.modid + ":" + ent.getKey(), "inventory"));
        }
    }

    public static void registerItem(Item item, String name, String key) {
        item.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, item);
        writeFile(item, key);
        GameRegistry.registerItem(item, key);
    }

    public static void writeFile(Item item, String key) {
        try {
            File f = new File(System.getProperty("user.home") + "/getFluxed/" + key + ".json");
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

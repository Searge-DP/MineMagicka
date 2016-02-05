package getfluxed.minemagicka.api.nature;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class NatureRegistry {

    private static BiMap<Integer, TreeSap> saps = HashBiMap.create();
    private static BiMap<Block, TreeSap> treeToSap = HashBiMap.create();
    private static BiMap<ItemStack, TreeSap> itemToSap = HashBiMap.create();

    private static int sapCount = 0;

    public static void registerSap(ItemStack item, TreeSap sap, Block treeBlock) {
        saps.put(sapCount++, sap);
        treeToSap.put(treeBlock, sap);
        itemToSap.put(item, sap);
    }

    public static TreeSap getSap(int id) {
        return saps.get(id);
    }

    public static TreeSap getSap(Item item) {
        return itemToSap.get(item);
    }

    public static BiMap<ItemStack, TreeSap> getItemToSap() {
        return itemToSap;
    }

    public static int getSapCount() {
        return sapCount;
    }

    public static BiMap<Block, TreeSap> getTreeToSap() {
        return treeToSap;
    }

    public static BiMap<Integer, TreeSap> getSaps() {
        return saps;
    }

}

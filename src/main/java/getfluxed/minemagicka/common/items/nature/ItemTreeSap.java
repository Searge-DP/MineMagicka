package getfluxed.minemagicka.common.items.nature;

import getfluxed.minemagicka.api.nature.TreeSap;
import getfluxed.minemagicka.common.items.ModItem;
import net.minecraft.item.ItemStack;

public class ItemTreeSap extends ModItem {

    public TreeSap sap;

    public ItemTreeSap(TreeSap sap) {
        this.sap = sap;
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return sap.getColour();
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return sap.getName();
    }

}

package getfluxed.minemagicka.common.items.magick;

import java.util.List;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.common.items.ModItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMagickSolid extends ModItem {
    @Override
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return ElementRegistry.getElementList()[stack.getItemDamage()].getColor();
    }

    public ItemMagickSolid() {
        setHasSubtypes(true);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "_" + ElementRegistry.getElementList()[stack.getItemDamage()];
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < ElementRegistry.getElementList().length; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

}

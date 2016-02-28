package getfluxed.minemagicka.common.items.magick;

import fluxedCore.util.NBTHelper;
import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.spells.IElementProvider;
import getfluxed.minemagicka.common.items.MMItems;
import getfluxed.minemagicka.common.items.ModItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * @author WireSegal
 *         Created at 3:27 PM on 2/17/16.
 */
public class ItemMagickSolid extends ModItem {

    public static ItemStack ofElement(String element) {
        ItemStack stack = new ItemStack(MMItems.magickSolid);
        NBTHelper.setString(stack, "element", element);
        return stack;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        IElement el = ElementRegistry.getElementFromName(NBTHelper.getString(stack, "element"));
        if (el == null) return StatCollector.translateToLocal("item.mm.magic_resource.empty");
        return StatCollector.translateToLocalFormatted("item.mm.solid_magic.name", el.getName());
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        IElement el = ElementRegistry.getElementFromName(NBTHelper.getString(stack, "element"));
        if (el != null)
            tooltip.add(el.getDescription());
    }
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (IElement el : ElementRegistry.getElementList()) {
            subItems.add(ofElement(el.getUnlocalizedName()));
        }
    }

    public int color(ItemStack stack) {
        IElement el = ElementRegistry.getElementFromName(NBTHelper.getString(stack, "element"));
        if (el != null)
            return el.getColor();
        return 0xFFFFFF;
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        switch (pass) {
            case 0:
                return color(stack);
            default:
                return 0xFFFFFF;
        }
    }
}

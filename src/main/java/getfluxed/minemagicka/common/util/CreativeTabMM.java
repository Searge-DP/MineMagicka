package getfluxed.minemagicka.common.util;

import getfluxed.minemagicka.common.items.MMItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMM extends CreativeTabs {

    public CreativeTabMM() {
        super("mm.tab.name");
    }

    @Override
    public Item getTabIconItem() {
        return MMItems.staff;
    }

}

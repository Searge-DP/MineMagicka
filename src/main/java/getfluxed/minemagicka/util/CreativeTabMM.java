package getfluxed.minemagicka.util;

import getfluxed.minemagicka.items.MMItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMM extends CreativeTabs{

	public CreativeTabMM() {
		super("mmc.tab.name");
	}

	@Override
	public Item getTabIconItem() {
		return MMItems.staff;
	}

}

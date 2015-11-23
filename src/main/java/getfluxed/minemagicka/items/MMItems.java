package getfluxed.minemagicka.items;

import cpw.mods.fml.common.registry.GameRegistry;
import getfluxed.minemagicka.reference.Reference;
import getfluxed.minemagicka.util.CreativeTabMM;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MMItems {
	public static CreativeTabs tab;

	public static Item staff = new ItemStaff();
	
	public static void preInit(){
		tab = new CreativeTabMM();
		registerItemNoTexture(staff, "staff", "staff");
	}
	
	public static void registerItem(Item item, String name, String key) {
		item.setUnlocalizedName(key).setTextureName(Reference.modid + ":" + key).setCreativeTab(tab);
		GameRegistry.registerItem(item, name);
	}

	public static void registerItem(Item item, String name, String key, String texture) {
		item.setUnlocalizedName(key).setTextureName(Reference.modid + ":" + texture).setCreativeTab(tab);
		GameRegistry.registerItem(item, name);
	}

	public static void registerItemNoTexture(Item item, String name, String key) {
		item.setUnlocalizedName(key).setCreativeTab(tab);
		GameRegistry.registerItem(item, name);
	}
}

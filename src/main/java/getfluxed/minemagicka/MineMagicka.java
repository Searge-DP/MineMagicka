package getfluxed.minemagicka;

import static getfluxed.minemagicka.reference.Reference.modid;
import static getfluxed.minemagicka.reference.Reference.name;
import static getfluxed.minemagicka.reference.Reference.version;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import getfluxed.minemagicka.api.RecipeRegistry;
import getfluxed.minemagicka.api.recipes.RecipeMagickInfusion;
import getfluxed.minemagicka.blocks.MMBlocks;
import getfluxed.minemagicka.client.GUIHandler;
import getfluxed.minemagicka.entities.spells.base.EntityBall;
import getfluxed.minemagicka.events.MagickEventHandler;
import getfluxed.minemagicka.items.MMItems;
import getfluxed.minemagicka.liquids.MMLiquids;
import getfluxed.minemagicka.network.PacketHandler;
import getfluxed.minemagicka.proxy.IProxy;
import getfluxed.minemagicka.reference.BuffReference;
import getfluxed.minemagicka.reference.ElementReference;
import getfluxed.minemagicka.reference.SpellReference;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

@Mod(modid = modid, name = name, version = version)
public class MineMagicka {

	@SidedProxy(clientSide = "getfluxed.minemagicka.proxy.ClientProxy", serverSide = "getfluxed.minemagicka.proxy.ServerProxy")
	public static IProxy proxy;

	@Instance
	public static MineMagicka INSTANCE;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		RecipeRegistry.registerMagickInfusionRecipe(new RecipeMagickInfusion(new ItemStack(Items.stick), new ItemStack(Items.diamond), 500));
		ElementReference.preInit();
		SpellReference.preInit();
		BuffReference.preInit();
		PacketHandler.preInit();
		MMLiquids.preInit();
		MMBlocks.preInit();
		MMItems.preInit();
		new GUIHandler();
		EntityRegistry.registerModEntity(EntityBall.class, "ball", 0, INSTANCE, 30, 30, true);

		proxy.registerRenderers();
		new MagickEventHandler();

	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}

}

package getfluxed.minemagicka;

import static getfluxed.minemagicka.reference.Reference.modid;
import static getfluxed.minemagicka.reference.Reference.name;
import static getfluxed.minemagicka.reference.Reference.version;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import getfluxed.minemagicka.events.MagickEventHandler;
import getfluxed.minemagicka.proxy.IProxy;
import getfluxed.minemagicka.reference.ElementReference;


@Mod(modid = modid, name = name, version = version)
public class MineMagicka {

	@SidedProxy(clientSide = "getfluxed.minemagicka.proxy.ClientProxy", serverSide = "getfluxed.minemagicka.proxy.ServerProxy")
	public static IProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		ElementReference.preInit();
		new MagickEventHandler();
		
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}
	
}

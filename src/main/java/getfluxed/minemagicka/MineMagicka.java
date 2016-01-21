package getfluxed.minemagicka;

import getfluxed.minemagicka.api.RecipeRegistry;
import getfluxed.minemagicka.api.recipes.RecipeMagickInfusion;
import getfluxed.minemagicka.api.spells.EntityBall;
import getfluxed.minemagicka.common.blocks.MMBlocks;
import getfluxed.minemagicka.client.GUIHandler;
import getfluxed.minemagicka.common.events.MagickEventHandler;
import getfluxed.minemagicka.common.items.MMItems;
import getfluxed.minemagicka.common.liquids.MMLiquids;
import getfluxed.minemagicka.common.network.PacketHandler;
import getfluxed.minemagicka.common.proxy.IProxy;
import getfluxed.minemagicka.common.reference.ElementReference;
import getfluxed.minemagicka.common.reference.SpellReference;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static getfluxed.minemagicka.common.reference.Reference.*;

@Mod(modid = modid, name = name, version = version)
public class MineMagicka {

    public static boolean isDevEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

    @SidedProxy(clientSide = "getfluxed.minemagicka.client.proxy.ClientProxy", serverSide = "getfluxed.minemagicka.common.proxy.ServerProxy")
    public static IProxy proxy;

    @Instance
    public static MineMagicka INSTANCE;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        MMLiquids.preInit();
        MMBlocks.preInit();
        MMItems.preInit();
        RecipeRegistry.registerMagickInfusionRecipe(new RecipeMagickInfusion(new ItemStack(Items.stick), new ItemStack(Items.diamond), 500));
        ElementReference.preInit();
        SpellReference.preInit();
        // BuffReference.preInit();
        PacketHandler.preInit();

        new GUIHandler();
        EntityRegistry.registerModEntity(EntityBall.class, "ball", 0, INSTANCE, 30, 30, true);

        new MagickEventHandler();

    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.registerRenderers();
        MMBlocks.init();
        MMItems.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        RecipeRegistry.registerMagickInfusionRecipe(new RecipeMagickInfusion(new ItemStack(Items.paper), new ItemStack(MMItems.pageLocked), 250));
    }

}

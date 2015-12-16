package getfluxed.minemagicka.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import getfluxed.minemagicka.client.render.RenderBall;
import getfluxed.minemagicka.client.render.items.RenderItemTransparent;
import getfluxed.minemagicka.entities.spells.base.EntityBall;
import getfluxed.minemagicka.items.MMItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy implements IProxy{

	@Override
	public EntityPlayer getPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public World getWorld() {
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBall.class, new RenderBall());		
		MinecraftForgeClient.registerItemRenderer(MMItems.bookMagick, new RenderItemTransparent());
	}

}

package getfluxed.minemagicka.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import getfluxed.minemagicka.client.render.RenderBall;
import getfluxed.minemagicka.entities.spells.base.EntityBall;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

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
	}

}

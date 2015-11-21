package getfluxed.minemagicka.proxy;

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

}

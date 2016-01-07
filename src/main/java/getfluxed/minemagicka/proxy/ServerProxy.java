package getfluxed.minemagicka.proxy;

import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {

    @Override
    public EntityPlayer getPlayer() {
        return null;
    }

    @Override
    public World getWorld() {
        return FMLServerHandler.instance().getServer().getEntityWorld();
    }

    @Override
    public void registerRenderers() {

    }

}

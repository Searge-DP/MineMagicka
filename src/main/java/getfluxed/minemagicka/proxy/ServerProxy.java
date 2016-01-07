package getfluxed.minemagicka.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.FMLServerHandler;

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

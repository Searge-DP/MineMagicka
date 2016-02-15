package getfluxed.minemagicka.common.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.FMLServerHandler;

public class CommonProxy {

    public EntityPlayer getPlayer() {
        return null;
    }

    public World getWorld() {
        return FMLServerHandler.instance().getServer().getEntityWorld();
    }

    public void registerRenderers() {

    }

}

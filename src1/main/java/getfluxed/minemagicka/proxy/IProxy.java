package getfluxed.minemagicka.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IProxy {

    public EntityPlayer getPlayer();

    public World getWorld();

    public void registerRenderers();
}

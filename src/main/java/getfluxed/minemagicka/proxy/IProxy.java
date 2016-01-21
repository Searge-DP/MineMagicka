package getfluxed.minemagicka.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IProxy {

    EntityPlayer getPlayer();

    World getWorld();

    void registerRenderers();
}

package getfluxed.minemagicka.common.proxy;

import getfluxed.minemagicka.client.render.ring.DummyRingHandler;
import getfluxed.minemagicka.client.render.ring.IRingGUIHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.FMLServerHandler;

public class CommonProxy {

    public IRingGUIHandler ringHandler = new DummyRingHandler();

    public EntityPlayer getPlayer() {
        return null;
    }

    public World getWorld() {
        return FMLServerHandler.instance().getServer().getEntityWorld();
    }

    public void registerRenderers() {

    }

}

package getfluxed.minemagicka.client.proxy;

import getfluxed.minemagicka.api.spells.EntityBall;
import getfluxed.minemagicka.client.render.RenderBall;
import getfluxed.minemagicka.client.render.ring.IRingGUIHandler;
import getfluxed.minemagicka.client.render.ring.RingGUIHandler;
import getfluxed.minemagicka.common.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    public IRingGUIHandler ringHandler = new RingGUIHandler();

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
        RenderingRegistry.registerEntityRenderingHandler(EntityBall.class, new RenderBall(Minecraft.getMinecraft().getRenderManager()));
    }

}

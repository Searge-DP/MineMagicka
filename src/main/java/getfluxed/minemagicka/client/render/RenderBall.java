package getfluxed.minemagicka.client.render;

import getfluxed.minemagicka.client.render.models.ModelBall;
import getfluxed.minemagicka.common.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBall extends RenderEntity {
    ModelBall ball = new ModelBall();

    public RenderBall(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
        if (renderManager != null)
            if (this.renderManager.options != null) {
                GL11.glPushMatrix();
                GL11.glTranslated(x, y - 1, z);
                GL11.glRotated((x + y + z) * entity.worldObj.rand.nextInt(), 0, 1, 0);
                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.modid, "textures/models/modelBall.png"));
                ball.render(0.0625f);
                GL11.glPopMatrix();
            }
    }

}

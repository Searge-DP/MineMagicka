package getfluxed.minemagicka.client.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import getfluxed.minemagicka.api.spells.EntityBall;
import getfluxed.minemagicka.api.spells.ISpellBall;
import getfluxed.minemagicka.client.render.models.ModelBall;
import getfluxed.minemagicka.common.reference.Reference;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBall extends Render<EntityBall> {
    ModelBall ball = new ModelBall();

    public RenderBall(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void doRender(EntityBall entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
        if (renderManager != null) {
            if (this.renderManager.options != null) {
                GL11.glPushMatrix();
                GL11.glTranslated(x, y - 1, z);
                GL11.glRotated((x + y + z) * entity.worldObj.rand.nextInt(), 0, 1, 0);

                System.out.println(entity); // DEBUG
                ISpellBall ballSpell = entity.ball;
                if (ballSpell != null) {
                    // TODO figure out why the f this block of code is never entered
                    int c = ballSpell.getColor(entity, entity.worldObj);
                    Color color = new Color(c);
                    GL11.glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
                }
                bindEntityTexture(entity);
                ball.render(0.0625f);
                GL11.glColor3f(1f, 1f, 1f);
                GL11.glPopMatrix();
            }
        }
    }

    private ResourceLocation location = new ResourceLocation(Reference.modid, "textures/models/modelBall.png");

    @Override
    protected ResourceLocation getEntityTexture(EntityBall entity) {
        return location;
    }
}

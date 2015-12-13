package getfluxed.minemagicka.client.render;

import org.lwjgl.opengl.GL11;

import getfluxed.minemagicka.client.render.models.ModelBall;
import getfluxed.minemagicka.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBall extends RenderEntity {
	ModelBall ball = new ModelBall();

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y-1, z);
		GL11.glRotated((x+y+z)*entity.worldObj.rand.nextInt(), 0, 1, 0);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.modid, "textures/models/modelBall.png"));
		ball.render(0.0625f);
		GL11.glPopMatrix();
	}

}

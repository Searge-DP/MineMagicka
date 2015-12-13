package getfluxed.minemagicka.client.render.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelBall - Jaredlll08 Created using Tabula 4.1.1
 */
public class ModelBall extends ModelBase {
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;

	public ModelBall() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.shape2 = new ModelRenderer(this, 0, 0);
		this.shape2.setRotationPoint(0.0F, 18.0F, 0.0F);
		this.shape2.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
		this.setRotateAngle(shape2, 0.0F, 0.0F, 0.7853981633974483F);
		this.shape3 = new ModelRenderer(this, 0, 0);
		this.shape3.setRotationPoint(0.0F, 18.0F, 0.0F);
		this.shape3.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
		this.setRotateAngle(shape3, 0.0F, 0.7853981633974483F, 0.0F);
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 18.0F, 0.0F);
		this.shape1.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
		this.setRotateAngle(shape1, 0.7853981633974483F, 0.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.shape2.render(f5);
		this.shape3.render(f5);
		this.shape1.render(f5);
	}

	public void render(float f5) {
		this.shape2.render(f5);
		this.shape3.render(f5);
		this.shape1.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

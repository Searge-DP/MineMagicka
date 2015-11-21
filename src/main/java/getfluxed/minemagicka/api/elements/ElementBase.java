package getfluxed.minemagicka.api.elements;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;

public abstract class ElementBase implements IElement{

	public void render(int x, int y) {
		GL11.glPushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
		Minecraft.getMinecraft().ingameGUI.func_152125_a(x, y, 0, 0, 24, 24, 24, 24, 24, 24);
		GL11.glPopMatrix();
	}

}

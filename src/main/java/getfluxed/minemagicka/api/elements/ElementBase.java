package getfluxed.minemagicka.api.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public abstract class ElementBase implements IElement {

    @SuppressWarnings("static-access")
    public void render(Gui gui, int x, int y) {
        GL11.glPushMatrix();
        Color color = new Color(this.getColor());
        GL11.glColor3f(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
        gui.func_152125_a(x, y, 0, 0, 24, 24, 24, 24, 24, 24);
        GL11.glColor3f(1f, 1f, 1f);
        GL11.glPopMatrix();
    }

}

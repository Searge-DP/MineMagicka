package getfluxed.minemagicka.api.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public abstract class ElementBase implements IElement {

    @SuppressWarnings("static-access")
    public void render(Gui gui, int x, int y) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
        gui.func_152125_a(x, y, 0, 0, 24, 24, 24, 24, 24, 24);
        GL11.glPopMatrix();
    }

}

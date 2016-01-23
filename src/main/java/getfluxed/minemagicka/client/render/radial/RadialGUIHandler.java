package getfluxed.minemagicka.client.render.radial;

import getfluxed.minemagicka.api.rendering.IRadialItem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * @author WireSegal
 *         Created at 9:50 AM on 1/21/16.
 */
@SideOnly(Side.CLIENT)
public class RadialGUIHandler {
    private static boolean guiOn = false;
    private static IRadialGUI gui;

    static class DummyGUI implements IRadialGUI {
        @Override
        public int innerSize() {
            return 4;
        }

        @Override
        public int size() {
            return 8;
        }

        @Override
        public IRadialItem getInnerItem(int index) {
            return null;
        }

        @Override
        public IRadialItem getOuterItem(int index) {
            return null;
        }

        @Override
        public void onClick() {

        }
    }

    private static RadialGUIHandler instance = new RadialGUIHandler();

    public static void setup() {
        MinecraftForge.EVENT_BUS.register(instance);
    }

    public static boolean enableGui(IRadialGUI renderer) {
        return enableGui(renderer, false);
    }

    public static boolean enableGui(IRadialGUI renderer, boolean force) {
        if ((!guiOn || force) && renderer != null) {
            guiOn = true;
            gui = renderer;
            return true;
        }
        return false;
    }

    public static void disableGui() {
        gui = null;
        guiOn = false;
    }

    public static boolean getGuiState() {
        return guiOn && getActiveGUI() != null;
    }

    public static IRadialGUI getActiveGUI() {
        return gui;
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre e) {
        if (e.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS && getGuiState()) {

            int sections = getActiveGUI().size();
            int innerSections = getActiveGUI().innerSize();

            float radius = e.resolution.getScaledHeight()/4;

            float x1 = e.resolution.getScaledWidth()/2;
            float y1 = e.resolution.getScaledHeight()/2;

            renderCircle(x1, y1, radius, sections, new Color(0f, 0f, 0f, 0.7f));
            renderCircle(x1, y1, radius/2, innerSections, new Color(0x1E1E1E));

            e.setCanceled(true);
        }
    }

    static void renderCircle(float x, float y, float radius, int sections, Color color) {

        float r = color.getRed()/255F;
        float g = color.getGreen()/255F;
        float b = color.getBlue()/255F;
        float a = color.getAlpha()/255F;

        float rl = (r + .25f)/2;
        float gl = (g + .25f)/2;
        float bl = (b + .25f)/2;

        int outerRad = 360;
        if (sections > 0) outerRad /= sections;

        int angle;

        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(r, g, b, a);

        GL11.glBegin(GL11.GL_TRIANGLE_FAN);

        float x2, y2, x3, y3;
        if (sections > 1) {
            GL11.glVertex2f(x, y);
            for (int section = 0; section <= sections; section++) {
                angle = section * outerRad;
                if (section % 2 == 0) GlStateManager.color(r, g, b, a);
                else GlStateManager.color(rl, gl, bl, a);
                x2 = x + MathHelper.sin((float) (angle * Math.PI / 180)) * radius;
                y2 = y + MathHelper.cos((float) (angle * Math.PI / 180)) * radius;
                x3 = x + MathHelper.sin((float) ((angle + outerRad / 2) * Math.PI / 180)) * radius;
                y3 = y + MathHelper.cos((float) ((angle + outerRad / 2) * Math.PI / 180)) * radius;
                GL11.glVertex2f(x2, y2);
                if (section != sections) GL11.glVertex2f(x3, y3);
            }
        } else if (sections == 1) {
            GL11.glVertex2f(x, y);
            GL11.glVertex2f(x+radius, y);
            GL11.glVertex2f(x, y+radius);
            GL11.glVertex2f(x-radius, y);
            GL11.glVertex2f(x, y-radius);
        }

        GL11.glEnd();
        GlStateManager.resetColor();
        GlStateManager.disableBlend();
    }
}

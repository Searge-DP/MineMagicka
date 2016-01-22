package getfluxed.minemagicka.client.render.radial;

import getfluxed.minemagicka.api.rendering.IRadialRender;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * @author WireSegal
 *         Created at 9:50 AM on 1/21/16.
 */
public class RadialGUIHandler {
    private static boolean guiOn = false;
    private static IRadialGUI gui;

    static class DummyGUI implements IRadialGUI {
        @Override
        public int innerSize() {
            return 0;
        }

        @Override
        public int size() {
            return 4;
        }

        @Override
        public IRadialRender getInnerItem(int index) {
            return null;
        }

        @Override
        public IRadialRender getOuterItem(int index) {
            return null;
        }
    }

    private static RadialGUIHandler instance = new RadialGUIHandler();
    @SideOnly(Side.CLIENT)
    public static void setup() {
        MinecraftForge.EVENT_BUS.register(instance);

        enableGui(new DummyGUI());
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
    @SideOnly(Side.CLIENT)
    public void onRender(RenderGameOverlayEvent.Pre e) {
        if (e.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS && getGuiState()) {

            final float transparency = 0.7f;

            int sections = getActiveGUI().size();
            int outerRad = 360;
            if (sections > 0) outerRad /= sections;
            int innerSections = getActiveGUI().innerSize();
            int innerRad = 360;
            if (innerSections > 0) innerRad /= sections;

            float radius = 100f;

            float x1 = e.resolution.getScaledWidth()/2;
            float y1 = e.resolution.getScaledHeight()/2;
            float x2;
            float y2;
            float x3;
            float y3;

            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.color(0.0f,0.0f,0.0f,transparency);

            GL11.glBegin(GL11.GL_TRIANGLE_FAN);


            int slice;
            int oldSlice = 0;

            GL11.glVertex2f(x1, y1);

            for (int angle = 0; angle <= 360; angle++) {
                slice = Math.round((angle)/(outerRad+0.5f));
                if (slice != oldSlice && angle != 360) {
                    oldSlice = slice;
                    if (slice % 2 == 0)
                        GlStateManager.color(0.0f,0.0f,0.0f,transparency);
                    else
                        GlStateManager.color(0.5f,0.5f,0.5f,transparency);
                }
                x2 = MathHelper.sin((float) (angle * Math.PI/180)) * radius;
                y2 = MathHelper.cos((float) (angle * Math.PI/180)) * radius;
                x3 = x1+x2;
                y3 = y1+y2;
                GL11.glVertex2f(x3,y3);
            }

            GlStateManager.color(1f,1f,1f,1f);
            GlStateManager.disableBlend();

            GL11.glEnd();

            e.setCanceled(true);
        }
    }
}

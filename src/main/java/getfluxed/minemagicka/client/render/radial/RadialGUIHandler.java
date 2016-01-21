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
            return 8;
        }

        @Override
        public IRadialRender getInnerItem(int index) {
            return null;
        }

        @Override
        public IRadialRender getOuterItem(int index) {
            return null;
        }

        @Override
        public void onClick(boolean inner, int index) {

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

            GL11.glPushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(0.0f,0.0f,0.0f,0.5f);

            GL11.glBegin(GL11.GL_TRIANGLE_FAN);
            GL11.glVertex2f(x1,y1);

            for (float angle=0f; angle <= 360; angle++) {
                if (angle % (outerRad+1) == 0) {
                    if (angle % (outerRad*2+1) != 0) {
                        GL11.glColor4f(0.0f,0.0f,0.0f,0.5f);
                    } else {
                        GL11.glColor4f(0.2f,0.2f,0.2f,0.5f);
                    }
                }
                x2 = x1 + MathHelper.sin((float) (angle * Math.PI/180)) * radius;
                y2 = y1 + MathHelper.cos((float) (angle * Math.PI/180)) * radius;
                GL11.glVertex2f(x2,y2);
            }

            GL11.glColor4f(1f,1f,1f,1f);
            GlStateManager.disableBlend();

            GL11.glEnd();
            GL11.glPopMatrix();

            e.setCanceled(true);
        }
    }
}

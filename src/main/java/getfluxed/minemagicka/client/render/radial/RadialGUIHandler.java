package getfluxed.minemagicka.client.render.radial;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author WireSegal
 *         Created at 9:50 AM on 1/21/16.
 */
@SideOnly(Side.CLIENT)
public class RadialGUIHandler {
    private static boolean guiOn = false;
    private static IRadialGUI gui;

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
        return guiOn;
    }

    public static IRadialGUI getActiveGUI() {
        return gui;
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post e) {
        if (e.type == RenderGameOverlayEvent.ElementType.ALL && getGuiState()) {

            int sections = getActiveGUI().size();
            int outerRad = 360;
            if (sections > 0) outerRad /= sections;
            int innerSections = getActiveGUI().innerSize();
            int innerRad = 360;
            if (innerSections > 0) innerRad /= sections;

        }
    }
}

package getfluxed.minemagicka.api.rendering;

import net.minecraft.client.gui.Gui;

import java.awt.*;

/**
 * @author WireSegal
 *         Created at 9:53 AM on 1/21/16.
 */
public interface IRadialRender {
    /**
     * Renders this object in the current gui.
     *
     * @param gui The current GUI.
     * @param x The X coordinate onscreen to render at.
     * @param y The Y coordinate onscreen to render at.
     * @param colorModifier The color to apply to the object. (Example: Blue when selected.)
     */
    void render(Gui gui, int x, int y, Color colorModifier);
}

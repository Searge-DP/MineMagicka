package getfluxed.minemagicka.api.rendering;

import net.minecraft.client.gui.Gui;

import java.awt.*;

/**
 * @author WireSegal
 *         Created at 1:39 PM on 2/9/16.
 */
public interface IHaloFacet {
    /**
     * Renders this object.
     *
     * @param colorModifier The color to apply to the object. (Example: Blue when selected.)
     */
    void render(Color colorModifier);
}

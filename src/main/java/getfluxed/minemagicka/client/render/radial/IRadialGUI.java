package getfluxed.minemagicka.client.render.radial;

import getfluxed.minemagicka.api.rendering.IRadialRender;

/**
 * @author WireSegal
 *         Created at 2:07 PM on 1/21/16.
 */
public interface IRadialGUI {
    int size();
    int innerSize();

    IRadialRender getOuterItem(int index);
    IRadialRender getInnerItem(int index);

    void onClick(boolean inner, int index);
}

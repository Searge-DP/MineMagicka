package getfluxed.minemagicka.client.render.radial;

import getfluxed.minemagicka.api.rendering.IRadialItem;

/**
 * @author WireSegal
 *         Created at 2:07 PM on 1/21/16.
 */
public interface IRadialGUI {
    int size();
    int innerSize();

    IRadialItem getOuterItem(int index);
    IRadialItem getInnerItem(int index);

    void onClick();
}

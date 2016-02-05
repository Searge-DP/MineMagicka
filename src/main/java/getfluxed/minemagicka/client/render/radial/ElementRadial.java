package getfluxed.minemagicka.client.render.radial;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.rendering.IRadialItem;

/**
 * @author WireSegal Created at 9:44 PM on 1/22/16.
 */
public class ElementRadial implements IRadialGUI {
    @Override
    public int size() {
        return ElementRegistry.getElementList().length;
    }

    @Override
    public int innerSize() {
        return ElementRegistry.getElementList().length;
    }

    @Override
    public IRadialItem getInnerItem(int index) {
        return ElementRegistry.getElementList()[index];
    }

    @Override
    public IRadialItem getOuterItem(int index) {
        return ElementRegistry.getElementList()[index];
    }

    @Override
    public void onClick() {
    }
}

package getfluxed.minemagicka.client.render.ring;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.rendering.IHaloFacet;

/**
 * @author WireSegal
 *         Created at 3:21 PM on 2/9/16.
 */
public abstract class ElementHalo implements IHalo {

    @Override
    public IHaloFacet getSegment(int seg) {
        return ElementRegistry.getElementList()[seg];
    }

    @Override
    public int segments() {
        return ElementRegistry.getElementList().length;
    }
}

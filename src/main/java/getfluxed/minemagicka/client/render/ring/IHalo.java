package getfluxed.minemagicka.client.render.ring;

import getfluxed.minemagicka.api.rendering.IHaloFacet;

/**
 * @author WireSegal
 *         Created at 1:41 PM on 2/9/16.
 */
public interface IHalo {
    int segments();

    IHaloFacet getSegment(int seg);

    void onSegmentClicked(int seg);
}

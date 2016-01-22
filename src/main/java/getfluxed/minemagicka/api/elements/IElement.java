package getfluxed.minemagicka.api.elements;

import getfluxed.minemagicka.api.rendering.IRadialItem;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface IElement extends IRadialItem {
    /**
     * Gets the localized name of the element
     *
     * @return the name of the element
     */
    String getName();

    /**
     * return the unlocalized name of the element
     */
    String getUnlocalizedName();

    /**
     * Gets the description of the element
     *
     * @return the description of the element
     */
    String getDescription();

    /**
     * Gets the texture of the element.
     *
     * @return the texture of the element
     */
    ResourceLocation getTexture();

    /**
     * Gets the glColor of the element.
     *
     * @return The color in 0xrrggbb form.
     */
    int getColor();

    /**
     * Is this a primary element? (Does a player need to combine elements to
     * create
     *
     * @return
     */
    boolean isPrimary();

    /**
     * Gets the opposite elements
     *
     * @return
     */
    List<IElement> getOpposites();

    /**
     * If this isn't a primary element, this combination is used to create it. Can only include Primary Elements
     *
     * @return
     */
    Pair<IElement, IElement> getCombination();

}

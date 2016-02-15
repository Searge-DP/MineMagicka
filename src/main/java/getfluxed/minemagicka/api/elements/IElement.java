package getfluxed.minemagicka.api.elements;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;


public interface IElement {
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
     * Is this a primary element?
     *
     * @return Whether the element is primary
     */
    boolean isPrimary();

    /**
     * If this isn't a primary element, this combination is used to create it. Can only include Primary Elements
     *
     * @return The pair of elements that make this element.
     */
    Pair<IElement, IElement> getCombination();

    /**
     * Render the element in the current gui.
     *
     * @param gui           The current gui.
     * @param x             The x position in the gui.
     * @param y             The y position in the gui.
     * @param width         The width to render with.
     * @param height        The height to render with.
     * @param colorModifier The color to render with.
     */
    void render(Gui gui, int x, int y, int width, int height, Color colorModifier);
}

package getfluxed.minemagicka.api.elements;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface IElement {
    /**
     * Gets the localized name of the element
     *
     * @return the name of the element
     */
    public String getName();

    /**
     * return the unlocalized name of the element
     */
    public String getUnlocalizedName();

    /**
     * Gets the description of the element
     *
     * @return the description of the element
     */
    public String getDescription();

    /**
     * Gets the texture of the element.
     *
     * @return the texture of the element
     */
    public ResourceLocation getTexture();

    /**
     * Is this a primary element? (Does a player need to combine elements to
     * create
     *
     * @return
     */
    public boolean isPrimary();

    /**
     * Gets the opposite elements
     *
     * @return
     */
    public List<IElement> getOpposites();

    /**
     * If this isn't a primary element, this combination is used to create it. Can only include Primary Elements
     *
     * @return
     */
    public Pair<IElement, IElement> getCombination();

    /**
     * Renders the element in the current gui
     *
     * @param gui
     * @param x
     * @param y
     */
    public void render(Gui gui, int x, int y);

}

package getfluxed.minemagicka.api.compendium;

import net.minecraft.util.ResourceLocation;

/**
 * @author WireSegal
 *         Created at 7:47 PM on 1/6/16.
 */
public interface ICompendiumChapter {
    /**
     * @return The priority of the chapter.
     */
    int getSortingPriority();

    /**
     * @return The icon of the chapter.
     */
    ResourceLocation getIcon();
}

package getfluxed.minemagicka.api.compendium;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author WireSegal
 *         Created at 7:00 PM on 1/6/16.
 *         <p/>
 *         Defines the interface a Compendium Entry must have.
 */
public interface ICompendiumEntry {
    /**
     * @return Every page in the entry.
     */
    List<ICompendiumPage> getPages();

    /**
     * Get a page by its index.
     *
     * @param index - The index to search for.
     * @return The found page.
     */
    ICompendiumPage getPage(int index);

    /**
     * @return Every requirement for the page to be displayed. Returns an empty list if unlocked by default.
     */
    List<String> getRequirements();

    /**
     * Checks if the entry can be seen.
     *
     * @param compendium - The current compendium.
     * @return Whether the entry is visible.
     */
    boolean isEntryVisible(ICompendium compendium);

    /**
     * @return The unlocalized name of this entry.
     */
    String getUnlocalizedName();

    /**
     * Entries with a priority of greater than zero will have italicized names.
     *
     * @return The priority of this entry.
     */
    int getPriority();

    /**
     * @return The itemstack that will be used as the entry icon.
     */
    @SideOnly(Side.CLIENT)
    ItemStack getIcon();
}

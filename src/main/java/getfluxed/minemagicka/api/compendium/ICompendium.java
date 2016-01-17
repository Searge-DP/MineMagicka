package getfluxed.minemagicka.api.compendium;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author WireSegal
 *         Created at 7:08 PM on 1/6/16.
 *         <p/>
 *         Defines the interface for a Compendium item.
 */
public interface ICompendium {
    /**
     * Unlock a research key.
     *
     * @param stack    The compendium stack.
     * @param research - The key to unlock.
     */
    void unlockResearch(ItemStack stack, String research);

    /**
     * Check if a research key is unlocked.
     *
     * @param stack    The compendium stack.
     * @param research - The key to check.
     * @return Whether the key is unlocked.
     */
    boolean isResearchUnlocked(ItemStack stack, String research);

    /**
     * @return A list of all unlocked researches for this compendium.
     */
    List<String> getUnlockedResearch(ItemStack stack);

    /**
     * Unlocks all researches.
     *
     * @param stack The compendium stack.
     */
    void unlockAllResearch(ItemStack stack);

    /**
     * Removes all unlocked research.
     *
     * @param stack The compendium stack.
     */
    void stripAllResearch(ItemStack stack);
}

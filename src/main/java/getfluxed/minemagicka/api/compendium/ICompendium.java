package getfluxed.minemagicka.api.compendium;

import java.util.List;

/**
 * @author WireSegal
 *         <p/>
 *         Created at 7:08 PM on 1/6/16.
 *         <p/>
 *         Defines the interface for a Compendium item.
 */
public interface ICompendium {
    /**
     * Unlock a research key.
     *
     * @param research - The key to unlock.
     */
    void unlockResearch(String research);

    /**
     * Check if a research key is unlocked.
     *
     * @param research - The key to check.
     * @return Whether the key is unlocked.
     */
    boolean isResearchUnlocked(String research);

    /**
     * @return A list of all unlocked researches for this compendium.
     */
    List<String> getUnlockedResearch();

    /**
     * Unlocks all researches.
     */
    void unlockAllResearch();

    /**
     * Removes all unlocked research.
     */
    void stripAllResearch();
}

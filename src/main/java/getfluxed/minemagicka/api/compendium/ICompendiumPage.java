package getfluxed.minemagicka.api.compendium;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author WireSegal
 *         Created at 7:05 PM on 1/6/16.
 *         <p/>
 *         Defines the interface a Compendium Page must have.
 */
public interface ICompendiumPage {
    /**
     * Renders the compendium page.
     *
     * @param gui - The active GuiScreen.
     * @param mx  - The mouse's relative X position.
     * @param my  - The mouse's relative Y position.
     */
    @SideOnly(Side.CLIENT)
    void renderPage(IGuiCompendium gui, int mx, int my);

    /**
     * Called every update tick.
     *
     * @param gui - The active GuiScreen.
     */
    @SideOnly(Side.CLIENT)
    void onTick(IGuiCompendium gui);

    /**
     * Called when this page is opened, whether it was by the compendium opening or the page being turned to.
     *
     * @param gui - The active GuiScreen.
     */
    @SideOnly(Side.CLIENT)
    void onOpened(IGuiCompendium gui);

    /**
     * Called when this page is closed, whether it was by the compendium closing or the page being turned away.
     *
     * @param gui - The active GuiScreen.
     */
    @SideOnly(Side.CLIENT)
    void onClosed(IGuiCompendium gui);

    /**
     * Should be called when this page is added to an entry.
     * This should be done in any implementation of {@link ICompendiumEntry}.
     *
     * @param entry - The entry the page is being added to.
     * @param index - The index the page is in the entry.
     */
    void onPageAdded(ICompendiumEntry entry, int index);

    /**
     * Checks if the page itself is visible in the current compendium.
     *
     * @param compendium - The current compendium.
     * @return Whether the page is visible.
     */
    boolean isPageVisible(ICompendium compendium);

    /**
     * @return This page's unlocalized name.
     */
    String getUnlocalizedName();
}

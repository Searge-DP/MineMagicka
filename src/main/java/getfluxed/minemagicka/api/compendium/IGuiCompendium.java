package getfluxed.minemagicka.api.compendium;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;

import java.util.List;

/**
 * @author WireSegal
 *         Created at 7:16 PM on 1/6/16.
 *         <p/>
 *         An interface for the Compendium GUI. It's safe to cast this type to GuiScreen.
 */
@SideOnly(Side.CLIENT)
public interface IGuiCompendium {
    /**
     * @return The current Compendium entry.
     */
    ICompendiumEntry getEntry();

    /**
     * @return The current Compendium page.
     */
    ICompendiumPage getPage();

    /**
     * @return The current Compendium page's index.
     */
    int getPageIndex();

    /**
     * @return The topmost coordinate of the GUI.
     */
    int getTop();

    /**
     * @return The leftmost coordinate of the GUI.
     */
    int getLeft();

    /**
     * @return The width of the GUI.
     */
    int getWidth();

    /**
     * @return The height of the GUI.
     */
    int getHeight();

    /**
     * @return The rendering height of the GUI.
     */
    float getZLevel();

    /**
     * @return The buttons in the GUI.
     */
    List<GuiButton> getButtonList();

    /**
     * @return The total ticks + partial ticks the player has been in the GUI.
     */
    float getElapsedTicks();

    /**
     * @return The current partial ticks.
     */
    float getPartialTicks();

    /**
     * @return The delta (1F = 1 tick) since the last render call.
     */
    float getTickDelta();
}

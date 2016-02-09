package getfluxed.minemagicka.client.render.ring;

import net.minecraft.entity.player.EntityPlayer;

/**
 * @author WireSegal
 *         Created at 1:43 PM on 2/9/16.
 */
public interface IRingGUIHandler {
    void setGui(IHalo gui, EntityPlayer player);

    IHalo getActiveGui();

    void clearGui();

    float getStartingYaw();
}

package getfluxed.minemagicka.client.render.ring;

import net.minecraft.entity.player.EntityPlayer;

/**
 * @author WireSegal
 *         Created at 2:19 PM on 2/9/16.
 */
public class DummyRingHandler implements IRingGUIHandler {
    @Override
    public IHalo getActiveGui() {
        return null;
    }

    @Override
    public void clearGui() {
        // NO-OP
    }

    @Override
    public void setGui(IHalo gui, EntityPlayer player) {
        // NO-OP
    }

    @Override
    public float getStartingYaw() {
        return 0f;
    }
}

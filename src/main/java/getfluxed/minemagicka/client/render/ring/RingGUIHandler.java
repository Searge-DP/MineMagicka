package getfluxed.minemagicka.client.render.ring;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author WireSegal
 *         Created at 2:21 PM on 2/9/16.
 */
@SideOnly(Side.CLIENT)
public class RingGUIHandler implements IRingGUIHandler {

    private IHalo activeGui;
    private float yaw;

    @Override
    public void clearGui() {
        activeGui = null;
        yaw = 0f;
    }

    @Override
    public IHalo getActiveGui() {
        return activeGui;
    }

    @Override
    public void setGui(IHalo gui, EntityPlayer player) {
        activeGui = gui;
        yaw = HaloRenderHandler.getAngle(player, gui.segments());
    }

    @Override
    public float getStartingYaw() {
        return 0f;
    }
}

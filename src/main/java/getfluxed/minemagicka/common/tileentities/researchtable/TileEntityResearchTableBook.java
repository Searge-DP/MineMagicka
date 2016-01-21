package getfluxed.minemagicka.common.tileentities.researchtable;

import fluxedCore.tileEntities.TileEntityInventory;
import net.minecraft.util.IChatComponent;

public class TileEntityResearchTableBook extends TileEntityInventory {

    public TileEntityResearchTableBook() {
        super(3);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public String getCommandSenderName() {
        return "researchTableBook";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }

}

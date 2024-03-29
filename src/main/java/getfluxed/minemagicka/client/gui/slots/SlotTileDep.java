package getfluxed.minemagicka.client.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTileDep extends Slot {

    private int slotMax;

    public SlotTileDep(IInventory inventory, int number, int x, int y) {
        super(inventory, number, x, y);
        this.slotMax = inventory.getInventoryStackLimit();
    }

    public int getSlotStackLimit() {
        return slotMax;
    }

    public boolean isItemValid(ItemStack stack) {
        return inventory.isItemValidForSlot(this.slotNumber, stack);
    }

}

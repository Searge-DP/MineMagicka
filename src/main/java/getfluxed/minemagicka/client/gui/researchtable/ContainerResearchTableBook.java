package getfluxed.minemagicka.client.gui.researchtable;

import getfluxed.minemagicka.common.items.MMItems;
import getfluxed.minemagicka.common.blocks.tile.researchtable.TileEntityResearchTableBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerResearchTableBook extends Container {
    public ContainerResearchTableBook(InventoryPlayer invPlayer, TileEntityResearchTableBook tile) {

        addSlotToContainer(new Slot(tile, 0, 12, 18) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() == MMItems.bookMagick;
            }
        });
        addSlotToContainer(new Slot(tile, 1, 12, 36));
        addSlotToContainer(new Slot(tile, 2, 12, 54));

        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 142));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
            }
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or
     * you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
        return null;
    }

}

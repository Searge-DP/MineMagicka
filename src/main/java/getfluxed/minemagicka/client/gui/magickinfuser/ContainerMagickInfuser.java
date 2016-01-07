package getfluxed.minemagicka.client.gui.magickinfuser;

import getfluxed.minemagicka.client.gui.slots.SlotTileDep;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMagickInfuser extends Container {
    public ContainerMagickInfuser(InventoryPlayer invPlayer, TileEntityMagickInfuser tile) {

        addSlotToContainer(new SlotTileDep(tile, 0, 36, 13));
        addSlotToContainer(new SlotTileDep(tile, 1, 123, 13) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }
        });

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

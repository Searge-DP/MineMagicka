package getfluxed.minemagicka.client.gui.essencetransfuser;

import getfluxed.minemagicka.client.gui.slots.SlotTileDep;
import getfluxed.minemagicka.common.tileentities.machines.TileEntityEssenceTransfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEssenseTransfuser extends Container {
    public ContainerEssenseTransfuser(InventoryPlayer invPlayer, TileEntityEssenceTransfuser tile) {

        addSlotToContainer(new SlotTileDep(tile, 0, 47, 17));
        addSlotToContainer(new SlotTileDep(tile, 1, 65, 17));
        addSlotToContainer(new SlotTileDep(tile, 2, 56, 53));
        addSlotToContainer(new SlotTileDep(tile, 3, 116, 35));
        

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
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) { //TODO
        switch (slotNumber) {
            case TileEntityEssenceTransfuser.FUEL:
                break;
            case TileEntityEssenceTransfuser.GLASS:
                break;
            case TileEntityEssenceTransfuser.MATERIAL:
                break;
        }
        return null;
    }

}

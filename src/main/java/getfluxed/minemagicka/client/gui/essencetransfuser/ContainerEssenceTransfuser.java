package getfluxed.minemagicka.client.gui.essencetransfuser;

import getfluxed.minemagicka.api.RecipeRegistry;
import getfluxed.minemagicka.client.gui.slots.SlotTileDep;
import getfluxed.minemagicka.common.blocks.tile.machines.TileEntityEssenceTransfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.oredict.OreDictionary;

import static getfluxed.minemagicka.common.blocks.tile.machines.TileEntityEssenceTransfuser.*;

public class ContainerEssenceTransfuser extends Container {
    public ContainerEssenceTransfuser(InventoryPlayer invPlayer, TileEntityEssenceTransfuser tile) {

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

    private static int INVENTORY_BEGIN = OUT + 1;
    private static int INVENTORY_END = INVENTORY_BEGIN + 27;
    private static int HOTBAR_BEGIN = INVENTORY_END;
    private static int HOTBAR_END = HOTBAR_BEGIN + 9;

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == OUT) {
                if (!this.mergeItemStack(itemstack1, INVENTORY_BEGIN, HOTBAR_END, true)) { // Add to the player's inventory
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != GLASS && index != MATERIAL && index != FUEL) {
                boolean flag = false;
                int[] ids = OreDictionary.getOreIDs(itemstack1);
                for (int i : ids) {
                    if (OreDictionary.getOreName(i).equals("blockGlass")) {
                        flag = true;
                    }
                }

                if (flag) {
                    if (!this.mergeItemStack(itemstack1, GLASS, GLASS+1, false)) {
                        return null;
                    }
                } else if (RecipeRegistry.getTransfuserOutput(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, MATERIAL, MATERIAL+1, false)) {
                        return null;
                    }
                } else if (TileEntityFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, FUEL, FUEL+1, false)) {
                        return null;
                    }
                } else if (index >= INVENTORY_BEGIN && index < INVENTORY_END) {
                    if (!this.mergeItemStack(itemstack1, HOTBAR_BEGIN, HOTBAR_END, false)) { // Add to main player inv
                        return null;
                    }
                } else if (index >= HOTBAR_BEGIN && index < HOTBAR_END && !this.mergeItemStack(itemstack1, INVENTORY_BEGIN, INVENTORY_END, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, INVENTORY_BEGIN, HOTBAR_END, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
    }

}

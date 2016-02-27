package getfluxed.minemagicka.common.tileentities.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityEssenceTransfuser extends TileEntity implements ITickable, ISidedInventory {
    private ItemStack[] items;

    public TileEntityEssenceTransfuser() {
        items = new ItemStack[4];
    }

    @Override
    public void update() {

    }

    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f) <= 64;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        switch (slot) {
        case 0:
            int[] ids = OreDictionary.getOreIDs(stack);
            for (int i : ids) {
                if (OreDictionary.getOreName(i).equals("blockGlass")) {
                    return true;
                }
            }
            break;
        case 1:
            return true;
        case 2:
            return TileEntityFurnace.isItemFuel(stack);
        case 3:
            return false;
        }
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        readInventoryFromNBT(nbt);
    }

    public void readInventoryFromNBT(NBTTagCompound tags) {
        NBTTagList nbttaglist = tags.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        for (int iter = 0; iter < nbttaglist.tagCount(); iter++) {
            NBTTagCompound tagList = nbttaglist.getCompoundTagAt(iter);
            byte slotID = tagList.getByte("Slot");
            if (slotID >= 0 && slotID < items.length) {
                items[slotID] = ItemStack.loadItemStackFromNBT(tagList);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        writeInventoryToNBT(tag);
    }

    public void writeInventoryToNBT(NBTTagCompound tags) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int iter = 0; iter < items.length; iter++) {
            if (items[iter] != null) {
                NBTTagCompound tagList = new NBTTagCompound();
                tagList.setByte("Slot", (byte) iter);
                items[iter].writeToNBT(tagList);
                nbttaglist.appendTag(tagList);
            }
        }

        tags.setTag("Items", nbttaglist);
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return items[slot];
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        ItemStack itemstack = getStackInSlot(i);

        if (itemstack != null) {
            if (itemstack.stackSize <= count) {
                setInventorySlotContents(i, null);
            } else {
                itemstack = itemstack.splitStack(count);

            }
        }

        return itemstack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        items[slot] = stack;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public void clear() {
        for (int i = 0; i < items.length; i++) {
            items[i] = null;
        }
    }

    public boolean addInventorySlotContents(int i, ItemStack itemstack) {
        if (getStackInSlot(i) != null) {

            if (getStackInSlot(i).isItemEqual(itemstack)) {
                getStackInSlot(i).stackSize += itemstack.stackSize;
            }
            if (getStackInSlot(i).stackSize > getInventoryStackLimit()) {
                return false;
            }
        } else {
            setInventorySlotContents(i, itemstack);
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return items[index];
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
        return "EssenceTransfuser";
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(getCommandSenderName());
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        int[] slots;
        switch (side) {
        case DOWN:
            slots = new int[] { 3 };
            break;
        case UP:
            slots = new int[] { 1 };
            break;
        default:
            slots = new int[] { 0, 2 };
            break;
        }
        return slots;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        for (int i : getSlotsForFace(direction)) {
            if (index == i) {
                if (isItemValidForSlot(index, itemStackIn)) {
                    if ((getStackInSlot(0) != null && getStackInSlot(0).isItemEqual(itemStackIn) && getStackInSlot(0).stackSize + itemStackIn.stackSize <= getStackInSlot(0).getMaxStackSize()) || (getStackInSlot(0) == null)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        for (int i : getSlotsForFace(direction)) {
            if (index == i) {
                return true;
            }

        }
        return false;
    }

}

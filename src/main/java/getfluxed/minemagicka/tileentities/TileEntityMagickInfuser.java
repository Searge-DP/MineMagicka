package getfluxed.minemagicka.tileentities;

import fluxedCore.tileEntities.TileEntityInventory;
import getfluxed.minemagicka.api.RecipeRegistry;
import getfluxed.minemagicka.api.recipes.RecipeMagickInfusion;
import getfluxed.minemagicka.blocks.BlockMagickInfuser;
import getfluxed.minemagicka.blocks.MMBlocks;
import getfluxed.minemagicka.network.PacketHandler;
import getfluxed.minemagicka.network.messages.tiles.MessageMagickInfuser;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class TileEntityMagickInfuser extends TileEntityInventory implements IInventory, ITickable {

    public int infuserTimer = 40;
    public int infuserTimerMax = 40;
    public int currentMagick = 0;
    public int maxMagick = 8000;

    public TileEntityMagickInfuser() {
        super(2);
    }

    public void update() {
        boolean working = infuserTimer < 40;
        if (working && worldObj.getBlockState(getPos()).equals(MMBlocks.magickInfuser.getBlockState())) {
            BlockMagickInfuser.setState(working, this.worldObj, this.pos);
        } else
            BlockMagickInfuser.setState(working, this.worldObj, this.pos);
        boolean dirty = false;
        if (getStackInSlot(0) != null && getStackInSlot(0).getItem() != null) {
            RecipeMagickInfusion recipe = RecipeRegistry.getInfusionRecipe(getStackInSlot(0), currentMagick);
            if (recipe != null) {
                if ((getStackInSlot(1) != null && getStackInSlot(1).isItemEqual(recipe.getOutput())) || getStackInSlot(1) == null) {
                    infuserTimer--;
                    dirty = true;
                    if (infuserTimer <= 0) {
                        if (addInventorySlotContents(1, recipe.getOutput())) {
                            getStackInSlot(0).stackSize--;
                            if (getStackInSlot(0).stackSize <= 0) {
                                setInventorySlotContents(0, null);
                            }
                            infuserTimer = 40;
                            currentMagick -= recipe.getLiquidUsed();
                            dirty = true;
                            setInventorySlotContents(1, recipe.onCraft(worldObj, getStackInSlot(1), getPos()));
                        }
                    }
                }
            } else {
                infuserTimer = 40;
            }
        }
        if (dirty) {
            // markDirty();
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
    public void markDirty() {
        super.markDirty();
        PacketHandler.INSTANCE.sendToAllAround(new MessageMagickInfuser(this), new TargetPoint(worldObj.provider.getDimensionId(), pos.getX(), pos.getY(), pos.getZ(), 128D));

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        readInventoryFromNBT(nbt);
        this.infuserTimer = nbt.getInteger("infuseTimer");
        this.currentMagick = nbt.getInteger("currentMagick");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        writeInventoryToNBT(tag);
        tag.setInteger("infuseTimer", infuserTimer);
        tag.setInteger("currentMagick", currentMagick);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isItemValidForSlot(int slotNumber, ItemStack stack) {
        return true;
    }

    public int getField(int id) {
        switch (id) {
        case 0:
            return this.currentMagick;
        case 1:
            return this.maxMagick;
        case 2:
            return this.infuserTimer;
        case 3:
            return this.infuserTimerMax;
        default:
            return 0;
        }
    }

    public void setField(int id, int value) {
        switch (id) {
        case 0:
            this.currentMagick = value;
            break;
        case 1:
            this.maxMagick = value;
            break;
        case 2:
            this.infuserTimer = value;
            break;
        case 3:
            this.infuserTimerMax = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    /**
     * Gets the name of this command sender (usually username, but possibly
     * "Rcon")
     */
    public String getCommandSenderName() {
        return "container.magick.infuser";
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

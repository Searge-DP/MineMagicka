package getfluxed.minemagicka.tileentities;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import fluxedCore.tileEntities.TileEntityInventory;
import getfluxed.minemagicka.api.RecipeRegistry;
import getfluxed.minemagicka.api.recipes.RecipeMagickInfusion;
import getfluxed.minemagicka.network.PacketHandler;
import getfluxed.minemagicka.network.messages.tiles.MessageMagickInfuser;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityMagickInfuser extends TileEntityInventory {

    public int infuserTimer = 40;
    public int infuserTimerMax = 40;
    public int currentMagick = 0;
    public int maxMagick = 8000;

    public TileEntityMagickInfuser() {
        super(2);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
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
                            }
                        }
                    }
                } else {
                    infuserTimer = 40;
                }
            }

            if (dirty) {
                markDirty();
            }
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
        PacketHandler.INSTANCE.sendToAllAround(new MessageMagickInfuser(this), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));

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
    public String getInventoryName() {
        return "Magick Infuser";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int slotNumber, ItemStack stack) {
        return true;
    }

}

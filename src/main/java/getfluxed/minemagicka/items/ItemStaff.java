package getfluxed.minemagicka.items;

import fluxedCore.handlers.ClientEventHandler;
import fluxedCore.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStaff extends Item {

    public ItemStaff() {
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        switch (pass) {
            case 0:
                return 0xFFFFFF;
            case 1:
                return ClientEventHandler.getColorInt();
            default:
                return 0xFFFFFF;
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean par5) {
        if (stack.getTagCompound() == null) {
            NBTHelper.initNBTTagCompound(stack);
            NBTHelper.setInteger(stack, "MMSelectedElement", 0);
        }
    }

    public int getSelectedElement(ItemStack stack) {
        return NBTHelper.getInt(stack, "MMSelectedElement");
    }
}

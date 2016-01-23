package getfluxed.minemagicka.common.items;

import fluxedCore.handlers.ClientEventHandler;
import fluxedCore.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStaff extends ModItem {

    public ItemStaff() {
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
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

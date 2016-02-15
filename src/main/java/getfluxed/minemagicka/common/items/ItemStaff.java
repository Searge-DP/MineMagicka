package getfluxed.minemagicka.common.items;

import fluxedCore.handlers.ClientEventHandler;
import fluxedCore.util.NBTHelper;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.spells.ICasterItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStaff extends ModItem implements ICasterItem {

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
    public boolean canCast(ItemStack stack, EntityPlayer player, ElementCompound comp) {
        return true;
    }

    @Override
    public boolean isActive(ItemStack stack, EntityPlayer player) {
        return true;
    }

    @Override
    public void onCast(ItemStack stack, EntityPlayer player, ElementCompound comp) {
        // NO-OP
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

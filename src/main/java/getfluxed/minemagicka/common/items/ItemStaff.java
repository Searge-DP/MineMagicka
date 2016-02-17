package getfluxed.minemagicka.common.items;

import fluxedCore.handlers.ClientEventHandler;
import fluxedCore.util.NBTHelper;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.spells.ElementProviderHelper;
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
    public boolean canCast(ItemStack stack, EntityPlayer player, ElementCompound comp) {
        return ElementProviderHelper.requestElements(player, comp, false);
    }

    @Override
    public boolean isActive(ItemStack stack, EntityPlayer player) {
        return true; // TODO: 2/16/16 implement cooldown 
    }

    @Override
    public void onCast(ItemStack stack, EntityPlayer player, ElementCompound comp) {
        ElementProviderHelper.requestElements(player, comp, true);
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        switch (pass) {
            case 1:
                return ClientEventHandler.getColorInt();
            default:
                return 0xFFFFFF;
        }
    }
}

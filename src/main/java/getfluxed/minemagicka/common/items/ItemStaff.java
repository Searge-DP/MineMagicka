package getfluxed.minemagicka.common.items;

import fluxedCore.handlers.ClientEventHandler;
import getfluxed.minemagicka.api.spells.ICasterItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemStaff extends ModItem implements ICasterItem {

    public ItemStaff() {
        this.setMaxStackSize(1);
        this.setFull3D();
    }



    @Override
    public boolean isActive(ItemStack stack, EntityPlayer player) {
        return true; // TODO: 2/16/16 implement cooldown 
    }

    @Override
    public int getPurity(ItemStack stack, EntityPlayer player) {
        return -1; // Maximum
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

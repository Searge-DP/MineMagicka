package getfluxed.minemagicka.common.items;

import fluxedCore.handlers.ClientEventHandler;
import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.spells.ElementProviderHelper;
import getfluxed.minemagicka.api.spells.ICasterItem;
import getfluxed.minemagicka.api.spells.ISpell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemStaff extends ModItem implements ICasterItem {

    public ItemStaff() {
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    @Override
    public boolean canCast(ItemStack stack, EntityPlayer player, ElementCompound comp) {
        ISpell spell = SpellRegistry.getSpellFromElements(comp);
        int purityRequired = 0;
        if (spell != null) {
            purityRequired = spell.getPurity();
        }
        return ElementProviderHelper.requestElements(player, comp, purityRequired, false);
    }

    @Override
    public boolean isActive(ItemStack stack, EntityPlayer player) {
        return true; // TODO: 2/16/16 implement cooldown 
    }

    @Override
    public void onCast(ItemStack stack, EntityPlayer player, ElementCompound comp) {
        ISpell spell = SpellRegistry.getSpellFromElements(comp);
        int purityRequired = 0;
        if (spell != null) {
            purityRequired = spell.getPurity();
        }
        ElementProviderHelper.requestElements(player, comp, purityRequired, true);
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

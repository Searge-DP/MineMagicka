package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author WireSegal
 *         Created at 3:39 PM on 2/17/16.
 */
public class ElementProviderHelper {

    public static boolean requestElements(EntityPlayer player, ElementCompound comp, boolean doit) {
        return requestElements(player, comp.getElementList().add(comp.getModifierElementList()), doit);
    }

    public static boolean requestElements(EntityPlayer player, ElementList list, boolean doit) {
        if (player.capabilities.isCreativeMode) return true;

        IInventory mainInv = player.inventory;

        int invSize = mainInv.getSizeInventory();

        for(int i = 0; i < invSize; i++) {
            ItemStack stackInSlot = mainInv.getStackInSlot(i);

            if(stackInSlot != null && stackInSlot.getItem() instanceof IElementProvider) {
                IElementProvider elementItem = (IElementProvider) stackInSlot.getItem();
                ElementList els = elementItem.getElements(player, stackInSlot);
                if(els.size() != 0) {
                    ItemStack newStack = elementItem.consumeElements(player, stackInSlot, list, doit);
                    if (newStack.stackSize <= 0)
                        mainInv.setInventorySlotContents(i, null);
                    else
                        mainInv.setInventorySlotContents(i, newStack);
                    
                    if (list.size() == 0)
                        return true;
                }
            }
        }
        return false;
    }


    public static ElementList checkElements(EntityPlayer player, ElementCompound comp) {
        return checkElements(player, comp.getElementList().add(comp.getModifierElementList()));
    }

    public static ElementList checkElements(EntityPlayer player, ElementList list) {
        if (player.capabilities.isCreativeMode) return new ElementList();

        IInventory mainInv = player.inventory;

        int invSize = mainInv.getSizeInventory();

        for(int i = 0; i < invSize; i++) {
            ItemStack stackInSlot = mainInv.getStackInSlot(i);

            if(stackInSlot != null && stackInSlot.getItem() instanceof IElementProvider) {
                IElementProvider elementItem = (IElementProvider) stackInSlot.getItem();
                ElementList els = elementItem.getElements(player, stackInSlot);
                if(els.size() != 0) {
                    elementItem.consumeElements(player, stackInSlot, list, false);
                    if (list.size() == 0)
                        return list;
                }
            }
        }
        return list;
    }

    public static ElementList getElements(EntityPlayer player) {
        IInventory mainInv = player.inventory;

        ElementList list = new ElementList();

        int invSize = mainInv.getSizeInventory();

        for(int i = 0; i < invSize; i++) {
            ItemStack stackInSlot = mainInv.getStackInSlot(i);

            if(stackInSlot != null && stackInSlot.getItem() instanceof IElementProvider) {
                IElementProvider elementItem = (IElementProvider) stackInSlot.getItem();
                ElementList els = elementItem.getElements(player, stackInSlot);
                if(els.size() != 0) {
                    list.add(elementItem.getElements(player, stackInSlot));
                }
            }
        }
        return list;
    }
}

package getfluxed.minemagicka.handlers;

import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.elements.IElement;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SpellHandler {

    public static void addElement(ItemStack stack, IElement element) {
        ElementList currentElements = getElements(stack);
        currentElements.add(element, 1);

        NBTTagCompound nbt = stack.stackTagCompound.getCompoundTag("MMTag");
        currentElements.writeToNBT(nbt);
        stack.stackTagCompound.setTag("MMTag", nbt);
    }

    public static ElementList getElements(ItemStack stack) {
        NBTTagCompound nbt = stack.stackTagCompound.getCompoundTag("MMTag");
        return (new ElementList()).readFromNBT(nbt);
    }

    public static void clearElements(ItemStack stack) {
        NBTTagCompound nbt = stack.stackTagCompound.getCompoundTag("MMTag");
        nbt.setTag("elements", new NBTTagCompound());
        stack.stackTagCompound.setTag("MMTag", nbt);
    }
}

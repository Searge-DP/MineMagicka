package getfluxed.minemagicka.common.handlers;

import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.IElement;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SpellHandler {

    public static void addElement(ItemStack stack, IElement element) {
        ElementCompound currentElements = getElements(stack);
        currentElements.add(element, 1);
        NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("MMTag");
        currentElements.writeToNBT(nbt);
        stack.getTagCompound().setTag("MMTag", nbt);
    }
    
    public static void addModifierElement(ItemStack stack, IElement element){
        ElementCompound currentElements = getElements(stack);
        currentElements.addModifier(element, 1);
        NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("MMTag");
        currentElements.writeToNBT(nbt);
        stack.getTagCompound().setTag("MMTag", nbt);
    }

    public static ElementCompound getElements(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("MMTag");
        return (new ElementCompound()).readFromNBT(nbt);
    }

    public static void clearElements(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("MMTag");
        new ElementCompound().writeToNBT(nbt);
        stack.getTagCompound().setTag("MMTag", nbt);
    }
}

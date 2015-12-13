package getfluxed.minemagicka.handlers;

import java.util.LinkedList;
import java.util.List;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.elements.IElement;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants.NBT;

public class SpellHandler {

	public static void addElement(ItemStack stack, IElement element) {
		NBTTagCompound nbt = stack.stackTagCompound.getCompoundTag("MMTag");
		NBTTagList currentElements = nbt.getTagList("MMCurrentElements", NBT.TAG_STRING);
		currentElements.appendTag(new NBTTagString(element.getUnlocalizedName()));
		nbt.setTag("MMCurrentElements", currentElements);
		stack.stackTagCompound.setTag("MMTag", nbt);
	}

	public static LinkedList<IElement> getElements(ItemStack stack) {
		LinkedList<IElement> elements = new LinkedList<IElement>();
		NBTTagCompound nbt = stack.stackTagCompound.getCompoundTag("MMTag");
		NBTTagList currentElements = nbt.getTagList("MMCurrentElements", NBT.TAG_STRING);

		for (int i = 0; i < currentElements.tagCount(); i++) {
			IElement el = ElementRegistry.getElementFromName(currentElements.getStringTagAt(i));
			elements.add(el);
		}
		return elements;
	}
	public static void clearElements(ItemStack stack){
		NBTTagCompound nbt = stack.stackTagCompound.getCompoundTag("MMTag");
		nbt.setTag("MMCurrentElements", new NBTTagList());
		stack.stackTagCompound.setTag("MMTag", nbt);
	}
}

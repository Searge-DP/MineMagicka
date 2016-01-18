package getfluxed.minemagicka.api.elements;

import net.minecraft.nbt.NBTTagCompound;

/**
 * @author WireSegal Created at 8:09 PM on 1/6/16.
 */
public class ElementCompound { // A wrapper around ElementList.
    private ElementList elements = new ElementList();
    private ElementList modifiers = new ElementList();

    public ElementCompound() {
    }

    public ElementCompound copy() {
        return (new ElementCompound()).add(this);
    }

    public int size() {
        return elements.size();
    }

    public int modifierSize() {
        return modifiers.size();
    }

    public IElement[] getElements() {
        return elements.getElements();
    }

    public ElementList getElementList() {
        return elements.copy();
    }

    public IElement[] getModifierElements() {
        return modifiers.getElements();
    }

    public ElementList getModifierElementList() {
        return modifiers.copy();
    }

    public int getAmount(IElement key) {
        return elements.getAmount(key);
    }

    public int getModifierAmount(IElement key) {
        return modifiers.getAmount(key);
    }

    public boolean reduce(IElement key, int amount) {
        return elements.reduce(key, amount);
    }

    public boolean reduceModifier(IElement key, int amount) {
        return modifiers.reduce(key, amount);
    }

    public ElementCompound remove(IElement key, int amount) {
        elements.remove(key, amount);
        return this;
    }

    public ElementCompound removeModifiers(IElement key, int amount) {
        modifiers.remove(key, amount);
        return this;
    }

    public ElementCompound remove(IElement key) {
        this.elements.remove(key);
        return this;
    }

    public ElementCompound removeModifier(IElement key) {
        this.modifiers.remove(key);
        return this;
    }

    public ElementCompound add(IElement element, int amount) {
        elements.add(element, amount);
        return this;
    }

    public ElementCompound addModifier(IElement element, int amount) {
        modifiers.add(element, amount);
        return this;
    }

    public ElementCompound merge(IElement element, int amount) {
        elements.merge(element, amount);
        return this;
    }

    public ElementCompound mergeModifier(IElement element, int amount) {
        modifiers.merge(element, amount);
        return this;
    }

    public ElementCompound add(ElementCompound in) {
        elements.add(in.getElementList());
        modifiers.add(in.getModifierElementList());
        return this;
    }

    public ElementCompound add(ElementList in) {
       return add(in, false);
    }
    public ElementCompound add(ElementList in, boolean mod) {
        if (mod)
            modifiers.add(in);
        else
            elements.add(in);
        return this;
    }

    public ElementCompound merge(ElementCompound in) {
        elements.merge(in.getElementList());
        modifiers.merge(in.getModifierElementList());
        return this;
    }

    public ElementCompound merge(ElementList in) {
        return merge(in, false);
    }
    public ElementCompound merge(ElementList in, boolean mod) {
        if (mod)
            modifiers.merge(in);
        else
            elements.merge(in);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ElementList || obj instanceof ElementCompound) {
            return elements.equals(obj);
        }
        return super.equals(obj);
    }

    public ElementCompound readFromNBT(NBTTagCompound nbttagcompound) {
        return readFromNBT(nbttagcompound, "elements");
    }

    public ElementCompound readFromNBT(NBTTagCompound nbttagcompound, String label) {
        NBTTagCompound tcomp = nbttagcompound.getCompoundTag(label);
        this.elements.readFromNBT(tcomp, "elements");
        this.modifiers.readFromNBT(tcomp, "modifiers");
        return this;
    }

    public ElementCompound writeToNBT(NBTTagCompound nbttagcompound) {
        return writeToNBT(nbttagcompound, "elements");
    }

    public ElementCompound writeToNBT(NBTTagCompound nbttagcompound, String label) {
        NBTTagCompound tcomp = new NBTTagCompound();
        elements.writeToNBT(tcomp, "elements");
        modifiers.writeToNBT(tcomp, "modifiers");
        nbttagcompound.setTag(label, tcomp);
        return this;

    }

    @Override
    public String toString() {
        return "ElementCompound [elements=" + elements + ", modifiers=" + modifiers + "]";
    }

}

package getfluxed.minemagicka.api.elements;

import getfluxed.minemagicka.api.ElementRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.LinkedHashMap;

/**
 * @author WireSegal Created at 8:09 PM on 1/6/16.
 */
public class ElementCompound extends ElementList {
    LinkedHashMap<IElement, Integer> modifiers = new LinkedHashMap<>();

    public ElementCompound() {
    }

    public ElementCompound copy() {
        return (new ElementCompound()).add(this);
    }

    public int modifierSize() {
        return modifiers.size();
    }

    public ElementList getElementList() {
        return ElementList.fromHashMap(elements);
    }

    public IElement[] getModifierElements() {
        IElement[] q = new IElement[1];
        return this.modifiers.keySet().toArray(q);
    }

    public ElementList getModifierElementList() {
        return ElementList.fromHashMap(modifiers);
    }

    public int getModifierAmount(IElement key) {
        return this.modifiers.get(key) == null ? 0 : this.modifiers.get(key);
    }

    public boolean reduceModifier(IElement key, int amount) {
        if (this.getModifierAmount(key) >= amount) {
            int am = this.getModifierAmount(key) - amount;
            this.modifiers.put(key, am);
            return true;
        }
        return false;
    }

    public ElementCompound removeModifiers(IElement key, int amount) {
        int am = this.getModifierAmount(key) - amount;
        if (am <= 0) {
            this.modifiers.remove(key);
        } else {
            this.modifiers.put(key, am);
        }

        return this;
    }

    public ElementCompound removeModifier(IElement key) {
        this.modifiers.remove(key);
        return this;
    }

    public ElementCompound addModifier(IElement element, int amount) {
        if (this.modifiers.containsKey(element)) {
            int oldamount = this.modifiers.get(element);
            amount += oldamount;
        }

        this.modifiers.put(element, amount);
        return this;
    }

    public ElementCompound mergeModifier(IElement element, int amount) {
        if (this.modifiers.containsKey(element)) {
            int oldamount = this.modifiers.get(element);
            if (amount < oldamount) {
                amount = oldamount;
            }
        }

        this.modifiers.put(element, amount);
        return this;
    }

    public ElementCompound add(ElementCompound in) {
        for (IElement a : in.getElements()) {
            this.add(a, in.getAmount(a));
        }
        for (IElement a : in.getModifierElements()) {
            this.addModifier(a, in.getModifierAmount(a));
        }
        return this;
    }

    public ElementCompound add(ElementList in) {
        return add(in, false);
    }

    public ElementCompound add(ElementList in, boolean mod) {
        if (mod)
            for (IElement a : in.getElements()) {
                this.addModifier(a, in.getAmount(a));
            }
        else
            for (IElement a : in.getElements()) {
                this.add(a, in.getAmount(a));
            }
        return this;
    }

    public ElementCompound merge(ElementCompound in) {
        for (IElement a : in.getElements()) {
            this.merge(a, in.getAmount(a));
        }
        for (IElement a : in.getModifierElements()) {
            this.mergeModifier(a, in.getModifierAmount(a));
        }
        return this;
    }

    public ElementCompound merge(ElementList in) {
        return merge(in, false);
    }

    public ElementCompound merge(ElementList in, boolean mod) {
        if (mod)
            for (IElement a : in.getElements()) {
                this.mergeModifier(a, in.getAmount(a));
            }
        else
            for (IElement a : in.getElements()) {
                this.add(a, in.getAmount(a));
            }
        return this;
    }

    public ElementCompound readFromNBT(NBTTagCompound nbttagcompound) {
        return readFromNBT(nbttagcompound, "elements");
    }

    public ElementCompound readFromNBT(NBTTagCompound nbttagcompound, String label) {
        NBTTagCompound tcomp = nbttagcompound.getCompoundTag(label);
        super.readFromNBT(tcomp, "elements");

        this.modifiers.clear();
        NBTTagList telem = tcomp.getTagList("modifiers", 10);

        for (int j = 0; j < telem.tagCount(); ++j) {
            NBTTagCompound rs = telem.getCompoundTagAt(j);
            if (rs.hasKey("name")) {
                this.addModifier(ElementRegistry.getElementFromName(rs.getString("name")), rs.getInteger("amount"));
            }
        }

        return this;
    }

    public ElementCompound writeToNBT(NBTTagCompound nbttagcompound) {
        return writeToNBT(nbttagcompound, "elements");
    }

    public ElementCompound writeToNBT(NBTTagCompound nbttagcompound, String label) {
        NBTTagCompound tcomp = new NBTTagCompound();
        super.writeToNBT(tcomp, "elements");

        NBTTagList telem = new NBTTagList();

        for (IElement el : this.getModifierElements()) {
            if (el != null) {
                NBTTagCompound f = new NBTTagCompound();
                f.setString("name", el.getUnlocalizedName());
                f.setInteger("amount", this.getModifierAmount(el));
                telem.appendTag(f);
            }
        }

        nbttagcompound.setTag("modifiers", telem);
        nbttagcompound.setTag(label, tcomp);

        return this;

    }

    @Override
    public String toString() {
        return "ElementCompound [elements=" + elements + ", modifiers=" + modifiers + "]";
    }

}

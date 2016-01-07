package getfluxed.minemagicka.api.elements;

import getfluxed.minemagicka.api.ElementRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.LinkedHashMap;

/**
 * @author WireSegal
 *         Created at 8:09 PM on 1/6/16.
 */
public class ElementList { // A shameless steal of a lot of Thaumcraft's AspectList
    private LinkedHashMap<IElement, Integer> elements = new LinkedHashMap();
    private LinkedHashMap<IElement, Integer> modifiers = new LinkedHashMap();

    public ElementList() {
    }

    public ElementList copy() {
        return (new ElementList()).add(this);
    }

    public int size() {
        return elements.size();
    }

    public int modifierSize() {
        return modifiers.size();
    }

    public IElement[] getElements() {
        IElement[] q = new IElement[1];
        return (IElement[]) this.elements.keySet().toArray(q);
    }

    public IElement[] getModifierElements() {
        IElement[] q = new IElement[1];
        return (IElement[]) this.modifiers.keySet().toArray(q);
    }

    public int getAmount(IElement key) {
        return this.elements.get(key) == null ? 0 : ((Integer) this.elements.get(key)).intValue();
    }

    public int getModifierAmount(IElement key) {
        return this.modifiers.get(key) == null ? 0 : ((Integer) this.modifiers.get(key)).intValue();
    }

    public boolean reduce(IElement key, int amount) {
        if (this.getAmount(key) >= amount) {
            int am = this.getAmount(key) - amount;
            this.elements.put(key, Integer.valueOf(am));
            return true;
        } else {
            return false;
        }
    }

    public boolean reduceModifier(IElement key, int amount) {
        if (this.getModifierAmount(key) >= amount) {
            int am = this.getModifierAmount(key) - amount;
            this.modifiers.put(key, Integer.valueOf(am));
            return true;
        } else {
            return false;
        }
    }

    public ElementList remove(IElement key, int amount) {
        int am = this.getAmount(key) - amount;
        if (am <= 0) {
            this.elements.remove(key);
        } else {
            this.elements.put(key, Integer.valueOf(am));
        }

        return this;
    }

    public ElementList removeModifiers(IElement key, int amount) {
        int am = this.getAmount(key) - amount;
        if (am <= 0) {
            this.modifiers.remove(key);
        } else {
            this.modifiers.put(key, Integer.valueOf(am));
        }

        return this;
    }

    public ElementList remove(IElement key) {
        this.elements.remove(key);
        return this;
    }

    public ElementList removeModifier(IElement key) {
        this.modifiers.remove(key);
        return this;
    }

    public ElementList add(IElement aspect, int amount) {
        if (this.elements.containsKey(aspect)) {
            int oldamount = ((Integer) this.elements.get(aspect)).intValue();
            amount += oldamount;
        }

        this.elements.put(aspect, Integer.valueOf(amount));
        return this;
    }

    public ElementList addModifier(IElement aspect, int amount) {
        if (this.modifiers.containsKey(aspect)) {
            int oldamount = ((Integer) this.modifiers.get(aspect)).intValue();
            amount += oldamount;
        }

        this.modifiers.put(aspect, Integer.valueOf(amount));
        return this;
    }

    public ElementList merge(IElement aspect, int amount) {
        if (this.elements.containsKey(aspect)) {
            int oldamount = ((Integer) this.elements.get(aspect)).intValue();
            if (amount < oldamount) {
                amount = oldamount;
            }
        }

        this.elements.put(aspect, Integer.valueOf(amount));
        return this;
    }

    public ElementList mergeModifier(IElement aspect, int amount) {
        if (this.modifiers.containsKey(aspect)) {
            int oldamount = ((Integer) this.modifiers.get(aspect)).intValue();
            if (amount < oldamount) {
                amount = oldamount;
            }
        }

        this.modifiers.put(aspect, Integer.valueOf(amount));
        return this;
    }

    public ElementList add(ElementList in) {
        IElement[] arr = this.getElements();
        IElement[] arr2 = this.getModifierElements();
        int len = arr.length;
        int len2 = arr2.length;

        for (int i = 0; i < len; ++i) {
            IElement a = arr[i];
            this.add(a, in.getAmount(a));
        }
        for (int i = 0; i < len2; ++i) {
            IElement a = arr2[i];
            this.addModifier(a, in.getAmount(a));
        }

        return this;
    }

    public ElementList merge(ElementList in) {
        IElement[] arr = this.getElements();
        IElement[] arr2 = this.getModifierElements();
        int len = arr.length;
        int len2 = arr2.length;

        for (int i = 0; i < len; ++i) {
            IElement a = arr[i];
            this.merge(a, in.getAmount(a));
        }
        for (int i = 0; i < len2; ++i) {
            IElement a = arr2[i];
            this.mergeModifier(a, in.getAmount(a));
        }

        return this;
    }

    public boolean spellMatches(ElementList spellList) {
        int elementsFound = 0;
        for (IElement el : this.getElements()) {
            if (spellList.getAmount(el) > 0)
                elementsFound++;
            if (this.getAmount(el) != spellList.getAmount(el))
                return false;
        }
        if (elementsFound != spellList.size())
            return false;
        return true;
    }

    public ElementList readFromNBT(NBTTagCompound nbttagcompound) {
        return readFromNBT(nbttagcompound, "elements");
    }

    public ElementList readFromNBT(NBTTagCompound nbttagcompound, String label) {
        this.elements.clear();
        this.modifiers.clear();
        NBTTagCompound tcomp = nbttagcompound.getCompoundTag(label);
        NBTTagList telem = tcomp.getTagList("elements", 10);
        NBTTagList tmods = tcomp.getTagList("modifiers", 10);

        for (int j = 0; j < telem.tagCount(); ++j) {
            NBTTagCompound rs = telem.getCompoundTagAt(j);
            if (rs.hasKey("name")) {
                this.add(ElementRegistry.getElementFromName(rs.getString("name")), rs.getInteger("amount"));
            }
        }
        for (int j = 0; j < tmods.tagCount(); ++j) {
            NBTTagCompound rs = tmods.getCompoundTagAt(j);
            if (rs.hasKey("name")) {
                this.addModifier(ElementRegistry.getElementFromName(rs.getString("name")), rs.getInteger("amount"));
            }
        }

        return this;
    }

    public ElementList writeToNBT(NBTTagCompound nbttagcompound) {
        return writeToNBT(nbttagcompound, "elements");
    }

    public ElementList writeToNBT(NBTTagCompound nbttagcompound, String label) {
        NBTTagCompound tcomp = new NBTTagCompound();
        NBTTagList telem = new NBTTagList();
        NBTTagList tmods = new NBTTagList();
        tcomp.setTag("elements", telem);
        tcomp.setTag("modifiers", tmods);

        IElement[] arr = this.getElements();
        IElement[] arr2 = this.getModifierElements();
        int len = arr.length;
        int len2 = arr2.length;

        for (int i = 0; i < len; ++i) {
            IElement el = arr[i];
            if (el != null) {
                NBTTagCompound f = new NBTTagCompound();
                f.setString("key", el.getUnlocalizedName());
                f.setInteger("amount", this.getAmount(el));
                telem.appendTag(f);
            }
        }
        for (int i = 0; i < len2; ++i) {
            IElement el = arr2[i];
            if (el != null) {
                NBTTagCompound f = new NBTTagCompound();
                f.setString("key", el.getUnlocalizedName());
                f.setInteger("amount", this.getAmount(el));
                tmods.appendTag(f);
            }
        }

        nbttagcompound.setTag(label, tcomp);
        return this;

    }
}

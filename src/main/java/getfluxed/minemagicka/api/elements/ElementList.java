package getfluxed.minemagicka.api.elements;

import getfluxed.minemagicka.api.ElementRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author WireSegal
 *         Created at 4:34 PM on 1/17/16.
 */
public class ElementList { // A shameless steal of a lot of Thaumcraft's AspectList.
    LinkedHashMap<IElement, Integer> elements = new LinkedHashMap<>();

    public static ElementList fromHashMap(Map<IElement, Integer> map) {
        ElementList l = new ElementList();
        l.elements = new LinkedHashMap<>(map);
        return l;
    }

    public ElementList copy() {
        return (new ElementList()).add(this);
    }

    public int size() {
        return elements.size();
    }

    public IElement[] getElements() {
        IElement[] q = new IElement[1];
        return this.elements.keySet().toArray(q);
    }

    public int getAmount(IElement key) {
        return this.elements.get(key) == null ? 0 : this.elements.get(key);
    }

    public boolean reduce(IElement key, int amount) {
        if (this.getAmount(key) >= amount) {
            int am = this.getAmount(key) - amount;
            this.elements.put(key, am);
            return true;
        }
        return false;
    }

    public ElementList remove(IElement key, int amount) {
        int am = this.getAmount(key) - amount;
        if (am <= 0) {
            this.elements.remove(key);
        } else {
            this.elements.put(key, am);
        }

        return this;
    }

    public ElementList remove(IElement key) {
        this.elements.remove(key);
        return this;
    }

    public ElementList add(IElement element, int amount) {
        if (this.elements.containsKey(element)) {
            int oldamount = this.elements.get(element);
            amount += oldamount;
        }

        this.elements.put(element, amount);
        return this;
    }

    public ElementList merge(IElement element, int amount) {
        if (this.elements.containsKey(element)) {
            int oldamount = this.elements.get(element);
            if (amount < oldamount) {
                amount = oldamount;
            }
        }

        this.elements.put(element, amount);
        return this;
    }

    public ElementList add(ElementList in) {
        for (IElement a : in.getElements()) {
            this.add(a, in.getAmount(a));
        }
        return this;
    }

    public ElementList merge(ElementList in) {
        for (IElement a : in.getElements()) {
            this.merge(a, in.getAmount(a));
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ElementList) {
            ElementList list = (ElementList) obj;
            int elementsFound = 0;
            for (IElement el : this.getElements()) {
                if (list.getAmount(el) > 0)
                    elementsFound++;
                if (this.getAmount(el) != list.getAmount(el))
                    return false;
            }
            return elementsFound == list.size();
        }
        return super.equals(obj);
    }

    public ElementList readFromNBT(NBTTagCompound nbttagcompound) {
        return readFromNBT(nbttagcompound, "elements");
    }

    public ElementList readFromNBT(NBTTagCompound nbttagcompound, String label) {
        this.elements.clear();
        NBTTagList telem = nbttagcompound.getTagList(label, 10);

        for (int j = 0; j < telem.tagCount(); ++j) {
            NBTTagCompound rs = telem.getCompoundTagAt(j);
            if (rs.hasKey("name")) {
                this.add(ElementRegistry.getElementFromName(rs.getString("name")), rs.getInteger("amount"));
            }
        }

        return this;
    }

    public ElementList writeToNBT(NBTTagCompound nbttagcompound) {
        return writeToNBT(nbttagcompound, "elements");
    }

    public ElementList writeToNBT(NBTTagCompound nbttagcompound, String label) {
        NBTTagList telem = new NBTTagList();

        for (IElement el : this.getElements()) {
            if (el != null) {
                NBTTagCompound f = new NBTTagCompound();
                f.setString("name", el.getUnlocalizedName());
                f.setInteger("amount", this.getAmount(el));
                telem.appendTag(f);
            }
        }

        nbttagcompound.setTag(label, telem);
        return this;

    }

    @Override
    public String toString() {
        return "ElementList [elements=" + elements + ", size()=" + size() + "]";
    }
}

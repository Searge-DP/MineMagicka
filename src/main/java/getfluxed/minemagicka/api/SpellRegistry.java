package getfluxed.minemagicka.api;

import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.spells.ISpell;

import java.util.*;

public class SpellRegistry {

    private static Map<Integer, ISpell> spells = new HashMap<Integer, ISpell>();
    private static int id = 0;

    public static void registerElement(ISpell spell) {
        getSpells().put(id++, spell);
    }

    public static Map<Integer, ISpell> getSpells() {
        return spells;
    }

    public static ISpell getSpellFromName(String name) {
        for (ISpell el : getSpells().values()) {
            if (el.getUnlocalizedName().equals(name)) {
                return el;
            }
        }
        return null;
    }

    public static ISpell getSpellFromElements(List<IElement> elements) {
        List<IElement> ele = new LinkedList<IElement>();
        for (IElement el : elements) {
            ele.add(el);
        }
        Collections.sort(ele, new Comparator<IElement>() {

            @Override
            public int compare(IElement el1, IElement el2) {
                return el1.getUnlocalizedName().compareTo(el2.getUnlocalizedName());
            }
        });
        for (ISpell spell : getSpells().values()) {
            List<IElement> eleSpell = new LinkedList<IElement>();
            for (IElement el : spell.getElements()) {
                eleSpell.add(el);
            }
            Collections.sort(eleSpell, new Comparator<IElement>() {

                @Override
                public int compare(IElement el1, IElement el2) {
                    return el1.getUnlocalizedName().compareTo(el2.getUnlocalizedName());
                }
            });
            if (ele.equals(eleSpell)) {
                return spell;
            }
        }
        return null;
    }
}

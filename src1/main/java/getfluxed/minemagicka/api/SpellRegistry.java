package getfluxed.minemagicka.api;

import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.ISpell;

import java.util.HashMap;
import java.util.Map;

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

    public static ISpell getSpellFromElements(ElementList elements) {
        for (ISpell spell : getSpells().values()) {
            if (elements.spellMatches(spell.getElements())) {
                return spell;
            }
        }
        return null;
    }
}

package getfluxed.minemagicka.api;

import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.spells.ISpell;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class SpellRegistry {

    private static Map<Integer, ISpell> spells = new HashMap<>();
    private static int id = 0;

    public static void registerSpell(ISpell spell) {
        getSpells().put(id++, spell);
    }

    public static Map<Integer, ISpell> getSpells() {
        return spells;
    }

    public static
    @Nullable
    ISpell getSpellFromName(String name) {
        for (ISpell el : getSpells().values()) {
            if (el.getUnlocalizedName().equals(name)) {
                return el;
            }
        }
        return null;
    }

    public static
    @Nullable
    ISpell getSpellFromElements(ElementCompound elements) {
        for (ISpell spell : getSpells().values()) {
            if (spell.spellMatches(elements)) {
                return spell;
            }
        }
        return null;
    }
}

package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public interface ISpell {

    CastingType getType();

    default String getName() {
        return StatCollector.translateToLocal("mm.spells." + getUnlocalizedName() + ".name");
    }

    String getUnlocalizedName();

    ElementList getElements();

    default boolean spellMatches(ElementCompound elements) {
        return elements.equals(getElements());
    }

    /**
     * Called when the spell is cast
     * @param world World the spell is called from
     * @param player The player casting the spell
     * @param elements The elements in the casting tool
     * @param x X co-ord where the spell is cast
     * @param y Y co-ord where the spell is cast
     * @param z Z co-ord where the spell is cast
     */
    void cast(World world, EntityPlayer player, ElementCompound elements, double x, double y, double z);

}

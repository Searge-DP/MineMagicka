package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ISpell {

    CastingType getType();

    String getName();

    String getUnlocalizedName();

    ElementList getElements();

    boolean spellMatches(ElementCompound elements);

    void cast(World world, EntityPlayer player, ElementCompound elements, double x, double y, double z);

}

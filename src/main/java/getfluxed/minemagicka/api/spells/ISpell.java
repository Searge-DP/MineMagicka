package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ISpell {

    CastingType type = null;

    String getName();

    String getUnlocalizedName();

    ElementList getElements();

    void cast(World world, EntityPlayer player, double x, double y, double z);

}

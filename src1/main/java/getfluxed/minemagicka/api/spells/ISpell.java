package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ISpell {

    public CastingType type = null;

    public String getName();

    public String getUnlocalizedName();

    public ElementList getElements();

    public void cast(World world, EntityPlayer player, double x, double y, double z);

}

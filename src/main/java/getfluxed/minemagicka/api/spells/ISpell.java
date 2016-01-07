package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.IElement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.List;

public interface ISpell {

    public CastingType type = null;

    public String getName();

    public String getUnlocalizedName();

    public List<IElement> getElements();

    public void cast(World world, EntityPlayer player, double x, double y, double z);

}

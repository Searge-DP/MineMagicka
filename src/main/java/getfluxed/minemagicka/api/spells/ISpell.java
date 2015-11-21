package getfluxed.minemagicka.api.spells;

import java.util.List;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.IElement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ISpell {
	
	public CastingType type = null;
	
	public String getName();
	public List<IElement> getElements();
	public void cast(World world, EntityPlayer player, double x, double y, double z);
	
}

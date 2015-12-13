package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.entities.spells.base.EntityBall;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class SpellBall implements ISpell{
	
	public abstract void onImpact(EntityBall ball, World world, MovingObjectPosition mop);
	public abstract void onEntityUpdate(EntityBall ball, World world);

}

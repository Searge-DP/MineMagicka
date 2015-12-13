package getfluxed.minemagicka.buffs;

import fluxedCore.buffs.Buff;
import fluxedCore.util.CoordinatePair;
import fluxedCore.util.ResourceInformation;
import getfluxed.minemagicka.reference.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class BuffBurning extends Buff {

	public BuffBurning() {
		super("burning", true, new ResourceInformation(Reference.buffLocation, new CoordinatePair(0, 0), new CoordinatePair(16, 16)));
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void onBuffTick(World world, EntityLivingBase entity, int duration, int power) {
		if (!entity.isWet() || !entity.isInWater())
			entity.setFire(1);
	}

}

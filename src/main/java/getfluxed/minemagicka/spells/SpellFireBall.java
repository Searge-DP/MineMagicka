package getfluxed.minemagicka.spells;

import java.util.Arrays;
import java.util.List;

import fluxedCore.buffs.BuffEffect;
import fluxedCore.buffs.BuffHelper;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.spells.SpellBall;
import getfluxed.minemagicka.entities.spells.base.EntityBall;
import getfluxed.minemagicka.reference.BuffReference;
import getfluxed.minemagicka.reference.ElementReference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SpellFireBall extends SpellBall {

	@Override
	public String getName() {
		return StatCollector.translateToLocal("mm.spells.fireball.name");
	}

	@Override
	public String getUnlocalizedName() {
		return "fireball";
	}

	@Override
	public List<IElement> getElements() {
		return Arrays.asList(new IElement[] { ElementReference.fire, ElementReference.earth });
	}

	@Override
	public void cast(World world, EntityPlayer player, double x, double y, double z) {
		world.spawnEntityInWorld(new EntityBall(world, this, player));
	}

	@Override
	public void onImpact(EntityBall ball, World world, MovingObjectPosition mop) {

		if (!world.isRemote && world != null && mop != null) {
			double x;
			double y;
			double z;
			switch (mop.typeOfHit) {
				case BLOCK:
					x = mop.blockX;
					y = mop.blockY;
					z = mop.blockZ;
					break;
				case ENTITY:
					x = mop.entityHit.posX;
					y = mop.entityHit.posY;
					z = mop.entityHit.posZ;

					break;
				default:
					x = 0;
					y = 0;
					z = 0;
					break;
			}
			for (EntityLivingBase ent : (List<EntityLivingBase>) world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - 3, y - 2, z - 3, x + 3, y + 2, z + 3))) {
				BuffHelper.applyToEntity(world, ent, new BuffEffect(BuffReference.burning, 80 + (world.rand.nextInt(30) + 1), 1));
			}
			world.newExplosion(ball, x, y + 1, z, 0.8f, true, false);

			ball.setDead();
		}
	
	}

	@Override
	public void onEntityUpdate(EntityBall ball, World world) {
		ball.setFire(1);
	}

}

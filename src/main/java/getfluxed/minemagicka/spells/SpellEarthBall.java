package getfluxed.minemagicka.spells;

import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.SpellBall;
import getfluxed.minemagicka.entities.spells.base.EntityBall;
import getfluxed.minemagicka.reference.ElementReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SpellEarthBall extends SpellBall {

    @Override
    public String getName() {
        return StatCollector.translateToLocal("mm.spells.earthball.name");
    }

    @Override
    public String getUnlocalizedName() {
        return "earthball";
    }

    @Override
    public ElementList getElements() {
        return (new ElementList()).add(ElementReference.earth, 1);
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
					x = mop.getBlockPos().getX();
					y = mop.getBlockPos().getY();
					z = mop.getBlockPos().getZ();
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
            world.newExplosion(ball, x, y + 1, z, world.rand.nextFloat() + 0.5f, false, true);

            ball.setDead();
        }

    }

    @Override
    public void onEntityUpdate(EntityBall ball, World world) {
        // TODO Auto-generated method stub

    }

}

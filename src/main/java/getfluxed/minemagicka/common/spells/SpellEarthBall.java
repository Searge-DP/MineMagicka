package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.EntityBall;
import getfluxed.minemagicka.api.spells.ISpellBall;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpellEarthBall implements ISpellBall {

    @Override
    public int getPurity() {
        return 0;
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
    public void onImpact(EntityBall ball, World world, MovingObjectPosition mop) {

        if (world != null && !world.isRemote && mop != null) {
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
    public int getColor(EntityBall ball, World world) {
        return 0x964B00;
    }

}

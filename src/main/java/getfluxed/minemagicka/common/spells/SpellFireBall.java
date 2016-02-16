package getfluxed.minemagicka.common.spells;

import fluxedCore.buffs.BuffEffect;
import fluxedCore.buffs.BuffHelper;
import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.EntityBall;
import getfluxed.minemagicka.api.spells.ISpellBall;
import getfluxed.minemagicka.common.reference.BuffReference;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SpellFireBall implements ISpellBall {

    @Override
    public CastingType getType() {
        return CastingType.CREATE;
    }

    @Override
    public String getName() {
        return StatCollector.translateToLocal("mm.spells.fireball.name");
    }

    @Override
    public String getUnlocalizedName() {
        return "fireball";
    }

    @Override
    public ElementList getElements() {
        return (new ElementList()).add(ElementReference.fire, 1).add(ElementReference.earth, 1);
    }

    @Override
    public boolean spellMatches(ElementCompound elements) {
        return elements.equals(getElements());
    }

    @Override
    public void cast(World world, EntityPlayer player, ElementCompound elements, double x, double y, double z) {
        world.spawnEntityInWorld(new EntityBall(world, this, elements, player));
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
            for (EntityLivingBase ent : world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.fromBounds(x - 3, y - 2, z - 3, x + 3, y + 2, z + 3))) {
                BuffHelper.applyToEntity(world, ent, new BuffEffect(BuffReference.burning, 80 + (world.rand.nextInt(30) + 1), 1));
            }
            world.newExplosion(ball, x, y + 1, z, 0.8f, true, false);

            ball.setDead();
        }

    }

    @Override
    public int getColor(EntityBall ball, World world) {
        return 0xC15D22;
    }

    @Override
    public void onEntityUpdate(EntityBall ball, World world) {
        ball.setFire(1);
    }

}

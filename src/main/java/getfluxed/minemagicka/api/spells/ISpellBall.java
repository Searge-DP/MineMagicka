package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementCompound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public interface ISpellBall extends ISpell {

    void onImpact(EntityBall ball, World world, MovingObjectPosition mop);

    default void onEntityUpdate(EntityBall ball, World world) {}

    int getColor(EntityBall ball, World world);

    @Override
    default CastingType getType() {
        return CastingType.CREATE;
    }

    @Override
    default void cast(World world, EntityPlayer player, ElementCompound elements, double x, double y, double z) {
        world.spawnEntityInWorld(new EntityBall(world, this, elements, player));
    }

}

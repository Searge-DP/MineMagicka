package getfluxed.minemagicka.api.spells;

import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public interface ISpellBall extends ISpell {

    void onImpact(EntityBall ball, World world, MovingObjectPosition mop);

    void onEntityUpdate(EntityBall ball, World world);

}

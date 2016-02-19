package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.EntityBall;
import getfluxed.minemagicka.api.spells.ISpellBall;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by Alex on 17/02/2016 at 21:03.
 */
public class SpellChickenation implements ISpellBall {

    @Override
    public int getPurity() {
        return 2;
    }

    @Override
    public void onImpact(EntityBall ball, World world, MovingObjectPosition mop) {
        Entity entity = mop.entityHit;
        if (entity != null) {
            BlockPos pos = entity.getPosition();
            EntityChicken chicken = new EntityChicken(world);
            chicken.setPosition(pos.getX(), pos.getY(), pos.getZ());
            entity.setDead();
            world.spawnEntityInWorld(chicken);
        }
        ball.setDead();
    }

    @Override
    public int getColor(EntityBall ball, World world) {
        return 0;
    }

    @Override
    public String getUnlocalizedName() {
        return "chickenation";
    }

    @Override
    public ElementList getElements() {
        return (new ElementList()).add(ElementReference.life, 1).add(ElementReference.earth, 1);
    }
}

package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.EntityBall;
import getfluxed.minemagicka.api.spells.ISpellBall;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Alex on 19/02/2016 at 11:43.
 */
public class SpellMudSlinger implements ISpellBall {
    @Override
    public void onImpact(EntityBall ball, World world, MovingObjectPosition mop) {
        int slowTime = Math.max(ball.elements.getModifierAmount(ElementReference.water), 2) * 20 + 20;
        int blindTime = Math.max(ball.elements.getModifierAmount(ElementReference.earth), 2) * 20 + 20;
        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, ball.getEntityBoundingBox().expand(3, 3, 3));
        entities.forEach(entity -> {
            entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, slowTime, 0));
            entity.addPotionEffect(new PotionEffect(Potion.blindness.id, blindTime, 0));
        });
        ball.setDead();
    }

    @Override
    public int getColor(EntityBall ball, World world) {
        return 0;
    }

    @Override
    public String getUnlocalizedName() {
        return "mudslinger";
    }

    @Override
    public int getPurity() {
        return 2;
    }

    @Override
    public ElementList getElements() {
        return (new ElementList()).add(ElementReference.water, 1).add(ElementReference.earth, 2);
    }
}

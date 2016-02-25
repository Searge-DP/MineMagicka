package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

public class SpellStopTime implements ISpell {

    @Override
    public int getPurity() {
        return 2;
    }

    @Override
    public CastingType getType() {
        return CastingType.SEAL;
    }

    @Override
    public String getUnlocalizedName() {
        return "stoptime";
    }

    @Override
    public ElementList getElements() {
        return (new ElementList()).add(ElementReference.arcane, 1).add(ElementReference.cold, 1).add(ElementReference.shield, 1);
    }

    @Override
    public void cast(World world, EntityPlayer player, ElementCompound elements, double x, double y, double z) {
        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().expand(3, 3, 3));
        int time = Math.max(elements.getModifierAmount(ElementReference.cold), 2) * 20 + 20;

        entities.stream().filter(entity -> entity != player).forEach(entity -> {
            entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, time, 1));
            if (elements.getModifierAmount(ElementReference.earth) > 0) {
                entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, time, 1));
            }
        });
    }

}

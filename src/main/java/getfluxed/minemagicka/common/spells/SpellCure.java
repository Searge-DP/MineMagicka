package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * @author WireSegal
 *         Created at 9:28 PM on 1/20/16.
 */
public class SpellCure implements ISpell {
    @Override
    public CastingType getType() {
        return CastingType.CHANGE;
    }

    @Override
    public ElementList getElements() {
        return (new ElementList()).add(ElementReference.life, 1);
    }

    @Override
    public String getUnlocalizedName() {
        return "heal";
    }

    @Override
    public void cast(World world, EntityPlayer player, ElementCompound elements, double x, double y, double z) {
        boolean curePotionEffects = elements.getModifierAmount(ElementReference.arcane) > 0;
        boolean extinguish = elements.getModifierAmount(ElementReference.water) > 0;
        boolean gracePeriod = elements.getModifierAmount(ElementReference.shield) > 0;
        float healAmount = Math.min(elements.getModifierAmount(ElementReference.life), 2) * 2F + 6F;

        player.heal(healAmount);

        if (curePotionEffects)
            player.clearActivePotions();
        if (extinguish)
            player.extinguish();
        if (gracePeriod)
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 100, 2));
    }
}

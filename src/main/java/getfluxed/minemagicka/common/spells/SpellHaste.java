package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by Alex on 17/02/2016 at 16:37.
 */
public class SpellHaste implements ISpell {
    @Override
    public CastingType getType() {
        return CastingType.CHANGE;
    }

    @Override
    public String getUnlocalizedName() {
        return "haste";
    }

    @Override
    public ElementList getElements() {
        return (new ElementList()).add(ElementReference.arcane, 1).add(ElementReference.lightning, 1);
    }

    @Override
    public void cast(World world, EntityPlayer player, ElementCompound elements, double x, double y, double z) {
        int time = Math.max(elements.getModifierElements().length, 8) * 20 + 40;
        if (elements.getModifierAmount(ElementReference.earth) > 0) {
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, time, Math.max(elements.getModifierAmount(ElementReference.earth), 3)));
        }
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, time, 0));

    }
}

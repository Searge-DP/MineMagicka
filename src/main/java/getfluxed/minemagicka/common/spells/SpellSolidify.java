package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.items.MMItems;
import getfluxed.minemagicka.common.items.magick.ItemMagickSolid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import static getfluxed.minemagicka.common.reference.ElementReference.*;

import java.util.List;

public class SpellSolidify implements ISpell {

    @Override
    public int getPurity() {
        return 0;
    }

    @Override
    public CastingType getType() {
        return CastingType.CREATE;
    }

    @Override
    public String getUnlocalizedName() {
        return "solidify";
    }

    @Override
    public ElementList getElements() {
        return new ElementList().add(earth, 1).add(arcane, 1).add(cold, 1);
    }

    @Override
    public void cast(World world, EntityPlayer player, ElementCompound elements, double x, double y, double z) { // todo have failure messages if conditions not met
        if (elements.getModifierAmount(arcane) > 0) {
            List<EntityLivingBase> entList = world.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().expand(3, 3, 3));
            for (EntityLivingBase entity : entList)
                if (entity.isEntityUndead()) {
                    makeItem(world, x, y, z, elements, arcane);
                    break;
                }
        }
        if (elements.getModifierAmount(cold) > 0) {
            if (world.getBiomeGenForCoords(player.getPosition()).isSnowyBiome()) {
                makeItem(world, x, y, z, elements, cold);
            }
        }
        if (elements.getModifierAmount(earth) > 0) {
            if (player.posY < 32) {
                makeItem(world, x, y, z, elements, earth);
            }
        }
        if (elements.getModifierAmount(fire) > 0) {
            if (player.isBurning()) {
                makeItem(world, x, y, z, elements, fire);
            }
        }
        if (elements.getModifierAmount(life) > 0) {
            List<EntityLivingBase> entList = world.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().expand(3, 3, 3));
            for (EntityLivingBase entity : entList)
                if (!entity.isEntityUndead()) {
                    makeItem(world, x, y, z, elements, arcane);
                    break;
                }
        }
        if (elements.getModifierAmount(lightning) > 0) {
            if (world.isThundering()) {
                makeItem(world, x, y, z, elements, lightning);
            }
        }
        if (elements.getModifierAmount(shield) > 0) {
            if (player.getHealth() <= 4) {
                makeItem(world, x, y, z, elements, shield);
            }
        }
        if (elements.getModifierAmount(water) > 0) {
            if (player.isWet()) {
                makeItem(world, x, y, z, elements, water);
            }
        }

    }

    private void makeItem(World world, double x, double y, double z, ElementCompound comp, IElement element) {
        ItemStack stack = ItemMagickSolid.ofElement(element.getUnlocalizedName());
        stack.stackSize = comp.getModifierAmount(element);
        world.spawnEntityInWorld(new EntityItem(world, x, y, z, stack));
    }

}

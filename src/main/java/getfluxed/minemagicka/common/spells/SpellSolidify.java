package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.items.MMItems;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class SpellSolidify implements ISpell {

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
        ElementList castList = new ElementList();
        castList.add(ElementReference.earth, 1);
        castList.add(ElementReference.water, 1);
        castList.add(ElementReference.life, 1);
        return castList;
    }

    @Override
    public void cast(World world, EntityPlayer player, ElementCompound elements, double x, double y, double z) {
        if (elements.getModifierAmount(ElementReference.arcane) > 0) {
            List<EntityLivingBase> entList = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.fromBounds(3 - player.posX, 3 - player.posY, 3 - player.posZ, 3 + player.posX, 3 + player.posY, 3 + player.posZ));
            for (EntityLivingBase base : entList) {
                if (base.isEntityUndead()) {
                    world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(MMItems.magickSolid, elements.getModifierAmount(ElementReference.arcane), ElementRegistry.getIdFromElement(ElementReference.arcane))));
                }
            }
        }
        if (elements.getModifierAmount(ElementReference.cold) > 0) {
            if (world.getBiomeGenForCoords(player.getPosition()).isSnowyBiome()) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(MMItems.magickSolid, elements.getModifierAmount(ElementReference.cold), ElementRegistry.getIdFromElement(ElementReference.cold))));

            }
        }
        if (elements.getModifierAmount(ElementReference.earth) > 0) {
            if (player.posY < 32) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(MMItems.magickSolid, elements.getModifierAmount(ElementReference.earth), ElementRegistry.getIdFromElement(ElementReference.earth))));

            }
        }
        if (elements.getModifierAmount(ElementReference.fire) > 0) {
            if (player.isBurning()) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(MMItems.magickSolid, elements.getModifierAmount(ElementReference.fire), ElementRegistry.getIdFromElement(ElementReference.fire))));

            }
        }
        if (elements.getModifierAmount(ElementReference.life) > 0) {
            List<EntityLivingBase> entList = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.fromBounds(3 - player.posX, 3 - player.posY, 3 - player.posZ, 3 + player.posX, 3 + player.posY, 3 + player.posZ));
            for (EntityLivingBase base : entList) {
                if (!base.isEntityUndead()) {
                    world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(MMItems.magickSolid, elements.getModifierAmount(ElementReference.life), ElementRegistry.getIdFromElement(ElementReference.life))));

                }
            }
        }
        if (elements.getModifierAmount(ElementReference.lightning) > 0) {
            if (world.isThundering()) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(MMItems.magickSolid, elements.getModifierAmount(ElementReference.lightning), ElementRegistry.getIdFromElement(ElementReference.lightning))));

            }
        }
        if (elements.getModifierAmount(ElementReference.shield) > 0) {
            if (player.getHealth() <= 4) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(MMItems.magickSolid, elements.getModifierAmount(ElementReference.shield), ElementRegistry.getIdFromElement(ElementReference.shield))));

            }
        }
        if (elements.getModifierAmount(ElementReference.water) > 0) {
            if (player.isWet()) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(MMItems.magickSolid, elements.getModifierAmount(ElementReference.water), ElementRegistry.getIdFromElement(ElementReference.water))));
            }
        }

    }

}

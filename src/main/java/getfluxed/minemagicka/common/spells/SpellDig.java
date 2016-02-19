package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.EntityBall;
import getfluxed.minemagicka.api.spells.ISpellBall;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Alex on 17/02/2016 at 18:17.
 */
public class SpellDig implements ISpellBall {

    @Override
    public int getPurity() {
        return 2;
    }

    @Override
    public CastingType getType() {
        return CastingType.DESTROY;
    }

    @Override
    public String getUnlocalizedName() {
        return "dig";
    }

    @Override
    public ElementList getElements() {
        return (new ElementList()).add(ElementReference.earth, 1).add(ElementReference.arcane, 1);
    }

    @Override
    public void onImpact(EntityBall ball, World world, MovingObjectPosition mop) {

        int miningLevel = ball.elements.getModifierAmount(ElementReference.cold) > 0 ? 3 : 1;

        BlockPos mpos = mop.getBlockPos();
        for (int x = mpos.getX() - 1; x <= mpos.getX() + 1; x++) {
            for (int y = mpos.getY() - 1; y <= mpos.getY() + 1; y++) {
                for (int z = mpos.getZ() - 1; z <= mpos.getZ() + 1; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    IBlockState state = world.getBlockState(pos);
                    if (state.getBlock().getHarvestLevel(state) <= miningLevel && state.getBlock().getBlockHardness(world, pos) != -1) {
                        List<ItemStack> drops = state.getBlock().getDrops(world, pos, world.getBlockState(pos), 0);
                        world.setBlockToAir(pos);
                        drops.forEach(item -> {
                            EntityItem itemEntity = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), item);
                            world.spawnEntityInWorld(itemEntity);
                        });
                    }
                }
            }
        }
        ball.setDead();
    }

    @Override
    public int getColor(EntityBall ball, World world) {
        return 0x808080;
    }
}

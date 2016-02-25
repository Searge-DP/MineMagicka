package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.EntityBall;
import getfluxed.minemagicka.api.spells.ISpellBall;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * @author WireSegal
 *         Created at 6:00 PM on 2/15/16.
 */
public class SpellHydrate implements ISpellBall {

    @Override
    public int getPurity() {
        return 0;
    }

    @Override
    public int getColor(EntityBall ball, World world) {
        return 0x0040FF;
    }

    @Override
    public void onImpact(EntityBall ball, World world, MovingObjectPosition mop) {
        int x = (int) ball.posX;
        int y = (int) ball.posY;
        int z = (int) ball.posZ;

        BlockPos pos = new BlockPos(x, y, z);

        pos = pos.down();
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock().isAir(world, pos) || state.getBlock().isReplaceable(world, pos))
            world.setBlockState(pos, Blocks.flowing_water.getDefaultState());
        else {
            pos = pos.up();
            state = world.getBlockState(pos);
            if (state.getBlock().isAir(world, pos) || state.getBlock().isReplaceable(world, pos))
                world.setBlockState(pos, Blocks.flowing_water.getDefaultState());
        }

        ball.setDead();
    }

    @Override
    public ElementList getElements() {
        return (new ElementList()).add(ElementReference.earth, 1).add(ElementReference.water, 1);
    }

    @Override
    public String getUnlocalizedName() {
        return "waterball";
    }

}

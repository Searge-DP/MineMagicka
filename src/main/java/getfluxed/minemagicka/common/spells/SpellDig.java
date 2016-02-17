package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.spells.EntityBall;
import getfluxed.minemagicka.api.spells.ISpellBall;
import getfluxed.minemagicka.common.reference.ElementReference;
import net.minecraft.block.Block;
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
    public String getUnlocalizedName() {
        return "dig";
    }

    @Override
    public ElementList getElements() {
        return (new ElementList()).add(ElementReference.earth, 1).add(ElementReference.arcane, 1);
    }

    @Override
    public void onImpact(EntityBall ball, World world, MovingObjectPosition mop) {
        BlockPos pos = mop.getBlockPos();
        IBlockState state = world.getBlockState(pos);
        List<ItemStack> drops = state.getBlock().getDrops(world, pos, world.getBlockState(pos), 0);
        world.setBlockToAir(pos);
        drops.forEach(item -> {
            EntityItem itemEntity = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), item);
            world.spawnEntityInWorld(itemEntity);
        });
        ball.setDead();
    }

    @Override
    public int getColor(EntityBall ball, World world) {
        return 0;
    }
}

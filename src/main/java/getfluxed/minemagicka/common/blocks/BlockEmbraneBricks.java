package getfluxed.minemagicka.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEmbraneBricks extends Block {

    public BlockEmbraneBricks() {
        super(Material.rock);

        setHardness(2.0f);
        setHarvestLevel("pickaxe", 2);
    }
    

}

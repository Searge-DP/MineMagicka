package getfluxed.minemagicka.common.blocks.machines;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.common.blocks.tile.machines.TileEntityEssenceTransfuser;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEssenceTransfuser extends Block implements ITileEntityProvider {

    public BlockEssenceTransfuser() {
        super(Material.iron);
        setHardness(2.0f);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(MineMagicka.INSTANCE, 2, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityEssenceTransfuser();
    }

}

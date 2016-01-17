package getfluxed.minemagicka.blocks.researchtable;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.tileentities.researchtable.TileEntityResearchTableBook;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockResearchTableBook extends BlockContainer {

    public BlockResearchTableBook() {
        super(Material.wood);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(MineMagicka.INSTANCE, 1, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityResearchTableBook();
    }

}

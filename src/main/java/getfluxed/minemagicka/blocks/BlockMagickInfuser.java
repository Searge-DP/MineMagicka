package getfluxed.minemagicka.blocks;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.items.MMItems;
import getfluxed.minemagicka.network.PacketHandler;
import getfluxed.minemagicka.network.messages.tiles.MessageMagickInfuser;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.swing.*;
import java.util.Random;

public class BlockMagickInfuser extends Block implements ITileEntityProvider {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public Icon frontOn;
    public Icon frontOff;
    public Icon other;

    public BlockMagickInfuser() {
        super(Material.iron);

        setHardness(2.0f);
        setHarvestLevel("pickaxe", 2);
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (active) {
            worldIn.setBlockState(pos, MMBlocks.magickInfuserOn.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, MMBlocks.magickInfuserOn.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        } else {
            worldIn.setBlockState(pos, MMBlocks.magickInfuser.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, MMBlocks.magickInfuser.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.markBlockForUpdate(pos);
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to
     * allow for adjustments to the IBlockstate
     */
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    /**
     * The type of render function called. 3 for standard block models, 2 for
     * TESR's, 1 for liquids, -1 is no render
     */
    public int getRenderType() {
        return 3;
    }

    /**
     * Possibly modify the given BlockState before rendering it on an Entity
     * (Minecarts, Endermen, ...)
     */
    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{FACING});
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntityMagickInfuser inf = (TileEntityMagickInfuser) world.getTileEntity(pos);
            if (!player.isSneaking()) {
                if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() != null) {
                    if (player.getCurrentEquippedItem().isItemEqual(new ItemStack(MMItems.bucketMagickLiquid))) {
                        if (inf.currentMagick + 1000 <= inf.maxMagick) {
                            inf.currentMagick += 1000;
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
                            PacketHandler.INSTANCE.sendToAllAround(new MessageMagickInfuser(inf), new TargetPoint(world.provider.getDimensionId(), pos.getX(), pos.getY(), pos.getZ(), 128D));
                            return true;
                        }
                    }
                }
                player.openGui(MineMagicka.INSTANCE, 0, world, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityMagickInfuser();
    }

}

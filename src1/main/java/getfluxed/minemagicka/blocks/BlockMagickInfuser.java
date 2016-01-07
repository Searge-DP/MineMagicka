package getfluxed.minemagicka.blocks;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.items.MMItems;
import getfluxed.minemagicka.network.PacketHandler;
import getfluxed.minemagicka.network.messages.tiles.MessageMagickInfuser;
import getfluxed.minemagicka.reference.Reference;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockMagickInfuser extends Block implements ITileEntityProvider {

    public IIcon frontOn;
    public IIcon frontOff;
    public IIcon other;

    public BlockMagickInfuser() {
        super(Material.iron);

        setHardness(2.0f);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public void registerBlockIcons(IIconRegister icon) {

        this.frontOff = icon.registerIcon(Reference.modid + ":infuser_front_off");
        this.frontOn = icon.registerIcon(Reference.modid + ":infuser_front_on");
        this.other = icon.registerIcon(Reference.modid + ":bricks");
        this.blockIcon = other;
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityMagickInfuser inf = (TileEntityMagickInfuser) world.getTileEntity(x, y, z);
        boolean on = inf.infuserTimer != inf.infuserTimerMax;
        IIcon front = null;
        if (on) {
            front = frontOn;
        } else {
            front = frontOff;
        }
        return side == 1 ? this.other : (side == 0 ? this.other : (side != world.getBlockMetadata(x, y, z) ? this.other : front));

    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        switch (side) {
            case 3:
                return frontOff;
            default:
                return other;
        }
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        world.markBlockForUpdate(x, y, z);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        int l = MathHelper.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 1) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }

        if (l == 3) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }

        if (p_149689_6_.hasDisplayName()) {
            ((TileEntityFurnace) p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_)).func_145951_a(p_149689_6_.getDisplayName());
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntityMagickInfuser inf = (TileEntityMagickInfuser) world.getTileEntity(x, y, z);
            if (!player.isSneaking()) {
                if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() != null) {
                    if (player.getCurrentEquippedItem().isItemEqual(new ItemStack(MMItems.bucketMagickLiquid))) {
                        if (inf.currentMagick + 1000 <= inf.maxMagick) {
                            inf.currentMagick += 1000;
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
                            PacketHandler.INSTANCE.sendToAllAround(new MessageMagickInfuser(inf), new TargetPoint(world.provider.dimensionId, x, y, z, 128D));
                            return true;
                        }
                    }
                }
                player.openGui(MineMagicka.INSTANCE, 0, world, x, y, z);
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityMagickInfuser();
    }

}

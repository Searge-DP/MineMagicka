package getfluxed.minemagicka.blocks;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.items.MMItems;
import getfluxed.minemagicka.network.PacketHandler;
import getfluxed.minemagicka.network.messages.tiles.MessageMagickInfuser;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMagickInfuser extends Block implements ITileEntityProvider {

	public IIcon frontOn;
	public IIcon frontOff;
	public IIcon other;
	
	public BlockMagickInfuser() {
		super(Material.iron);
	}
	
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return other;
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

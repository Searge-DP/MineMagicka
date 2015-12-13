package getfluxed.minemagicka.network.messages.tiles;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class MessageMagickInfuser implements IMessage, IMessageHandler<MessageMagickInfuser, IMessage> {

	private int x;
	private int y;
	private int z;
	private int infuserTime;
	private int infuserTimeMax;
	private int magick;
	private ItemStack input;
	private ItemStack output;

	public MessageMagickInfuser() {

	}

	public MessageMagickInfuser(TileEntityMagickInfuser tile) {

		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
		this.infuserTime = tile.infuserTimer;
		this.infuserTimeMax = tile.infuserTimerMax;
		this.magick = tile.currentMagick;
		this.input = tile.getStackInSlot(0);
		this.output = tile.getStackInSlot(1);
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

		this.infuserTime = buf.readInt();
		this.infuserTimeMax = buf.readInt();
		this.magick = buf.readInt();
		this.input = ByteBufUtils.readItemStack(buf);
		this.output = ByteBufUtils.readItemStack(buf);

	}

	@Override
	public void toBytes(ByteBuf buf) {

		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);

		buf.writeInt(infuserTime);
		buf.writeInt(infuserTimeMax);
		buf.writeInt(magick);
		ByteBufUtils.writeItemStack(buf, input);
		ByteBufUtils.writeItemStack(buf, output);
		
	}

	@Override
	public IMessage onMessage(MessageMagickInfuser message, MessageContext ctx) {

		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

		if (tileEntity instanceof TileEntityMagickInfuser) {

			((TileEntityMagickInfuser) tileEntity).infuserTimer = message.infuserTime;
			((TileEntityMagickInfuser) tileEntity).infuserTimerMax = message.infuserTimeMax;
			((TileEntityMagickInfuser) tileEntity).currentMagick = message.magick;
			((TileEntityMagickInfuser) tileEntity).setInventorySlotContents(0, message.input);
			((TileEntityMagickInfuser) tileEntity).setInventorySlotContents(1, message.output);

		}

		return null;

	}

}

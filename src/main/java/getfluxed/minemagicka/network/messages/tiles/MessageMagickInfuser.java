package getfluxed.minemagicka.network.messages.tiles;

import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageMagickInfuser implements IMessage, IMessageHandler<MessageMagickInfuser, IMessage> {

    private BlockPos pos;
    private int infuserTime;
    private int infuserTimeMax;
    private int magick;
    private ItemStack input;
    private ItemStack output;

    public MessageMagickInfuser() {

    }

    public MessageMagickInfuser(TileEntityMagickInfuser tile) {

        this.pos = tile.getPos();
        this.infuserTime = tile.infuserTimer;
        this.infuserTimeMax = tile.infuserTimerMax;
        this.magick = tile.currentMagick;
        this.input = tile.getStackInSlot(0);
        this.output = tile.getStackInSlot(1);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.infuserTime = buf.readInt();
        this.infuserTimeMax = buf.readInt();
        this.magick = buf.readInt();
        this.input = ByteBufUtils.readItemStack(buf);
        this.output = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());

        buf.writeInt(infuserTime);
        buf.writeInt(infuserTimeMax);
        buf.writeInt(magick);
        ByteBufUtils.writeItemStack(buf, input);
        ByteBufUtils.writeItemStack(buf, output);

    }

    @Override
    public IMessage onMessage(MessageMagickInfuser message, MessageContext ctx) {

        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.pos);

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

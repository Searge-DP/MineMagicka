package getfluxed.minemagicka.network.messages.tiles;

import getfluxed.minemagicka.network.messages.PlugNPlayMessage;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageMagickInfuser extends PlugNPlayMessage<MessageMagickInfuser> {

    public BlockPos pos;
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
    public IMessage handleMessage(MessageContext ctx) {

        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(pos);

        if (tileEntity instanceof TileEntityMagickInfuser) {

            ((TileEntityMagickInfuser) tileEntity).infuserTimer = infuserTime;
            ((TileEntityMagickInfuser) tileEntity).infuserTimerMax = infuserTimeMax;
            ((TileEntityMagickInfuser) tileEntity).currentMagick = magick;
            ((TileEntityMagickInfuser) tileEntity).setInventorySlotContents(0, input);
            ((TileEntityMagickInfuser) tileEntity).setInventorySlotContents(1, output);

        }

        return null;

    }

}

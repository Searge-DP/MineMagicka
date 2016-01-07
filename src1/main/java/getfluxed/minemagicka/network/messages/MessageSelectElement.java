package getfluxed.minemagicka.network.messages;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fluxedCore.util.NBTHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MessageSelectElement implements IMessage, IMessageHandler<MessageSelectElement, IMessage> {
    private int selectedID;

    public MessageSelectElement() {
    }

    public MessageSelectElement(int selectedID) {
        this.selectedID = selectedID;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(selectedID);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.selectedID = buf.readInt();
    }

    @Override
    public IMessage onMessage(MessageSelectElement message, MessageContext ctx) {
        EntityPlayer entity = ctx.getServerHandler().playerEntity;
        if (entity != null) {
            ItemStack stack = entity.inventory.getCurrentItem();
            if (stack != null) {
                NBTHelper.initNBTTagCompound(stack);
                NBTHelper.setInteger(stack, "MMSelectedElement", message.selectedID);
            }
        }

        return null;
    }
}

package getfluxed.minemagicka.network.messages.spells;

import getfluxed.minemagicka.handlers.SpellHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageClearElements implements IMessage, IMessageHandler<MessageClearElements, IMessage> {
    public MessageClearElements() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public IMessage onMessage(MessageClearElements message, MessageContext ctx) {
        EntityPlayer entity = ctx.getServerHandler().playerEntity;
        if (entity != null) {
            ItemStack stack = entity.inventory.getCurrentItem();
            if (stack != null)
                SpellHandler.clearElements(stack);
        }

        return null;
    }
}

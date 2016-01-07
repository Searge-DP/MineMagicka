package getfluxed.minemagicka.network.messages.spells;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.handlers.SpellHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageAddElement implements IMessage, IMessageHandler<MessageAddElement, IMessage> {
    private String element;

    public MessageAddElement() {
    }

    public MessageAddElement(IElement element) {
        this.element = element.getUnlocalizedName();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, element);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.element = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public IMessage onMessage(MessageAddElement message, MessageContext ctx) {
        EntityPlayer entity = ctx.getServerHandler().playerEntity;
        if (entity != null) {
            ItemStack stack = entity.inventory.getCurrentItem();
            if (stack != null)
                SpellHandler.addElement(stack, ElementRegistry.getElementFromName(message.element));
        }

        return null;
    }
}

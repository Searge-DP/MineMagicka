package getfluxed.minemagicka.common.network.messages.spells;

import getfluxed.minemagicka.common.handlers.SpellHandler;
import getfluxed.minemagicka.common.network.messages.PlugNPlayMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

@SuppressWarnings("serial")
public class MessageClearElements extends PlugNPlayMessage<MessageClearElements> {
    public MessageClearElements() {
    }

    @Override
    public IMessage handleMessage(MessageContext ctx) {
        EntityPlayer entity = ctx.getServerHandler().playerEntity;
        if (entity != null) {
            ItemStack stack = entity.inventory.getCurrentItem();
            if (stack != null)
                SpellHandler.clearElements(stack);
        }

        return null;
    }
}

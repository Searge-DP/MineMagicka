package getfluxed.minemagicka.network.messages.spells;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.handlers.SpellHandler;
import getfluxed.minemagicka.network.messages.PlugNPlayMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageAddElement extends PlugNPlayMessage<MessageAddElement> {
    public String element;

    public MessageAddElement() {
    }

    public MessageAddElement(IElement element) {
        this.element = element.getUnlocalizedName();
    }

    @Override
    public IMessage handleMessage(MessageContext ctx) {
        EntityPlayer entity = ctx.getServerHandler().playerEntity;
        if (entity != null) {
            ItemStack stack = entity.inventory.getCurrentItem();
            if (stack != null)
                SpellHandler.addElement(stack, ElementRegistry.getElementFromName(element));
        }

        return null;
    }
}

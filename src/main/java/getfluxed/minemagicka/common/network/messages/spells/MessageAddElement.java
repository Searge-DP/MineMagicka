package getfluxed.minemagicka.common.network.messages.spells;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.events.SelectElementEvent;
import getfluxed.minemagicka.common.handlers.SpellHandler;
import getfluxed.minemagicka.common.network.messages.PlugNPlayMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
@SuppressWarnings("serial")
public class MessageAddElement extends PlugNPlayMessage<MessageAddElement> {
    public String element;
    public boolean modifier;
    public MessageAddElement() {
    }

    public MessageAddElement(IElement element, boolean modifier) {
        this.element = element.getUnlocalizedName();
        this.modifier = modifier;
    }

    @Override
    public IMessage handleMessage(MessageContext ctx) {
        EntityPlayer entity = ctx.getServerHandler().playerEntity;
        if (entity != null) {
            ItemStack stack = entity.inventory.getCurrentItem();
            if (stack != null) {
                if (!MinecraftForge.EVENT_BUS.post(new SelectElementEvent(entity, stack, SpellHandler.getElements(stack), ElementRegistry.getElementFromName(element), false))) {
                    if (modifier)
                        SpellHandler.addModifierElement(stack, ElementRegistry.getElementFromName(element));
                    else
                        SpellHandler.addElement(stack, ElementRegistry.getElementFromName(element));
                }

                
            }
        }

        return null;
    }
}

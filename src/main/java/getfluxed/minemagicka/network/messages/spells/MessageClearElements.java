package getfluxed.minemagicka.network.messages.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import getfluxed.minemagicka.handlers.SpellHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

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

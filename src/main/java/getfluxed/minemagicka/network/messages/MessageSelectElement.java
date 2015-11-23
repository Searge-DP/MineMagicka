package getfluxed.minemagicka.network.messages;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fluxedCore.FluxedCore;
import fluxedCore.util.NBTHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MessageSelectElement implements IMessage, IMessageHandler<MessageSelectElement, IMessage> {
	public MessageSelectElement() {
	}

	private int selectedID;
	private int entityID;

	public MessageSelectElement(EntityPlayer target, int selectedID) {
		this.entityID = target.getEntityId();
		this.selectedID = selectedID;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(entityID);
		buf.writeInt(selectedID);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityID = buf.readInt();
		this.entityID = buf.readInt();
	}

	@Override
	public IMessage onMessage(MessageSelectElement message, MessageContext ctx) {
		World world = FluxedCore.proxy.getWorld();
		EntityPlayer entity = (EntityPlayer) world.getEntityByID(message.entityID);
		if (entity != null) {
			NBTHelper.setInteger(entity.inventory.getCurrentItem(), "MMSelectedElement", message.selectedID);
		}
		return null;
	}
}

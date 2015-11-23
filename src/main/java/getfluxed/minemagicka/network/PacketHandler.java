package getfluxed.minemagicka.network;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import getfluxed.minemagicka.network.messages.MessageSelectElement;
import getfluxed.minemagicka.reference.Reference;

public class PacketHandler {

	public static SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(Reference.modid);
	public static int ID = 0;
	public static void preInit() {
		INSTANCE.registerMessage(MessageSelectElement.class, MessageSelectElement.class, ID++, Side.SERVER);
	}

}

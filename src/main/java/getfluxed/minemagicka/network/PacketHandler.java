package getfluxed.minemagicka.network;

import getfluxed.minemagicka.network.messages.MessageSelectElement;
import getfluxed.minemagicka.network.messages.spells.MessageAddElement;
import getfluxed.minemagicka.network.messages.spells.MessageCastSpell;
import getfluxed.minemagicka.network.messages.spells.MessageClearElements;
import getfluxed.minemagicka.network.messages.tiles.MessageMagickInfuser;
import getfluxed.minemagicka.reference.Reference;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    public static SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(Reference.modid);
    public static int ID = 0;

    public static void preInit() {
        INSTANCE.registerMessage(MessageSelectElement.class, MessageSelectElement.class, ID++, Side.SERVER);
        INSTANCE.registerMessage(MessageAddElement.class, MessageAddElement.class, ID++, Side.SERVER);
        INSTANCE.registerMessage(MessageCastSpell.class, MessageCastSpell.class, ID++, Side.SERVER);
        INSTANCE.registerMessage(MessageClearElements.class, MessageClearElements.class, ID++, Side.SERVER);
        INSTANCE.registerMessage(MessageMagickInfuser.class, MessageMagickInfuser.class, ID++, Side.CLIENT);

    }

}

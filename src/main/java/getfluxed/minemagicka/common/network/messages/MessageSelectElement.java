package getfluxed.minemagicka.common.network.messages;

import fluxedCore.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSelectElement extends PlugNPlayMessage<MessageSelectElement> {
    public int selectedID;

    public MessageSelectElement() {
    }

    public MessageSelectElement(int selectedID) {
        this.selectedID = selectedID;
    }

    @Override
    public IMessage handleMessage(MessageContext ctx) {
        EntityPlayer entity = ctx.getServerHandler().playerEntity;
        if (entity != null) {
            ItemStack stack = entity.inventory.getCurrentItem();
            if (stack != null) {
                NBTHelper.initNBTTagCompound(stack);
                NBTHelper.setInteger(stack, "MMSelectedElement", selectedID);
            }
        }

        return null;
    }
}

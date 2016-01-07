package getfluxed.minemagicka.network.messages.spells;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.spells.ISpell;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MessageCastSpell implements IMessage, IMessageHandler<MessageCastSpell, IMessage> {
    private String spell;
    private double x, y, z;
    public MessageCastSpell() {
    }

    public MessageCastSpell(ISpell spell, double x, double y, double z) {
        this.spell = spell.getUnlocalizedName();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, spell);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.spell = ByteBufUtils.readUTF8String(buf);
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();

    }

    @Override
    public IMessage onMessage(MessageCastSpell message, MessageContext ctx) {
        EntityPlayer entity = ctx.getServerHandler().playerEntity;
        World world = ctx.getServerHandler().playerEntity.worldObj;
        if (entity != null) {
            SpellRegistry.getSpellFromName(message.spell).cast(world, entity, message.x, message.y, message.z);
        }

        return null;
    }
}

package getfluxed.minemagicka.common.network.messages.spells;

import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.network.messages.PlugNPlayMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

@SuppressWarnings("serial")
public class MessageCastSpell extends PlugNPlayMessage<MessageCastSpell> {
    public String spellKey;
    public double x;
    public double y;
    public double z;
    public ElementCompound elements;

    public MessageCastSpell() {

    }

    public MessageCastSpell(ISpell spell, double x, double y, double z, ElementCompound elements) {
        this.spellKey = spell.getUnlocalizedName();
        this.x = x;
        this.y = y;
        this.z = z;
        this.elements = elements;
    }



    @Override
    public IMessage handleMessage(MessageContext ctx) {
        EntityPlayer entity = ctx.getServerHandler().playerEntity;
        World world = ctx.getServerHandler().playerEntity.worldObj;
        if (entity != null) {
            ISpell spell = SpellRegistry.getSpellFromName(spellKey);
            if (spell != null) spell.cast(world, entity, elements, x, y, z);
        }

        return null;
    }
}

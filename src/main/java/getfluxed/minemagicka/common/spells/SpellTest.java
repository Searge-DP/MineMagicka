package getfluxed.minemagicka.common.spells;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.casting.CastingType;
import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.spells.ISpell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * @author WireSegal
 *         Created at 8:30 PM on 1/18/16.
 */
public class SpellTest implements ISpell {

    @Override
    public CastingType getType() {
        return CastingType.WEAVE;
    }

    @Override
    public String getUnlocalizedName() {
        return "test";
    }

    @Override
    public ElementList getElements() {
        ElementList castList = new ElementList();
        for (IElement el : ElementRegistry.getElementList()) {
            castList.add(el, 1);
        }
        return castList;
    }

    @Override
    public String getName() {
        return "Test spell";
    }

    @Override
    public void cast(World world, EntityPlayer player, ElementCompound elements, double x, double y, double z) {
        player.addChatMessage(new ChatComponentText(elements.toString()));
    }
}

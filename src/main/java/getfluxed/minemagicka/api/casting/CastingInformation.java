package getfluxed.minemagicka.api.casting;

import getfluxed.minemagicka.api.elements.ElementList;
import getfluxed.minemagicka.api.elements.IElement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CastingInformation {

    public ElementList elements;

    public CastingInformation(ElementList elements) {
        this.elements = elements;
    }

    public IElement getMainElement() {
        //TODO you can never have more than 4 different elemenets without adding shield, which then defaults the casting type to shield
        // when applying something with earth, it affects the entities around the hit point
        return null;
    }

    public void castSpell(World world, EntityPlayer player) {

    }
}

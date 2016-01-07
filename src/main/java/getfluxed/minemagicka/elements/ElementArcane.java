package getfluxed.minemagicka.elements;

import getfluxed.minemagicka.api.elements.ElementBase;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class ElementArcane extends ElementBase {

    @Override
    public String getName() {
        return StatCollector.translateToLocal("mm.element.arcane.name");
    }

    @Override
    public String getUnlocalizedName() {
        return "arcane";
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal("mm.element.arcane.description");
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(Reference.modid, "textures/gui/elements/arcane.png");
    }

    @Override
    public boolean isPrimary() {
        return true;
    }

    @Override
    public List<IElement> getOpposites() {
        List<IElement> list = new ArrayList<IElement>();
        return list;
    }

    @Override
    public Pair<IElement, IElement> getCombination() {
        return null;
    }

}

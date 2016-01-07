package getfluxed.minemagicka.elements;

import getfluxed.minemagicka.api.elements.ElementBase;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class ElementShield extends ElementBase {

    @Override
    public String getName() {
        return StatCollector.translateToLocal("mm.element.shield.name");
    }

    @Override
    public String getUnlocalizedName() {
        return "shield";
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal("mm.element.shield.description");
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(Reference.modid, "textures/gui/elements/shield.png");
    }

    @Override
    public int getColor() {
        return 0xFFFFFF; // TODO: 1/7/16 Use colorization instead of texture colors
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

package getfluxed.minemagicka.common.reference;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.elements.ElementBase;
import getfluxed.minemagicka.api.elements.IElement;
import net.minecraft.util.ResourceLocation;

public class ElementReference {

    public static IElement water = new ElementBase("hydros", 0x5c7dae, new ResourceLocation(Reference.modid, "textures/gui/elements/water.png"));
    public static IElement life = new ElementBase("zoia", 0x80ec51, new ResourceLocation(Reference.modid, "textures/gui/elements/life.png"));
    public static IElement shield = new ElementBase("alexia", 0xfff5a3, new ResourceLocation(Reference.modid, "textures/gui/elements/shield.png"));
    public static IElement cold = new ElementBase("krio", 0x8ee8e0, new ResourceLocation(Reference.modid, "textures/gui/elements/cold.png"));

    public static IElement lightning = new ElementBase("keranos", 0x8f2d8d, new ResourceLocation(Reference.modid, "textures/gui/elements/lightning.png"));
    public static IElement arcane = new ElementBase("magikos", 0x912419, new ResourceLocation(Reference.modid, "textures/gui/elements/arcane.png"));
    public static IElement earth = new ElementBase("gio", 0x663521, new ResourceLocation(Reference.modid, "textures/gui/elements/earth.png"));
    public static IElement fire = new ElementBase("floya", 0xd8643b, new ResourceLocation(Reference.modid, "textures/gui/elements/fire.png"));

    public static void preInit() {
        ElementRegistry.registerElement(water);
        ElementRegistry.registerElement(life);
        ElementRegistry.registerElement(shield);
        ElementRegistry.registerElement(cold);

        ElementRegistry.registerElement(lightning);
        ElementRegistry.registerElement(arcane);
        ElementRegistry.registerElement(earth);
        ElementRegistry.registerElement(fire);

    }

}

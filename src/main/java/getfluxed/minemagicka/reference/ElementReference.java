package getfluxed.minemagicka.reference;

import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.elements.*;

public class ElementReference {

    public static IElement water = new ElementWater();
    public static IElement life = new ElementLife();
    public static IElement shield = new ElementShield();
    public static IElement cold = new ElementCold();

    public static IElement lightning = new ElementLightning();
    public static IElement arcane = new ElementArcane();
    public static IElement earth = new ElementEarth();
    public static IElement fire = new ElementFire();

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

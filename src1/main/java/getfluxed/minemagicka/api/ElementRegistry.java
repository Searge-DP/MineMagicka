package getfluxed.minemagicka.api;

import getfluxed.minemagicka.api.elements.IElement;

import java.util.HashMap;
import java.util.Map;

public class ElementRegistry {

    private static Map<Integer, IElement> elements = new HashMap<Integer, IElement>();
    private static int id = 0;

    public static void registerElement(IElement element) {
        getElements().put(id++, element);
    }

    public static Map<Integer, IElement> getElements() {
        return elements;
    }

    public static IElement getElementFromName(String name) {
        for (IElement el : elements.values()) {
            if (el.getUnlocalizedName().equals(name)) {
                return el;
            }
        }
        return null;
    }
}

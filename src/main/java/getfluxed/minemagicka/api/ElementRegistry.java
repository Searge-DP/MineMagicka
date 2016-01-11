package getfluxed.minemagicka.api;

import java.util.LinkedHashMap;
import java.util.Map;

import getfluxed.minemagicka.api.elements.IElement;

public class ElementRegistry {

    private static Map<String, IElement> elements = new LinkedHashMap<String, IElement>();

    public static void registerElement(String key, IElement element) {
        elements.put(key, element);
    }
    public static void registerElement(IElement element) {
        registerElement(element.getUnlocalizedName(), element);
    }

    public static Map<String, IElement> getElements() {
        return elements;
    }

    public static IElement[] getElementList() {
        IElement[] q = new IElement[1];
        return (IElement[]) elements.values().toArray(q);
    }

    public static IElement getElementFromName(String name) {
        return elements.get(name);
    }
}

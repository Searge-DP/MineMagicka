package getfluxed.minemagicka.api;

import getfluxed.minemagicka.api.elements.IElement;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ElementRegistry {

    private static Map<String, IElement> elements = new LinkedHashMap<>();

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
        return elements.values().toArray(q);
    }

    public static IElement getElementFromName(String name) {
        return elements.get(name);
    }
    public static int getIdFromElement(IElement el){
        int ret = 0;
        for(String set : elements.keySet()){
            if(getElementFromName(set).equals(el)){
                return ret;
            }else{
                ret++;
            }
        }
        return -1;
    }
}

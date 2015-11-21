package getfluxed.minemagicka.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import getfluxed.minemagicka.api.elements.IElement;

public class ElementRegistry {

	private static Map<Integer, IElement> elements = new HashMap<Integer, IElement>();
	private static int id=0;

	public static void registerElement(IElement element) {
		getElements().put(id++, element);
	}

	public static Map<Integer, IElement> getElements() {
		return elements;
	}
}

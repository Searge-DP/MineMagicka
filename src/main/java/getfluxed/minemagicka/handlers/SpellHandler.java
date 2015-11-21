package getfluxed.minemagicka.handlers;

import java.util.LinkedList;
import java.util.List;

import getfluxed.minemagicka.api.elements.IElement;

public class SpellHandler {
	
	public static List<IElement> currentElements = new LinkedList<IElement>();

	
	public static void addElement(IElement element){
		currentElements.add(element);
	}
}

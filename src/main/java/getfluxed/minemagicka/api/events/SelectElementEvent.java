package getfluxed.minemagicka.api.events;

import getfluxed.minemagicka.api.elements.ElementCompound;
import getfluxed.minemagicka.api.elements.IElement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SelectElementEvent extends Event {

    public final EntityPlayer player;
    public final boolean modifier;
    public ItemStack stack;
    public ElementCompound elements;
    public IElement element;

    public SelectElementEvent(EntityPlayer player, ItemStack stack, ElementCompound elements, IElement element, boolean modifier) {
        this.player = player;
        this.stack = stack;
        this.elements = elements;
        this.element = element;
        this.modifier = modifier;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    @Override
    public boolean hasResult() {
        return true;
    }

}

package getfluxed.minemagicka.client.render.radial;

import getfluxed.minemagicka.api.rendering.IRadialItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

import java.awt.*;

/**
 * @author WireSegal
 *         Created at 7:16 PM on 1/21/16.
 */
public class RadialStack implements IRadialItem {

    public ItemStack stack;

    public RadialStack(ItemStack stack) {
        this.stack = stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    @Override
    public void render(Gui gui, int x, int y, int xSize, int ySize, Color colorModifier) {
        Minecraft.getMinecraft().getItemRenderer().renderItem(Minecraft.getMinecraft().thePlayer, stack, ItemCameraTransforms.TransformType.GUI);
    }
}

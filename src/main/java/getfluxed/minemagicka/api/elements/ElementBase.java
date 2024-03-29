package getfluxed.minemagicka.api.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

public class ElementBase implements IElement {

    private String name;
    private int color;
    private List<IElement> opposites;
    private Pair<IElement, IElement> ingredients;
    private ResourceLocation icon;

    /**
     * DO NOT USE THIS! It creates a primary element, and should only be used by MineMagicka itself.
     */
    public ElementBase(String name, int color, ResourceLocation icon) {
        this(name, color, icon, null);
    }

    /**
     * DO NOT PASS NULL TO INGREDIENTS! This creates a primary element, and should only be used by MineMagicka itself.
     */
    public ElementBase(String name, int color, ResourceLocation icon, Pair<IElement, IElement> ingredients) {
        this.name = name;
        this.color = color;
        this.icon = icon;
        this.ingredients = ingredients;
    }

    @SuppressWarnings("static-access")
    public void render(Gui gui, int x, int y, int width, int height, Color colorModifier) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
        Color renderColor = new Color(this.getColor());
        if (colorModifier != null)
            renderColor = new Color(
                    (renderColor.getRed() + colorModifier.getRed()) / 2,
                    (renderColor.getGreen() + colorModifier.getGreen()) / 2,
                    (renderColor.getBlue() + colorModifier.getBlue()) / 2);
        GL11.glColor3f(renderColor.getRed() / 255F, renderColor.getGreen() / 255F, renderColor.getBlue() / 255F);
        gui.drawScaledCustomSizeModalRect(x, y, 0, 0, 24, 24, width, height, width, height);
        GL11.glColor3f(1F, 1F, 1F);
        GL11.glPopMatrix();
    }

    @Override
    public String getUnlocalizedName() {
        return name;
    }

    @Override
    public String getName() {
        return WordUtils.capitalizeFully(name);
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal("mm.element." + this.getUnlocalizedName() + ".description").replace('&', '\u00a7');
    }

    @Override
    public ResourceLocation getTexture() {
        return icon;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public boolean isPrimary() {
        return ingredients == null;
    }

    @Override
    public
    @Nullable
    Pair<IElement, IElement> getCombination() {
        return ingredients;
    }

    @Override
    public String toString() {
        return getName();
    }
}

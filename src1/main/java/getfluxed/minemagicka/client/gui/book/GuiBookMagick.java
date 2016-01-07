package getfluxed.minemagicka.client.gui.book;

import getfluxed.minemagicka.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiBookMagick extends GuiScreen { // TODO: 1/6/16 Implement getfluxed.minemagicka.api.compendium.IGuiCompendium 
    public static ResourceLocation texture = new ResourceLocation(Reference.modid, "textures/gui/guiBookMagick.png");
    public static ResourceLocation other = new ResourceLocation(Reference.modid, "textures/gui/guiBookOther.png");
    int guiWidth = 256;
    int guiHeight = 256;
    int left, top;
    int middleX = (guiWidth / 2) - guiWidth;
    int middleY = (guiHeight / 2) - guiHeight;

    @SuppressWarnings({"unchecked"})
    @Override
    public void initGui() {
        this.guiWidth = 256;
        this.guiHeight = 256;
        super.initGui();

        this.left = (this.width / 2) - (guiWidth / 2);
        this.top = (this.height / 2) - (guiHeight / 2);
        texture = new ResourceLocation(Reference.modid, "textures/gui/guiBookMagick.png");
        other = new ResourceLocation(Reference.modid, "textures/gui/guiBookOther.png");

        buttonList.add(new GuiButton(0, left + 10, top + 14, 11, 231, "") {
            @Override
            public void drawButton(Minecraft mc, int x, int y) {
                mc.renderEngine.bindTexture(other);
                boolean mouse = x > xPosition && x < xPosition + width && y > yPosition && y < yPosition + height;
                if (mouse)
                    drawTexturedModalRect(xPosition, yPosition, 0, 0, 11, 231);
                else {
                    drawTexturedModalRect(xPosition, yPosition, 22, 0, 11, 231);
                }
            }
        });
        buttonList.add(new GuiButton(1, left + guiWidth - 22, top + 14, 11, 231, "") {
            @Override
            public void drawButton(Minecraft mc, int x, int y) {
                mc.renderEngine.bindTexture(other);
                boolean mouse = x > xPosition && x < xPosition + width && y > yPosition && y < yPosition + height;
                if (mouse)
                    drawTexturedModalRect(xPosition, yPosition, 11, 0, 11, 231);
                else {
                    drawTexturedModalRect(xPosition, yPosition, 33, 0, 11, 231);
                }
            }
        });
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        if (mc.renderEngine != null) {

            mc.renderEngine.bindTexture(texture);
        }

        drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight);
        super.drawScreen(par1, par2, par3);
    }

    public void drawString(FontRenderer font, String string, int x, int y, int color) {
        boolean unicode = font.getUnicodeFlag();
        font.setUnicodeFlag(true);
        font.drawSplitString(string, x, y, 125, color);
        font.setUnicodeFlag(unicode);
    }

    /**
     * Renders the specified text to the screen, center-aligned.
     */
    public void drawCenteredString(FontRenderer p_73732_1_, String p_73732_2_, int p_73732_3_, int p_73732_4_, int p_73732_5_) {
        p_73732_1_.drawString(p_73732_2_, p_73732_3_ - p_73732_1_.getStringWidth(p_73732_2_) / 2, p_73732_4_, p_73732_5_);

    }

}

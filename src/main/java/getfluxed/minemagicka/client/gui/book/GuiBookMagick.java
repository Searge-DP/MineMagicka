package getfluxed.minemagicka.client.gui.book;

import getfluxed.minemagicka.api.compendium.ICompendiumEntry;
import getfluxed.minemagicka.api.compendium.ICompendiumPage;
import getfluxed.minemagicka.api.compendium.IGuiCompendium;
import getfluxed.minemagicka.common.reference.Reference;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class GuiBookMagick extends GuiScreen implements IGuiCompendium {
    public static ResourceLocation texture = new ResourceLocation(Reference.modid, "textures/gui/guiBookMagick.png");
    int guiWidth = 256;
    int guiHeight = 171;
    int left, top;
    int middleX = (guiWidth / 2) - guiWidth;
    int middleY = (guiHeight / 2) - guiHeight;

    @Override
    public void initGui() {
        super.initGui();

        this.left = (this.width / 2) - (guiWidth / 2);
        this.top = (this.height / 2) - (guiHeight / 2);
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

    public List<GuiButton> getButtonList() {
        return buttonList;
    }

    @Override
    public ICompendiumEntry getEntry() {
        return null;
    }

    @Override
    public ICompendiumPage getPage() {
        return null;
    }

    @Override
    public int getPageIndex() {
        return 0;
    }

    @Override
    public int getTop() {
        return this.top;
    }

    @Override
    public int getLeft() {
        return this.left;
    }

    @Override
    public int getWidth() {
        return guiWidth;
    }

    @Override
    public int getHeight() {
        return guiHeight;
    }

    @Override
    public float getZLevel() {
        return 0;
    }

    @Override
    public float getElapsedTicks() {
        return 0;
    }

    @Override
    public float getPartialTicks() {
        return 0;
    }

    @Override
    public float getTickDelta() {
        return 0;
    }

}

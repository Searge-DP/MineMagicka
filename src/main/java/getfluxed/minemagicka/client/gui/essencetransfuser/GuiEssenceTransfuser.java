package getfluxed.minemagicka.client.gui.essencetransfuser;

import getfluxed.minemagicka.common.reference.Reference;
import getfluxed.minemagicka.common.blocks.tile.machines.TileEntityEssenceTransfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiEssenceTransfuser extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation(Reference.modid, "textures/gui/extractorEssence.png");
    private TileEntityEssenceTransfuser tile;
    private InventoryPlayer invPlayer;

    public GuiEssenceTransfuser(InventoryPlayer invPlayer, TileEntityEssenceTransfuser tile2) {
        super(new ContainerEssenceTransfuser(invPlayer, tile2));
        this.invPlayer = invPlayer;
        this.tile = tile2;

    }

    public void initGui() {
        this.xSize = 176;
        this.ySize = 166;
        super.initGui();

    }

    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        String s = tile.getDisplayName().getUnformattedText();
        int color = 0xb0b0b0;
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, color);
        this.fontRendererObj.drawString(invPlayer.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, color);
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = tile.getMaxFuelTime();

        if (i == 0)
        {
            i = 200;
        }

        return tile.getFuelTime() * pixels / i;
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = tile.getCookTime();
        int j = tile.getMaxCookTime();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        if (tile.isBurning())
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);

    }
}

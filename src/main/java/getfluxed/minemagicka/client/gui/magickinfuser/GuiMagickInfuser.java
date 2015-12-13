package getfluxed.minemagicka.client.gui.magickinfuser;

import org.lwjgl.opengl.GL11;

import getfluxed.minemagicka.reference.Reference;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiMagickInfuser extends GuiContainer {

	private TileEntityMagickInfuser tile;
	private InventoryPlayer invPlayer;

	public GuiMagickInfuser(InventoryPlayer invPlayer, TileEntityMagickInfuser tile2) {
		super(new ContainerMagickInfuser(invPlayer, tile2));
		this.invPlayer = invPlayer;
		this.tile = tile2;

	}

	private static final ResourceLocation texture = new ResourceLocation(Reference.modid, "textures/gui/magickInfuser.png");

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
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		int barHeight = (int) (((float) tile.infuserTimer / tile.infuserTimerMax) * 13);
		System.out.println(tile.infuserTimer);
		if(tile.infuserTimer==0){
			barHeight = 13;
		}
		drawTexturedModalRect(80, 33+(barHeight), 176, 0, 16, 13-barHeight);
		int barWidth = (int) (((float) tile.currentMagick / tile.maxMagick) * 89);
		
		drawTexturedModalRect(43, 50, 0, 166, barWidth, 18);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

	}
}

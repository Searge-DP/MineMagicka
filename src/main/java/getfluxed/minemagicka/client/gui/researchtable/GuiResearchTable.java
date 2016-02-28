package getfluxed.minemagicka.client.gui.researchtable;

import getfluxed.minemagicka.client.gui.researchtable.ResearchBlock.BlockType;
import getfluxed.minemagicka.common.reference.Reference;
import getfluxed.minemagicka.common.blocks.tile.researchtable.TileEntityResearchTableBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuiResearchTable extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation(Reference.modid, "textures/gui/research_table.png");
    public int innerX;
    public int innerY;
    public int innerNodeX = 36;
    public int innerNodeY = 20;
    private TileEntityResearchTableBook tile;
    private InventoryPlayer invPlayer;
    private List<ResearchBlock> researchBlocks = new ArrayList<ResearchBlock>();
    private ResearchBlock selectedBlock = null;

    public GuiResearchTable(InventoryPlayer invPlayer, TileEntityResearchTableBook tile2) {
        super(new ContainerResearchTableBook(invPlayer, tile2));
        this.invPlayer = invPlayer;
        this.tile = tile2;
    }

    public void initGui() {
        this.xSize = 176;
        this.ySize = 166;
        super.initGui();
        this.innerX = guiLeft + 36;
        this.innerY = guiTop + 20;
        addBlock(new ResearchBlock("d", 0, 2, BlockType.magickal, 0xFFFFFF));

        addBlock(new ResearchBlock("e", new Random().nextInt(14), new Random().nextInt(5), BlockType.three, new Random().nextInt()));
        addBlock(new ResearchBlock("f", new Random().nextInt(14), new Random().nextInt(5), BlockType.block, new Random().nextInt()));
        addBlock(new ResearchBlock("g", new Random().nextInt(14), new Random().nextInt(5), BlockType.three, new Random().nextInt()));

        addBlock(new ResearchBlock("e", new Random().nextInt(14), new Random().nextInt(5), BlockType.three, new Random().nextInt()));
        addBlock(new ResearchBlock("f", new Random().nextInt(14), new Random().nextInt(5), BlockType.block, new Random().nextInt()));
        addBlock(new ResearchBlock("g", new Random().nextInt(14), new Random().nextInt(5), BlockType.three, new Random().nextInt()));
        addBlock(new ResearchBlock("e", new Random().nextInt(14), new Random().nextInt(5), BlockType.three, new Random().nextInt()));
        addBlock(new ResearchBlock("f", new Random().nextInt(14), new Random().nextInt(5), BlockType.block, new Random().nextInt()));
        addBlock(new ResearchBlock("g", new Random().nextInt(14), new Random().nextInt(5), BlockType.three, new Random().nextInt()));

    }

    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isCollidedWithNode(mouseX, mouseY)) {

            if (mouseButton == 0) {
                selectedBlock = getNodeAt(mouseX, mouseY);
            }
            // PacketHandler.INSTANCE.sendToServer(new
            // MessageRuneCarverServerSync(nodes, lines, prevNode, tile.xCoord,
            // tile.yCoord, tile.zCoord));
        }

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        boolean canMove;
        ResearchBlock newRes;
        if (selectedBlock != null)
            switch (typedChar) {
                case 'w':
                    canMove = true;
                    newRes = new ResearchBlock("tempBlock", selectedBlock.getX() / 9, (selectedBlock.getY() / 9) - 1, selectedBlock.getBlockType(), 0xFFFFFF);
                    for (ResearchBlock ress : researchBlocks) {
                        if (!ress.equals(selectedBlock))
                            if (ress.isCollided(newRes)) {
                                canMove = false;
                            }
                    }
                    if (canMove)
                        selectedBlock.setY((selectedBlock.getY() / 9) - 1);
                    break;
                case 's':
                    canMove = true;
                    newRes = new ResearchBlock("tempBlock", selectedBlock.getX() / 9, (selectedBlock.getY() / 9) + 1, selectedBlock.getBlockType(), 0xFFFFFF);
                    for (ResearchBlock ress : researchBlocks) {
                        if (!ress.equals(selectedBlock))
                            if (ress.isCollided(newRes)) {
                                canMove = false;
                            }
                    }
                    if (canMove)
                        selectedBlock.setY((selectedBlock.getY() / 9) + 1);
                    break;
                case 'd':
                    canMove = true;
                    newRes = new ResearchBlock("tempBlock", (selectedBlock.getX() / 9) + 1, selectedBlock.getY() / 9, selectedBlock.getBlockType(), 0xFFFFFF);
                    for (ResearchBlock ress : researchBlocks) {
                        if (!ress.equals(selectedBlock))
                            if (ress.isCollided(newRes)) {
                                canMove = false;
                            }
                    }
                    if (canMove)
                        selectedBlock.setX((selectedBlock.getX() / 9) + 1);
                    break;
                case 'a':
                    canMove = true;
                    newRes = new ResearchBlock("tempBlock", (selectedBlock.getX() / 9) - 1, selectedBlock.getY() / 9, selectedBlock.getBlockType(), 0xFFFFFF);
                    for (ResearchBlock ress : researchBlocks) {
                        if (!ress.equals(selectedBlock))
                            if (ress.isCollided(newRes)) {
                                canMove = false;
                            }
                    }
                    if (canMove) {
                        selectedBlock.setX((selectedBlock.getX() / 9) - 1);
                    }
                    break;
            }

    }

    @Override
    public void drawGuiContainerForegroundLayer(int mx, int my) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(texture);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 770);

        for (ResearchBlock res : researchBlocks) {
            Color c = new Color(res.getColour());
            GL11.glColor4f(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, c.getAlpha() / 255f);
            drawTexturedModalRect(innerNodeX + res.getX(), innerNodeY + res.getY(), res.getBlockType().pair.getX(), res.getBlockType().pair.getY(), 9 * res.getBlockType().blocks, 9);
            GL11.glColor4f(1, 1, 1, 1);
        }
        if (selectedBlock != null) {
            for (int i = 0; i < selectedBlock.getBlockType().blocks; i++)
                drawTexturedModalRect(innerNodeX + selectedBlock.getX() + (9 * i), innerNodeY + selectedBlock.getY(), 176, 63, 9, 9);
        }
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    public List<ResearchBlock> getResearchBlocks() {
        return researchBlocks;
    }

    public boolean isCollidedWithNode(int mx, int my) {
        if (researchBlocks.isEmpty()) {
            return false;
        } else {
            for (ResearchBlock p : researchBlocks) {
                int nodeX = p.getX() + innerX;
                int nodeY = p.getY() + innerY;
                System.out.println(nodeX + ":" + mx);
                if (mx > nodeX && mx < nodeX + (9 * p.getBlockType().blocks)) {
                    if (my > nodeY && my < nodeY + 9) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ResearchBlock getNodeAt(int mx, int my) {
        if (!isCollidedWithNode(mx, my)) {
            return null;
        }
        for (ResearchBlock p : researchBlocks) {
            int nodeX = p.getX() + innerX;
            int nodeY = p.getY() + innerY;

            if (mx > nodeX && mx < nodeX + (9 * p.getBlockType().blocks)) {
                if (my > nodeY && my < nodeY + 9) {
                    return p;
                }
            }
        }
        return null;

    }

    public List<ResearchBlock> getBlocksFromID(String id) {
        List<ResearchBlock> retList = new ArrayList<>();
        for (ResearchBlock res : researchBlocks) {
            if (res.getIdentifier().equals(id)) {
                retList.add(res);
            }
        }
        return retList;
    }

    public boolean addBlock(ResearchBlock res) {
        for (ResearchBlock ress : researchBlocks) {
            if (res.isCollided(ress)) {
                return false;
            }
        }
        researchBlocks.add(res);
        return true;
    }
}

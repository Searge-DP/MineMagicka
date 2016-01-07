package getfluxed.minemagicka.client.gui.researchtable;

import java.awt.Rectangle;

import fluxedCore.util.CoordinatePair;
import scala.annotation.meta.setter;

public class ResearchBlock {

    private String identifier;
    private int x;
    private int y;
    private BlockType blockType;
    private int colour;

    public ResearchBlock(String identifier, int x, int y, BlockType blockType, int colour) {
        this.identifier = identifier;
        if (x + blockType.blocks > 14) {
            x = 14 - blockType.blocks;
        }
        this.x = x * 9;
        if (y > 4) {
            y = 4;
        }
        this.y = y * 9;
        this.blockType = blockType;
        this.colour = colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public int getColour() {
        return colour;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0) {
            x = 0;
        }
        if (x + blockType.blocks > 14) {
            x = 14 - blockType.blocks;
        }
        this.x = x * 9;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > 4) {
            y = 4;
        }
        this.y = y * 9;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public boolean isCollided(ResearchBlock block) {
        Rectangle r1 = new Rectangle(getX(), getY(), (9 * blockType.blocks), 9);
        Rectangle r2 = new Rectangle(block.getX(), block.getY(), (9 * block.blockType.blocks), 9);
        return r1.intersects(r2);
    }

    public enum BlockType {
        one(new CoordinatePair(176, 0), 1), two(new CoordinatePair(176, 9), 2), three(new CoordinatePair(176, 18), 3), four(new CoordinatePair(176, 27), 4), magickal(new CoordinatePair(176, 36), 1), end(new CoordinatePair(176, 45), 1), block(new CoordinatePair(176, 54), 1);
        public CoordinatePair pair;
        public int blocks;

        private BlockType(CoordinatePair pair, int blocks) {
            this.pair = pair;
            this.blocks = blocks;
        }
    }
}

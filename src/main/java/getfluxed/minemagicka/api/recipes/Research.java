package getfluxed.minemagicka.api.recipes;

import net.minecraft.block.Block;

import java.util.List;

public class Research {

    private Block magickBlock;
    private List<Block> researchBlocks;
    //TODO have a research that gets unlocked
//    private BookObject entryUnlock;

    public Research(Block magickBlock, List<Block> researchBlocks) {
        super();
        this.magickBlock = magickBlock;
        this.researchBlocks = researchBlocks;
    }

    public Block getMagickBlock() {
        return magickBlock;
    }

    public void setMagickBlock(Block magickBlock) {
        this.magickBlock = magickBlock;
    }

    public List<Block> getResearchBlocks() {
        return researchBlocks;
    }

    public void setResearchBlocks(List<Block> researchBlocks) {
        this.researchBlocks = researchBlocks;
    }

}

package getfluxed.minemagicka.api.recipes;

import getfluxed.minemagicka.client.gui.researchtable.ResearchBlock;

import java.util.List;

public class Research {

    private ResearchBlock magickBlock;
    private List<ResearchBlock> researchBlocks;
    //TODO have a research that gets unlocked
//    private BookObject entryUnlock;

    public Research(ResearchBlock magickBlock, List<ResearchBlock> researchBlocks) {
        super();
        this.magickBlock = magickBlock;
        this.researchBlocks = researchBlocks;
    }

    public ResearchBlock getMagickBlock() {
        return magickBlock;
    }

    public void setMagickBlock(ResearchBlock magickBlock) {
        this.magickBlock = magickBlock;
    }

    public List<ResearchBlock> getResearchBlocks() {
        return researchBlocks;
    }

    public void setResearchBlocks(List<ResearchBlock> researchBlocks) {
        this.researchBlocks = researchBlocks;
    }

}

package getfluxed.minemagicka.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockEmbraneBricks extends Block {

    public BlockEmbraneBricks() {
        super(Material.rock);
        setHardness(2.0f);
        setHarvestLevel("pickaxe", 2);
    }

}

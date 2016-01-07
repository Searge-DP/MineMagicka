package getfluxed.minemagicka.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockEmbraneOre extends Block {

    protected BlockEmbraneOre() {
        super(Material.rock);

        setHardness(0.5f);
        setHarvestLevel("pickaxe", 3);
    }

}

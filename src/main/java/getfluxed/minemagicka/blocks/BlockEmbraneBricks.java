package getfluxed.minemagicka.blocks;

import getfluxed.minemagicka.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockEmbraneBricks extends Block {

	public BlockEmbraneBricks() {
		super(Material.rock);
		
		setHardness(2.0f);
		setHarvestLevel("pickaxe", 2);
	}

}

package getfluxed.minemagicka.common.blocks;

import getfluxed.minemagicka.common.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class BlockFluidMagick extends BlockFluidClassic {

    private static Fluid magick = new FluidMagick();

    public BlockFluidMagick() {
        super(magick, Material.water);

        magick.setBlock(this);
    }

    public static Fluid getMagick() {
        return magick;
    }

    @Override
    public boolean canDisplace(IBlockAccess world, BlockPos blockPos) {
        return !world.getBlockState(blockPos).getBlock().getMaterial().isLiquid() && super.canDisplace(world, blockPos);
    }

    @Override
    public boolean displaceIfPossible(World world, BlockPos blockPos) {
        return !world.getBlockState(blockPos).getBlock().getMaterial().isLiquid() && super.displaceIfPossible(world, blockPos);
    }

    public static class FluidMagick extends Fluid {

        public FluidMagick() {
            super("liquidMagick", new ResourceLocation(Reference.modid + ":blocks/liquidMagick"), new ResourceLocation(Reference.modid + ":blocks/liquidMagick"));
        }

        @Override
        public String getLocalizedName(FluidStack fluidStack) {
            return StatCollector.translateToLocal(String.format("tile.%s.fluid.%s.name", Reference.modid, "magick"));
        }

    }

}

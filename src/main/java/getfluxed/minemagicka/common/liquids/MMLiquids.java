package getfluxed.minemagicka.common.liquids;

import getfluxed.minemagicka.common.blocks.BlockFluidMagick;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class MMLiquids {
    public static Fluid liquidMagick = BlockFluidMagick.getMagick();

    public static void preInit() {
        FluidRegistry.registerFluid(liquidMagick);
    }

}

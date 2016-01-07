package getfluxed.minemagicka.liquids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class MMLiquids {
    public static Fluid liquidMagick = new LiquidMagick();

    public static void preInit() {
        FluidRegistry.registerFluid(liquidMagick);
    }

}

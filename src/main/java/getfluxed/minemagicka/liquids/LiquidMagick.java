package getfluxed.minemagicka.liquids;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class LiquidMagick extends Fluid {

    public LiquidMagick() {
        super("liquidMagick",new ResourceLocation("tanks", "liquidMagick"), new ResourceLocation("tanks", "liquidMagick"));
    }

    @Override
    public int getColor() {
        return 0x005555;
    }

    @Override
    public String getLocalizedName(FluidStack stack) {
        return StatCollector.translateToLocal("liquidMagick");
    }

    @Override
    public String getUnlocalizedName(FluidStack stack) {
        return "liquidMagick";
    }

}

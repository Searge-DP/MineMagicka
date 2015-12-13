package getfluxed.minemagicka.liquids;

import fluxedCore.handlers.ClientEventHandler;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class LiquidMagick extends Fluid {

	public LiquidMagick() {
		super("liquidMagick");
	}

	@Override
	public int getColor() {
		return 0x005555;
	}

	@Override
	public int getColor(World world, int x, int y, int z) {
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

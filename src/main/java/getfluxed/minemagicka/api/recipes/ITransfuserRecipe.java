package getfluxed.minemagicka.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author WireSegal
 *         Created at 12:51 PM on 2/27/16.
 */
public interface ITransfuserRecipe {

    // These methods are for visualization, like JEI.
    Object inputStack(); // Only return a String or an ItemStack!
    ItemStack outputStack();

    boolean matches(ItemStack materialStack);

    @Nullable
    ItemStack output(ItemStack materialStack);
}

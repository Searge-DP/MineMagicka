package getfluxed.minemagicka.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author WireSegal
 *         Created at 1:02 PM on 2/27/16.
 */
public class BaseTransfuserRecipe implements ITransfuserRecipe {

    public Object in;
    public ItemStack out;

    public BaseTransfuserRecipe(Object input, ItemStack output) {
        if (!(input instanceof String) && !(input instanceof ItemStack))
            throw new ExceptionInInitializerError("Your input must be an ItemStack or an OreDictionary tag.");

        in = input;
        out = output;
    }

    @Override
    public boolean matches(ItemStack materialStack) {
        if (in instanceof ItemStack)
            return OreDictionary.itemMatches(materialStack, (ItemStack) in, false);
        else if (in instanceof String) {
            int[] ids = OreDictionary.getOreIDs(materialStack);
            for (int i : ids) {
                if (OreDictionary.getOreName(i).equals(in)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ItemStack output(ItemStack materialStack) {
        return out;
    }

    @Override
    public ItemStack outputStack() {
        return out;
    }

    @Override
    public Object inputStack() {
        return in;
    }
}

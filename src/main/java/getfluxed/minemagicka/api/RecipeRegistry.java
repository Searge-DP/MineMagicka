package getfluxed.minemagicka.api;

import getfluxed.minemagicka.api.recipes.RecipeMagickInfusion;
import getfluxed.minemagicka.items.MMItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RecipeRegistry {

    public static List<RecipeMagickInfusion> infusionRecipes = new ArrayList<RecipeMagickInfusion>();

    public static void registerMagickInfusionRecipe(RecipeMagickInfusion recipe) {
        infusionRecipes.add(recipe);
    }

    public static List<RecipeMagickInfusion> getInfusionRecipes() {
        return infusionRecipes;
    }

    public static RecipeMagickInfusion getInfusionRecipe(ItemStack input, int currentLiquid) {
        for (RecipeMagickInfusion r : getInfusionRecipes()) {
            if (r.canInfuse(input, currentLiquid)) {
                return r;
            }
        }

        return null;
    }

    public static int getLiquidMagickFromMeta(int metadata) {
        return 1000 / (metadata + 1);
    }

    public static void init() {
        registerMagickInfusionRecipe(new RecipeMagickInfusion(new ItemStack(Items.paper), new ItemStack(MMItems.pageLocked), 250) {
            @Override
            public ItemStack onCraft(World world, ItemStack output, BlockPos pos) {
                ItemStack retStack = output.copy();

                return super.onCraft(world, output, pos);
            }
        });
    }
}

package getfluxed.minemagicka.api;

import getfluxed.minemagicka.api.recipes.RecipeMagickInfusion;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecipeRegistry {

    public static List<RecipeMagickInfusion> infusionRecipes = new ArrayList<>();

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
}

package getfluxed.minemagicka.api;

import getfluxed.minemagicka.api.recipes.BaseTransfuserRecipe;
import getfluxed.minemagicka.api.recipes.ITransfuserRecipe;
import getfluxed.minemagicka.api.recipes.RecipeMagickInfusion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RecipeRegistry {

    public static List<RecipeMagickInfusion> infusionRecipes = new ArrayList<>();

    public static List<ITransfuserRecipe> transfusionRecipes = new ArrayList<>();

    public static void registerMagickInfusionRecipe(RecipeMagickInfusion recipe) {
        infusionRecipes.add(recipe);
    }

    public static List<RecipeMagickInfusion> getInfusionRecipes() {
        return infusionRecipes;
    }

    public static
    @Nullable
    RecipeMagickInfusion getInfusionRecipe(ItemStack input, int currentLiquid) {
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



    public static ITransfuserRecipe registerTransfuserRecipe(ITransfuserRecipe recipe) {
        transfusionRecipes.add(recipe);
        return recipe;
    }

    public static ITransfuserRecipe registerTransfuserRecipe(Object input, ItemStack output) {
        return registerTransfuserRecipe(new BaseTransfuserRecipe(input, output));
    }

    public static @Nullable ITransfuserRecipe getTransfuserRecipe(ItemStack material) {
        for (ITransfuserRecipe recipe : transfusionRecipes) {
            if (recipe.matches(material))
                return recipe;
        }
        return null;
    }

    public static @Nullable ItemStack getTransfuserOutput(ItemStack material) {
        for (ITransfuserRecipe recipe : transfusionRecipes) {
            if (recipe.matches(material))
                return recipe.output(material);
        }
        return null;
    }
}

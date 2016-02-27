package getfluxed.minemagicka.common.crafting;

import getfluxed.minemagicka.api.RecipeRegistry;
import getfluxed.minemagicka.api.recipes.RecipeMagickInfusion;
import getfluxed.minemagicka.common.items.ItemMagicResource;
import getfluxed.minemagicka.common.items.MMItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * @author WireSegal
 *         Created at 1:40 PM on 2/27/16.
 */
public class MMRecipes {
    public static void preInit() {
        RecipeRegistry.registerMagickInfusionRecipe(new RecipeMagickInfusion(new ItemStack(Items.stick), new ItemStack(Items.diamond), 500));
        RecipeRegistry.registerMagickInfusionRecipe(new RecipeMagickInfusion(new ItemStack(Items.paper), new ItemStack(MMItems.pageLocked), 250));
        RecipeRegistry.registerMagickInfusionRecipe(new RecipeMagickInfusion(new ItemStack(Items.quartz), new ItemStack(Items.prismarine_shard), 100));

        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Items.prismarine_shard), ItemMagicResource.ofElement("hydros"));

        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Items.porkchop), ItemMagicResource.ofElement("zoia"));
        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Items.beef), ItemMagicResource.ofElement("zoia"));
        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Items.chicken), ItemMagicResource.ofElement("zoia"));
        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Items.rabbit), ItemMagicResource.ofElement("zoia"));
        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Items.mutton), ItemMagicResource.ofElement("zoia"));

        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Items.leather), ItemMagicResource.ofElement("alexia"));

        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Blocks.ice), ItemMagicResource.ofElement("krio"));

        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Items.gunpowder), ItemMagicResource.ofElement("keranos"));

        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Items.ender_pearl), ItemMagicResource.ofElement("magikos"));

        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Blocks.dirt), ItemMagicResource.ofElement("gio"));

        RecipeRegistry.registerTransfuserRecipe(new ItemStack(Items.blaze_powder), ItemMagicResource.ofElement("floya"));
    }
}

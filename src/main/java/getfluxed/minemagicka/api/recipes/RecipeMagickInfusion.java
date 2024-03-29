package getfluxed.minemagicka.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RecipeMagickInfusion {

    private ItemStack input;
    private ItemStack output;
    private int liquidUsed;

    public RecipeMagickInfusion(ItemStack input, ItemStack output, int liquidUsed) {
        this.input = input;
        this.output = output;
        this.liquidUsed = liquidUsed;
    }

    public boolean canInfuse(ItemStack input, int currentLiquid) {
        return currentLiquid >= getLiquidUsed() && input.isItemEqual(getInput());
    }

    /*
     * Called after the item has been crafted, use it to add nbt
     */
    public ItemStack onCraft(World world, ItemStack output, BlockPos pos) {
        return output;
    }

    public ItemStack getInput() {
        return input;
    }

    public void setInput(ItemStack input) {
        this.input = input;
    }

    public ItemStack getOutput() {
        return output.copy();
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }

    public int getLiquidUsed() {
        return liquidUsed;
    }

    public void setLiquidUsed(int liquidUsed) {
        this.liquidUsed = liquidUsed;
    }

}

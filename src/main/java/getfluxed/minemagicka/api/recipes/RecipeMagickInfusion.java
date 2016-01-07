package getfluxed.minemagicka.api.recipes;

import net.minecraft.item.ItemStack;

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

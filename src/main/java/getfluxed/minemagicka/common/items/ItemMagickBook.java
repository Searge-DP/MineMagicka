package getfluxed.minemagicka.common.items;

import getfluxed.minemagicka.api.compendium.CompendiumHelper;
import getfluxed.minemagicka.api.compendium.ICompendium;
import getfluxed.minemagicka.client.gui.book.GuiBookMagick;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemMagickBook extends ModItem implements ICompendium {

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiBookMagick());
        }
        return stack;
    }

    @Override
    public void unlockResearch(ItemStack stack, String research) {
        CompendiumHelper.unlockResearch(stack, research);
    }

    @Override
    public boolean isResearchUnlocked(ItemStack stack, String research) {
        return CompendiumHelper.isResearchUnlocked(stack, research);
    }

    @Override
    public List<String> getUnlockedResearch(ItemStack stack) {
        return CompendiumHelper.getUnlockedResearch(stack);
    }

    @Override
    public void unlockAllResearch(ItemStack stack) {
        // TODO make research registry and get all the stuff from there
    }

    @Override
    public void stripAllResearch(ItemStack stack) {
        CompendiumHelper.stripAllResearch(stack);
    }
}

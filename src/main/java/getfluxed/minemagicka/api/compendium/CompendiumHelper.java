package getfluxed.minemagicka.api.compendium;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

public class CompendiumHelper {

    public static void unlockResearch(ItemStack stack, String research) {
        NBTTagList unlocks = getUnlockedResearches(stack);
        NBTTagCompound newUnlock = new NBTTagCompound();
        newUnlock.setString("name", research);
        unlocks.appendTag(newUnlock);
        setUnlockedResearches(stack, unlocks);
    }
    
    public static void lockResearch(ItemStack stack, String research) {
        NBTTagList unlocks = getUnlockedResearches(stack);
        NBTTagCompound newUnlock = new NBTTagCompound();
        newUnlock.setString("name", research);
        unlocks.appendTag(newUnlock);
        setUnlockedResearches(stack, unlocks);
    }
    

    public static NBTTagCompound getResearchTag(ItemStack stack) {
        return stack.getTagCompound().getCompoundTag("MMResearch");
    }

    public static NBTTagList getUnlockedResearches(ItemStack stack) {
        NBTTagCompound tag = getResearchTag(stack);
        return tag.getTagList("MMUnlockedResearches", NBT.TAG_COMPOUND);
    }

    public static void setResearchTag(ItemStack stack, NBTTagCompound nbt) {
        stack.getTagCompound().setTag("MMResearch", nbt);
    }

    public static void setUnlockedResearches(ItemStack stack, NBTTagList list) {
        NBTTagCompound tag = getResearchTag(stack);
        tag.setTag("MMUnlockedResearches", list);
        setResearchTag(stack, tag);
    }
}

package getfluxed.minemagicka.api.compendium;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i < unlocks.tagCount(); i++) {
            NBTTagCompound tag = unlocks.getCompoundTagAt(i);
            if (tag.getString("name").equals(research)) {
                unlocks.removeTag(i);
            }
        }
        setUnlockedResearches(stack, unlocks);
    }

    public static boolean isResearchUnlocked(ItemStack stack, String research) {

        NBTTagList unlocks = getUnlockedResearches(stack);
        for (int i = 0; i < unlocks.tagCount(); i++) {
            NBTTagCompound tag = unlocks.getCompoundTagAt(i);
            if (tag.getString("name").equals(research)) {
                return true;
            }
        }

        return false;

    }

    public static List<String> getUnlockedResearch(ItemStack stack) {
        ArrayList<String> list = new ArrayList<String>();
        NBTTagList unlocks = getUnlockedResearches(stack);
        for (int i = 0; i < unlocks.tagCount(); i++) {
            NBTTagCompound tag = unlocks.getCompoundTagAt(i);
            list.add(tag.getString("name"));
        }
        return list;
    }

    public static void stripAllResearch(ItemStack stack) {
        setUnlockedResearches(stack, new NBTTagList());
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

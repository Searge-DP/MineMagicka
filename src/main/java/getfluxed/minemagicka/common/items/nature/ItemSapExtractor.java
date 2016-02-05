package getfluxed.minemagicka.common.items.nature;

import java.util.List;

import fluxedCore.util.NBTHelper;
import getfluxed.minemagicka.api.nature.NatureRegistry;
import getfluxed.minemagicka.common.items.ModItem;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSapExtractor extends ModItem {

    public ItemSapExtractor() {
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        tooltip.add("Sap: " + NBTHelper.getString(stack, "sap"));
        tooltip.add(NBTHelper.getInt(stack, "currentSap") + "/5");
    }

    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack sap = NatureRegistry.getItemToSap().inverse().get(NatureRegistry.getTreeToSap().get(worldIn.getBlockState(pos).getBlock()));
        if (sap != null && (NBTHelper.getString(stack, "sap").equals(NatureRegistry.getItemToSap().get(sap).getName()) || NBTHelper.getString(stack, "sap").equals("none"))) {
            NBTHelper.setString(stack, "sap", NatureRegistry.getItemToSap().get(sap).getName());
            int currentSap = NBTHelper.getInt(stack, "currentSap");
            NBTHelper.setInteger(stack, "currentSap", ++currentSap);
            if (!worldIn.isRemote)
                if (worldIn.rand.nextInt(5) == 0) {
                    worldIn.setBlockState(pos, Blocks.log.getDefaultState());
                }
            if (currentSap == 6) {
                NBTHelper.setString(stack, "sap", "none");
                NBTHelper.setInteger(stack, "currentSap", 0);
                if (!worldIn.isRemote)
                    worldIn.spawnEntityInWorld(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, sap.copy()));
            }
        }
        return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
    }

}

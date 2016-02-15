package getfluxed.minemagicka.common.events;

import getfluxed.minemagicka.common.blocks.MMBlocks;
import getfluxed.minemagicka.common.items.MMItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BucketEventHandler { // TODO nuke this class from orbit

    public BucketEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void bucketFill(FillBucketEvent evt) {
        if (evt.current.getItem() == Items.bucket && evt.target.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos pos = evt.target.getBlockPos();

            if (evt.entityPlayer != null && !evt.entityPlayer.canPlayerEdit(pos, evt.target.sideHit, evt.current)) {
                return;
            }

            Block bID = evt.world.getBlockState(pos).getBlock();
            if (bID == MMBlocks.blockLiquidMagick) {
                if (evt.entityPlayer != null && evt.entityPlayer.capabilities.isCreativeMode) {
                    evt.world.setBlockToAir(pos);
                } else {
                    evt.world.setBlockToAir(pos);

                    evt.setResult(Result.ALLOW);
                    evt.result = new ItemStack(MMItems.bucketMagickLiquid);
                }
            }
        }
    }
}

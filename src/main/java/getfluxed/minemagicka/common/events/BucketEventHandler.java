package getfluxed.minemagicka.common.events;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.api.ElementRegistry;
import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.elements.IElement;
import getfluxed.minemagicka.api.events.SelectElementEvent;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.blocks.MMBlocks;
import getfluxed.minemagicka.common.handlers.SpellHandler;
import getfluxed.minemagicka.common.items.ItemStaff;
import getfluxed.minemagicka.common.items.MMItems;
import getfluxed.minemagicka.common.network.PacketHandler;
import getfluxed.minemagicka.common.network.messages.MessageSelectElement;
import getfluxed.minemagicka.common.network.messages.spells.MessageAddElement;
import getfluxed.minemagicka.common.network.messages.spells.MessageCastSpell;
import getfluxed.minemagicka.common.reference.ElementReference;
import getfluxed.minemagicka.common.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;

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

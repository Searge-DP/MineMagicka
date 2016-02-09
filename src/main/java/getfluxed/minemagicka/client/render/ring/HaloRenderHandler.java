package getfluxed.minemagicka.client.render.ring;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.common.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * @author WireSegal
 *         Created at 3:32 PM on 2/9/16.
 */
public class HaloRenderHandler {
    private static HaloRenderHandler instance = new HaloRenderHandler();

    public static int ticks = 0;

    @SubscribeEvent
    public void clientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            GuiScreen gui = Minecraft.getMinecraft().currentScreen;
            if(gui == null || !gui.doesGuiPauseGame()) {
                ticks++;
            }
        }
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(instance);
    }

    public static float getAngle(EntityPlayer player, int segs) {
        return getAngle(player, segs, 0F);
    }

    public static float getAngle(EntityPlayer player, int segs, float base) {
        float yaw = MathHelper.wrapAngleTo180_float(player.rotationYaw) + 90F;
        int angles = 360;
        float segAngles = angles / segs;
        float shift = segAngles / 2;

        if (yaw < 0)
            yaw = 180F + (180F + yaw);
        yaw -= 360F - base;
        float angle = 360F - yaw + shift;

        if (angle < 0)
            angle += 360F;

        return angle;
    }

    public static int getSegmentLookedAt(EntityPlayer player, int segs) {
        IHalo gui = MineMagicka.proxy.ringHandler.getActiveGui();

        if (gui == null) return -1;

        float yaw = getAngle(player, gui.segments(), MineMagicka.proxy.ringHandler.getStartingYaw());

        int angles = 360;
        int segAngles = angles / segs;
        for (int seg = 0; seg < segs; seg++) {
            float calcAngle = seg * segAngles;
            if (yaw >= calcAngle && yaw < calcAngle + segAngles)
                return seg;
        }
        return -1;
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent e) {
        EntityPlayer player = e.entityPlayer;

        IHalo gui = MineMagicka.proxy.ringHandler.getActiveGui();

        if (gui != null) {
            int segs = gui.segments();
            if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
                int seg = getSegmentLookedAt(player, segs);
                gui.onSegmentClicked(seg);
            }
        }
    }

    private double getRenderPosX() {
        return ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, Minecraft.getMinecraft().getRenderManager(), "renderPosX", "field_78725_b", "o" );
    }

    private double getRenderPosY() {
        return ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, Minecraft.getMinecraft().getRenderManager(), "renderPosY", "field_78726_c", "p");
    }

    private double getRenderPosZ() {
        return ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, Minecraft.getMinecraft().getRenderManager(), "renderPosZ", "field_78723_d", "q");
    }

    @SubscribeEvent
    public void onWorldRenderLast(RenderWorldLastEvent e) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        IHalo gui = MineMagicka.proxy.ringHandler.getActiveGui();
        if (gui != null) {
            int segs = gui.segments();

            Minecraft mc = Minecraft.getMinecraft();
            Tessellator tess = Tessellator.getInstance();

            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            float alpha = ((float) Math.sin((ticks + e.partialTicks) * 0.2F) * 0.5F + 0.5F) * 0.4F + 0.3F;

            double posX = player.prevPosX + (player.posX - player.prevPosX) * e.partialTicks;
            double posY = player.prevPosY + (player.posY - player.prevPosY) * e.partialTicks;
            double posZ = player.prevPosZ + (player.posZ - player.prevPosZ) * e.partialTicks;

            GlStateManager.translate(posX - getRenderPosX(), posY - getRenderPosY() + player.getDefaultEyeHeight(), posZ - getRenderPosZ());


            float base = MineMagicka.proxy.ringHandler.getStartingYaw();
            int angles = 360;
            int segAngles = angles / segs;
            float shift = base - segAngles / 2;

            float u = 1F;
            float v = 0.25F;

            float s = 3F;
            float m = 0.8F;
            float y = v * s * 2;
            float y0 = 0;

            int segmentLookedAt = getSegmentLookedAt(player, segs);

            for(int seg = 0; seg < segs; seg++) {
                boolean inside = false;
                float rotationAngle = (seg + 0.5F) * segAngles + shift;
                GlStateManager.pushMatrix();
                GlStateManager.rotate(rotationAngle, 0F, 1F, 0F);
                GlStateManager.translate(s * m, -0.75F, 0F);

                if(segmentLookedAt == seg)
                    inside = true;

                GlStateManager.scale(0.75F, 0.75F, 0.75F);
                GlStateManager.rotate(90F, 0F, 1F, 0F);
                gui.getSegment(seg).render(inside ? Color.gray : Color.white);
                GlStateManager.popMatrix();

                GlStateManager.pushMatrix();
                GlStateManager.rotate(180F, 1F, 0F, 0F);
                float a = alpha;
                if(inside) {
                    a += 0.3F;
                    y0 = -y;
                }

                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

                if(seg % 2 == 0)
                    GlStateManager.color(0.6F, 0.6F, 0.6F, a);
                else GlStateManager.color(1F, 1F, 1F, a);

                GlStateManager.disableCull();
                mc.renderEngine.bindTexture(new ResourceLocation(Reference.modid, "textures/models/halo.png"));
                for(int i = 0; i < segAngles; i++) {
                    float ang = i + seg * segAngles + shift;
                    double xp = Math.cos(ang * Math.PI / 180F) * s;
                    double zp = Math.sin(ang * Math.PI / 180F) * s;

//                    TODO: tess.getWorldRenderer().pos(xp * m, y, zp * m).tex(u, v).endVertex();
//                    TODO: tess.getWorldRenderer().pos(xp, y0, zp).tex(u, 0).endVertex();

                    xp = Math.cos((ang + 1) * Math.PI / 180F) * s;
                    zp = Math.sin((ang + 1) * Math.PI / 180F) * s;

//                    TODO: tess.getWorldRenderer().pos(xp, y0, zp).tex(0, 0).endVertex();
//                    TODO: tess.getWorldRenderer().pos(xp * m, y, zp * m).tex(0, v).endVertex();
                }
                tess.draw();
                GlStateManager.enableCull();
                GlStateManager.popMatrix();
            }
            GlStateManager.popMatrix();
        }
    }
}

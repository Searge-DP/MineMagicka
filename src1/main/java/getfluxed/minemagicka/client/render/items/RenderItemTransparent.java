package getfluxed.minemagicka.client.render.items;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderItemTransparent implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return (helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION) || (helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING);
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
        GL11.glEnable(2896);
        GL11.glEnable(2929);

        Item itemTrans = item.getItem();
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(16.0F, 16.0F, 1.0F);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glTranslatef(-0.5F, -0.25F, 0.0F);
            GL11.glDisable(2884);
        }
        Tessellator tessellator = Tessellator.instance;
        IIcon icon = itemTrans.getIcon(item, 0);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glEnable(3008);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glDisable(3008);

        GL11.glShadeModel(7425);
        // GL11.glColor4f(1.0F, 1.0F, 1.0F, trans);
        if (type != IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glEnable(32826);
            ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
            GL11.glDisable(32826);
        } else {
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(0.0D, 0.0D, 0.03125D, icon.getMinU(), icon.getMinV());
            tessellator.addVertexWithUV(0.0D, 1.0D, 0.03125D, icon.getMinU(), icon.getMaxV());
            tessellator.addVertexWithUV(1.0D, 1.0D, 0.03125D, icon.getMaxU(), icon.getMaxV());
            tessellator.addVertexWithUV(1.0D, 0.0D, 0.03125D, icon.getMaxU(), icon.getMinV());
            tessellator.draw();
        }
        GL11.glShadeModel(7424);
        GL11.glEnable(3008);
        GL11.glDisable(3042);
        // else if (type != IItemRenderer.ItemRenderType.INVENTORY)
        // {
        // GL11.glEnable(32826);
        // ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(),
        // icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(),
        // icon.getIconHeight(), 0.0625F);
        // GL11.glDisable(32826);
        // }
        // else
        // {
        // tessellator.startDrawingQuads();
        // tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, icon.getMinU(),
        // icon.getMinV());
        // tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, icon.getMinU(),
        // icon.getMaxV());
        // tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, icon.getMaxU(),
        // icon.getMaxV());
        // tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, icon.getMaxU(),
        // icon.getMinV());
        // tessellator.draw();
        // }
        // }
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(0.0625F, 0.0625F, 1.0F);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glTranslatef(0.5F, 0.25F, 0.0F);
            GL11.glEnable(2884);
        }
    }
}

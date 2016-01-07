package getfluxed.minemagicka.blocks;

import getfluxed.minemagicka.api.RecipeRegistry;
import getfluxed.minemagicka.api.recipes.RecipeMagickInfusion;
import getfluxed.minemagicka.liquids.MMLiquids;
import getfluxed.minemagicka.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockLiquidMagick extends BlockFluidClassic {

    public IIcon stillIcon;
    public IIcon flowIcon;

    public BlockLiquidMagick() {
        super(MMLiquids.liquidMagick, Material.water);
        setQuantaPerBlock(8);
    }

    @Override
    public void registerBlockIcons(IIconRegister icon) {
        super.registerBlockIcons(icon);
        this.stillIcon = icon.registerIcon(Reference.modid + ":liquidMagick");
        this.flowIcon = icon.registerIcon(Reference.modid + ":liquidMagick");
        getFluid().setIcons(stillIcon, flowIcon);

    }

    @Override
    public IIcon getIcon(int meta, int side) {
        if (side <= 1) {
            return this.stillIcon;
        }
        return this.flowIcon;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        super.onEntityCollidedWithBlock(world, x, y, z, entity);
        if (!world.isRemote && world.getBlockMetadata(x, y, z) == 0)
            if (entity instanceof EntityItem) {
                EntityItem itemEnt = (EntityItem) entity;
                RecipeMagickInfusion recipe = RecipeRegistry.getInfusionRecipe(itemEnt.getEntityItem(), 1000);
                double entX = itemEnt.posX;
                double entY = itemEnt.posY;
                double entZ = itemEnt.posZ;

                if (recipe != null) {
                    itemEnt.getEntityItem().stackSize--;
                    float f = 0.7F;
                    double d0 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d1 = (double) (world.rand.nextFloat() * f) + (double) (2F - f) * 0.5D;
                    double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(world, (double) entX, (double) entY, (double) entZ, recipe.getOutput().copy());
                    entityitem.delayBeforeCanPickup = 10;
                    world.spawnEntityInWorld(entityitem);
                    world.setBlockToAir(x, y, z);

                }
            }
    }

}

package getfluxed.minemagicka.client;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.client.gui.essencetransfuser.ContainerEssenceTransfuser;
import getfluxed.minemagicka.client.gui.essencetransfuser.GuiEssenceTransfuser;
import getfluxed.minemagicka.client.gui.magickinfuser.ContainerMagickInfuser;
import getfluxed.minemagicka.client.gui.magickinfuser.GuiMagickInfuser;
import getfluxed.minemagicka.client.gui.researchtable.ContainerResearchTableBook;
import getfluxed.minemagicka.client.gui.researchtable.GuiResearchTable;
import getfluxed.minemagicka.common.tileentities.TileEntityMagickInfuser;
import getfluxed.minemagicka.common.tileentities.machines.TileEntityEssenceTransfuser;
import getfluxed.minemagicka.common.tileentities.researchtable.TileEntityResearchTableBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GUIHandler implements IGuiHandler {

    public GUIHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(MineMagicka.INSTANCE, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {

            case 0:
                if (te != null && te instanceof TileEntityMagickInfuser) {
                    return new ContainerMagickInfuser(player.inventory, (TileEntityMagickInfuser) te);
                }
                break;
            case 1:
                if (te != null && te instanceof TileEntityResearchTableBook) {
                    return new ContainerResearchTableBook(player.inventory, (TileEntityResearchTableBook) te);
                }
                break;
                
            case 2:
                if (te != null && te instanceof TileEntityEssenceTransfuser) {
                    return new ContainerEssenceTransfuser(player.inventory, (TileEntityEssenceTransfuser) te);
                }
                break;
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case 0:
                if (te != null && te instanceof TileEntityMagickInfuser) {
                    return new GuiMagickInfuser(player.inventory, (TileEntityMagickInfuser) te);
                }
                break;

            case 1:
                if (te != null && te instanceof TileEntityResearchTableBook) {
                    return new GuiResearchTable(player.inventory, (TileEntityResearchTableBook) te);
                }
                break;
            case 2:
                if (te != null && te instanceof TileEntityEssenceTransfuser) {
                    return new GuiEssenceTransfuser(player.inventory, (TileEntityEssenceTransfuser) te);
                }
                break;
        }
        return null;
    }

}

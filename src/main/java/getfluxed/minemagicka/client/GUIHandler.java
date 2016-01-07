package getfluxed.minemagicka.client;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.client.gui.magickinfuser.ContainerMagickInfuser;
import getfluxed.minemagicka.client.gui.magickinfuser.GuiMagickInfuser;
import getfluxed.minemagicka.tileentities.TileEntityMagickInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GUIHandler implements IGuiHandler {

    public GUIHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(MineMagicka.INSTANCE, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        switch (ID) {

            case 0:
                if (te != null && te instanceof TileEntityMagickInfuser) {
                    return new ContainerMagickInfuser(player.inventory, (TileEntityMagickInfuser) te);
                }
                break;

        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        switch (ID) {
            case 0:
                if (te != null && te instanceof TileEntityMagickInfuser) {
                    return new GuiMagickInfuser(player.inventory, (TileEntityMagickInfuser) te);
                }
                break;
        }
        return null;
    }

}

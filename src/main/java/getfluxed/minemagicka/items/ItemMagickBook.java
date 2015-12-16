package getfluxed.minemagicka.items;

import getfluxed.minemagicka.client.gui.book.GuiBookMagick;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMagickBook extends Item {

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (world.isRemote) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiBookMagick());
		}
		return stack;
	}
}

package getfluxed.minemagicka.items;

import com.sun.xml.internal.ws.protocol.soap.ClientMUTube;

import fluxedCore.handlers.ClientEventHandler;
import fluxedCore.util.NBTHelper;
import getfluxed.minemagicka.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemStaff extends Item {

	public IIcon hilt;
	public IIcon gem;

	public ItemStaff() {
		this.setMaxStackSize(1);
		this.setFull3D();
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
		return pass == 0 ? hilt : gem;
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int pass) {
		switch (pass) {
			case 0:
				return 0xFFFFFF;
			case 1:
				return ClientEventHandler.getColorInt();
			default:
				return 0xFFFFFF;
		}
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return pass == 0 ? hilt : gem;
	}

	@Override
	public void registerIcons(IIconRegister icon) {
		this.hilt = icon.registerIcon(Reference.modid + ":magic_staff_hilt");
		this.gem = icon.registerIcon(Reference.modid + ":magic_staff_gem");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean par5) {
		if (stack.stackTagCompound == null) {
			NBTHelper.initNBTTagCompound(stack);
			NBTHelper.setInteger(stack, "MMSelectedElement", 0);
		}

	}

	public int getSelectedElement(ItemStack stack) {
		return NBTHelper.getInt(stack, "MMSelectedElement");
	}
}

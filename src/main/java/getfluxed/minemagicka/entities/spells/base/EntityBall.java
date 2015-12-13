package getfluxed.minemagicka.entities.spells.base;

import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.spells.SpellBall;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBall extends EntityThrowable {

	public SpellBall ball;

	public EntityBall(World world, SpellBall ball, EntityLivingBase entity) {
		super(world, entity);
		this.ball = ball;
	}

	public EntityBall(World world) {
		super(world);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (ball != null)
			ball.onEntityUpdate(this, worldObj);
	}

	@Override
	public void onImpact(MovingObjectPosition mop) {
		if (ball != null)
			ball.onImpact(this, worldObj, mop);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.ball = (SpellBall) SpellRegistry.getSpellFromName(nbt.getString("MMSpell"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
nbt.setString("MMSpell", this.ball.getUnlocalizedName());
	}

}

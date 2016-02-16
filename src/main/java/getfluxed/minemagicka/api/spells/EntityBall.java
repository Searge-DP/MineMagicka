package getfluxed.minemagicka.api.spells;

import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.elements.ElementCompound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBall extends EntityThrowable {

    public ISpellBall ball;
    public ElementCompound elements;

    public EntityBall(World world) {
        super(world);
    }

    public EntityBall(World world, ISpellBall ball, ElementCompound elements, EntityLivingBase entity) {
        super(world, entity);
        this.ball = ball;
        this.elements = elements;
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
        this.ball = (ISpellBall) SpellRegistry.getSpellFromName(nbt.getString("MMSpell"));
        this.elements = (new ElementCompound()).readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setString("MMSpell", this.ball.getUnlocalizedName());
        this.elements.writeToNBT(nbt);
    }

    @Override
    public String toString() {
        return "EntityBall [ ball=" + (ball == null ? "null" : ball.getName()) + " elements=" + (elements == null ? "null" : elements.toString()) + " ]";
    }

}

package getfluxed.minemagicka.api.casting;

import net.minecraft.entity.Entity;

public enum CastingType {
    spray(0), lightning(1), projectile(4), beam(3), shield(Integer.MAX_VALUE);

    int priority;
    Entity castEntity;

    private CastingType(int priority) {
        this.priority = priority;
        this.castEntity = castEntity;
    }
}

package net.eseption.dnd_minecraft.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class PlayerRaycastUtil {

    public static LivingEntity getLivingEntityLookedAt(Player player, double distance) {
        Vec3 eyePos = player.getEyePosition(1.0f);
        Vec3 lookVec = player.getViewVector(1.0f);
        Vec3 endPos = eyePos.add(lookVec.scale(distance));

        AABB boundingBox = player.getBoundingBox().expandTowards(lookVec.scale(distance)).inflate(1.0d);
        EntityHitResult hitResult = ProjectileUtil.getEntityHitResult(
                player,
                eyePos,
                endPos,
                boundingBox,
                (entity) -> !entity.isSpectator(),
                distance * distance
        );

        if (hitResult != null) {
            if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                return livingEntity;
            }
        }
        return null;
    }
}

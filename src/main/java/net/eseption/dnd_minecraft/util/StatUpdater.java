package net.eseption.dnd_minecraft.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class StatUpdater {

    public static void updateStats() {
    }

    public static int getStrengthBonus(LivingEntity entity) {
        int bonus = 0;

        //any level of strength maxed at + 11 strength
        if (entity.hasEffect(MobEffects.DAMAGE_BOOST)) {
            MobEffectInstance mobEffect = entity.getEffect(MobEffects.DAMAGE_BOOST);
            int amplifier = 0;
            assert mobEffect != null;
            amplifier = mobEffect.getAmplifier() + 1;

            bonus = Math.min(amplifier*3, 11);
        }

        return bonus;
    }

    public static int getDexterityBonus(LivingEntity entity) {
        int bonus = 0;

        if (entity.hasEffect(MobEffects.MOVEMENT_SPEED)) {
            MobEffectInstance mobEffect = entity.getEffect(MobEffects.MOVEMENT_SPEED);
            int amplifier = 0;
            assert mobEffect != null;
            amplifier = mobEffect.getAmplifier() + 1;

            bonus = Math.min(amplifier*2, 10);
        }

        return bonus;
    }

    public static int getConstitutionBonus(LivingEntity entity) {
        int bonus = 0;

        return bonus;
    }

    public static int getIntelligenceBonus(LivingEntity entity) {
        int bonus = 0;

        return bonus;
    }

    public static int getWisdomBonus(LivingEntity entity) {
        int bonus = 0;

        return bonus;
    }

    public static int getCharismaBonus(LivingEntity entity) {
        int bonus = 0;

        return bonus;
    }

    public static int getArmorClassBonus(LivingEntity entity) {
        int bonus = 0;

        //if armor > 8 (dex = 0)

        return bonus;
    }

    public static int getLevelXP(LivingEntity entity) {
        int bonus = 0;

        return bonus;
    }

    //need dedicated player level up or add a level point system so
    public static int getLevelUP(LivingEntity entity) {
        int bonus = 0;

        return bonus;
    }

}

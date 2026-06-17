package net.eseption.dnd_minecraft.util;

import net.eseption.dnd_minecraft.capability.EntityStatsHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class StatUpdater {

    public static void updateStats(LivingEntity entity, EntityStatsHolder stats) {

        stats.setBonusStrength(getStrengthBonus(entity, stats));

        stats.setBonusDexterity(getDexterityBonus(entity, stats));

        stats.setBonusConstitution(getConstitutionBonus(entity, stats));

        stats.setBonusIntelligence(getIntelligenceBonus(entity));

        stats.setBonusWisdom(getWisdomBonus(entity));

        stats.setBonusCharisma(getCharismaBonus(entity));

        stats.setArmorClass(getArmorClass(entity, stats));
    }

    public static int getStrengthBonus(LivingEntity entity, EntityStatsHolder stats) {
        int bonus = 0;

        //any level of strength maxed at + 11 strength
        if (entity.hasEffect(MobEffects.DAMAGE_BOOST)) {
            MobEffectInstance mobEffect = entity.getEffect(MobEffects.DAMAGE_BOOST);
            int amplifier = 0;
            assert mobEffect != null;
            amplifier = mobEffect.getAmplifier() + 1;

            bonus += Math.min(amplifier * 3, 11);
        }

        if (entity.hasEffect(MobEffects.WEAKNESS)) {
            MobEffectInstance mobEffect = entity.getEffect(MobEffects.WEAKNESS);
            int amplifier = 0;
            assert mobEffect != null;
            amplifier = mobEffect.getAmplifier() + 1;

            bonus += Math.max(amplifier * -3, -11);
        }

        return bonus;
    }

    public static int getDexterityBonus(LivingEntity entity, EntityStatsHolder stats) {
        int bonus = 0;

        //any level of speed maxed at + 8 dexterity
        if (entity.hasEffect(MobEffects.MOVEMENT_SPEED)) {
            MobEffectInstance mobEffect = entity.getEffect(MobEffects.MOVEMENT_SPEED);
            int amplifier = 0;
            assert mobEffect != null;
            amplifier = mobEffect.getAmplifier() + 1;

            bonus += Math.min(amplifier * 2, 8);
        }

        if (entity.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
            MobEffectInstance mobEffect = entity.getEffect(MobEffects.MOVEMENT_SLOWDOWN);
            int amplifier = 0;
            assert mobEffect != null;
            amplifier = mobEffect.getAmplifier() + 1;

            bonus += Math.max(amplifier * -2, -8);
        }

        return bonus;
    }

    public static int getConstitutionBonus(LivingEntity entity, EntityStatsHolder stats) {
        int bonus = 0;

        //any level of health boost maxed at + 11 constitution while also considering default/altered max health
        if (entity.hasEffect(MobEffects.HEALTH_BOOST)) {
            MobEffectInstance mobEffect = entity.getEffect(MobEffects.HEALTH_BOOST);
            int amplifier = 0;
            assert mobEffect != null;
            amplifier = mobEffect.getAmplifier() + 1;

            bonus += Math.min(amplifier, 11);
        }

        //ensure it does not go over the soft cap of 21
        if (stats.getBaseConstitution() + bonus >= 21) {
            bonus = 21 - stats.getBaseConstitution();
        }

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

        //any level of speed maxed at + 5 charisma
        if (entity.hasEffect(MobEffects.HERO_OF_THE_VILLAGE)) {
            MobEffectInstance mobEffect = entity.getEffect(MobEffects.HERO_OF_THE_VILLAGE);
            int amplifier = 0;
            assert mobEffect != null;
            amplifier = mobEffect.getAmplifier() + 1;

            bonus += Math.min(amplifier * 2, 5);
        }

        return bonus;
    }

    public static int getArmorClass(LivingEntity entity, EntityStatsHolder stats) {
        int base_ac = stats.getNaturalArmorClass();
        int final_ac = 0;
        int nat_acBonus = entity.getArmorValue() / 2;
        int dex_modifier;
        AttributeInstance armor = entity.getAttribute(Attributes.ARMOR);
        int armor_ac = 0;
        if (armor != null) {
            armor_ac = Math.floorDiv((int) armor.getValue(), 2);
        }

        //recalculates dexterity using getDexterityBonus to ensure correct math due to application order
        //may remove once order is identified

        //if class = monk wisdom instead of dex (large switch statement with below added)

        //heavy
        if (armor_ac > 5) {
            dex_modifier = 0;
            nat_acBonus = 0;
        //medium
        } else if (armor_ac > 2) {
            dex_modifier = Math.floorDiv((stats.getBaseDexterity() + getDexterityBonus(entity, stats) - 10),2);
            if (dex_modifier > 2) {
                dex_modifier = 2;
            }
            nat_acBonus = 0;
        //light and none
        } else {
            dex_modifier = Math.floorDiv((stats.getBaseDexterity() + getDexterityBonus(entity, stats) - 10),2);
        }

        //if armor > 8 (dex = 0)
        //??? = 10 + 2 + (0-10) + (0-5+)
        final_ac = base_ac + nat_acBonus + armor_ac + dex_modifier;


        return final_ac;
    }

    public static int getLevelXP(LivingEntity entity) {
        int bonus = 0;

        return bonus;
    }
    //stats.setXpToLevel(stats.getLevel() * 100); only when level up needed to reduce math

    //need dedicated player level up or add a level point system so
    public static int getLevelUP(LivingEntity entity) {
        int bonus = 0;

        return bonus;
    }
    //if level+1 ( getLevelXP )


}

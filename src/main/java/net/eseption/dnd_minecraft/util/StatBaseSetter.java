package net.eseption.dnd_minecraft.util;

import net.eseption.dnd_minecraft.capability.EntityStatsHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;


//separate stats into functions

public class StatBaseSetter {

    public static void generateStats(LivingEntity entity, EntityStatsHolder stats) {

        //entity.equipmentHasChanged()
        //entity.getActiveEffects()
        //entity.getMaxHealth()

        //Mob
        //WaterAnimal
        //Animal
        //AbstractGolem -includes shulkers
        //AgeableMob if baby init != true or get another one like if isBaby == true and isInit == true then init== false, so when they grow up they can be checked again

        if (!(entity instanceof LivingEntity)) {
            return;
        }

        if (entity instanceof Player) {
            stats.setInitialised(true);
            return;
        }

        if (stats.isInitialised()) {return;}

        //strength
        generateSTR(entity, stats);

        //dexterity
        generateDEX(entity, stats);

        //constitution
        generateCON(entity, stats);

        //intelligence
        generateINT(entity, stats);

        //wisdom
        generateWIS(entity, stats);


        //charisma
        generateCHA(entity, stats);

        //armorclass
        generateAC(entity, stats);

        //leveling system? (system that automatically assigns appropriate levels to all entities)

        //Boss Mobs
        generateSpecial(entity, stats);

        //levelxp setter
        generateLVLXP(stats);

        stats.setInitialised(true);
    }

    private static void generateSTR(LivingEntity entity, EntityStatsHolder stats) {

        AttributeInstance attack = entity.getAttribute(Attributes.ATTACK_DAMAGE);

        int str = 10;
        if (attack != null) {
            str = 10 + (int) (attack.getValue() / 2);
        } else {
            //make scale based on  constitution (horses...)
            str = 8;
        }

        str = Math.min(str, 21);
        stats.setBaseStrength(str);
    }

    private static void generateDEX(LivingEntity entity, EntityStatsHolder stats) {

        AttributeInstance fly_speed = null;
        AttributeInstance move_speed = null;

        if (entity.getAttribute(Attributes.FLYING_SPEED) != null) {
            fly_speed = entity.getAttribute(Attributes.FLYING_SPEED);
        }
        if (entity.getAttribute(Attributes.MOVEMENT_SPEED) != null) {
            move_speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        }

        int dex = 0;
        if (fly_speed != null) {
            dex = 10 +(int)(fly_speed.getBaseValue()*15);
            move_speed = null;
        } else if (move_speed != null){
            dex = 10 + (int)(Math.ceil(move_speed.getBaseValue()*4));
        }

        stats.setBaseDexterity(dex);
    }

    private static void generateCON(LivingEntity entity, EntityStatsHolder stats) {
        //axolotl
        int con = 10 + ((int)(entity.getMaxHealth() - 20) / 4);
        con = Math.min(con, 21);

        if (entity instanceof Axolotl) {
            con = 17;
        }
        if (entity instanceof IronGolem) {
            con = 23;
        }

        stats.setBaseConstitution(con);
    }

    private static void generateINT(LivingEntity entity, EntityStatsHolder stats) {
        //illagers (PatrollingMonster)!/ villager!/ wanderer!/ iron golem!

        if (entity instanceof AbstractVillager) {
            stats.setBaseIntelligence(12);
        }

        if (entity instanceof PatrollingMonster) {
            if (entity instanceof Illusioner) {
                stats.setBaseIntelligence(17);
            } else if (entity instanceof Ravager) {
                stats.setBaseIntelligence(8);
            } else {
                stats.setBaseIntelligence(14);
            }
        }

        if (entity instanceof AbstractGolem) {
            stats.setBaseIntelligence(6);
        }

        if (entity instanceof Animal) {
            if (entity instanceof TamableAnimal) {
                stats.setBaseIntelligence(7);
            } else if (entity instanceof AbstractHorse) {
                stats.setBaseIntelligence(8);
            } else if (entity instanceof Fox) {
                stats.setBaseIntelligence(8);
            } else {
                stats.setBaseIntelligence(2);
            }
        }

    }

    private static void generateWIS(LivingEntity entity, EntityStatsHolder stats) {
        //done
        if (entity instanceof Evoker) {
            stats.setBaseWisdom(17);
        }

        if (entity instanceof  Witch) {
            stats.setBaseWisdom(16);
        }

        if (entity instanceof Allay) {
            stats.setBaseWisdom(14);
        }
    }

    private static void generateCHA(LivingEntity entity, EntityStatsHolder stats) {
        //new mob default
        stats.setBaseCharisma(8);

        if (entity instanceof Animal) {
            stats.setBaseCharisma(4);
        }

        if (entity instanceof AbstractVillager) {
            stats.setBaseCharisma(16);
        }

        if (entity instanceof PatrollingMonster) {
            if (entity instanceof Ravager) {
                stats.setBaseCharisma(4);
            } else {
                stats.setBaseCharisma(14);
            }
        }

        if (entity instanceof AbstractGolem) {
            stats.setBaseCharisma(7);
        }
    }

    private static void generateAC(LivingEntity entity, EntityStatsHolder stats) {

        if (entity instanceof Turtle) {
            stats.setNaturalArmorClass(16);
        }
    }

    private static void generateSpecial(LivingEntity entity, EntityStatsHolder stats) {
        if (entity instanceof Warden) {
            stats.setAll(21, 19, 26, 6, 10, 2, 12, 14);
        }
        if (entity instanceof EnderDragon) {
            stats.setAll(20, 25, 27, 8, 10, 8, 12, 21);
        }
        if (entity instanceof WitherBoss) {
            stats.setAll(23, 21, 30, 7, 19, 4, 12, 19);
        }

        //guardian/elder??
    }

    private static void generateLVLXP(EntityStatsHolder stats) {
        stats.setXpToLevel(stats.getLevel() * 100);

    }
}

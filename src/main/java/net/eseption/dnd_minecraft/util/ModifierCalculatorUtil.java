package net.eseption.dnd_minecraft.util;

import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.minecraft.world.entity.LivingEntity;

public class ModifierCalculatorUtil {

    //STR = 0 DEX = 1 CON = 2 INT = 3 WIS = 4 CHA = 5
    public static int getModifierOnType(LivingEntity entity, int stat) {

        if (entity != null) {
            return entity.getCapability(EntityStatsProvider.ENTITY_STATS)
                    .map(stats ->
                            switch (stat) {
                                case 0 -> Math.floorDiv(stats.getStrength() - 10, 2);
                                case 1 -> Math.floorDiv(stats.getDexterity() - 10, 2);
                                case 2 -> Math.floorDiv(stats.getConstitution() - 10, 2);
                                case 3 -> Math.floorDiv(stats.getIntelligence() - 10, 2);
                                case 4 -> Math.floorDiv(stats.getWisdom() - 10, 2);
                                case 5 -> Math.floorDiv(stats.getCharisma() - 10, 2);
                                default -> 0;
                            })
                    .orElse(0);
        }
        return 0;
    }
}

package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;;
import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.eseption.dnd_minecraft.util.StatSetter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


//helper to attach stats to entity in server

@Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID)
public class EntityEvents {

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof LivingEntity livingEntity)) {
            return;
        }

        if(entity.level().isClientSide()){
            return;
        }

        livingEntity.getCapability(EntityStatsProvider.ENTITY_STATS).ifPresent(stats -> {
            if (!stats.isInitialised()) {
                StatSetter.generateStats(livingEntity, stats);
            }
        });
    }

    //can be used to check baby animals growth??
    //need another event ctrl + h
}

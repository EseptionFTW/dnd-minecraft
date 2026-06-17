package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.eseption.dnd_minecraft.network.ModMessages;
import net.eseption.dnd_minecraft.network.packets.EntityStatsSyncPacket;
import net.eseption.dnd_minecraft.util.StatBaseSetter;
import net.eseption.dnd_minecraft.util.StatUpdater;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
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
                StatBaseSetter.generateStats(livingEntity, stats);
                //depending on implementation this might not be needed imminently
                StatUpdater.updateStats(livingEntity, stats);
            }
        });
    }

    //LivingEvent - Top don't use
    //LivingTickEvent - every tick could get laggy but probably not if done correctly (is not currently done correctly)
    //MobEffectEvent - nice
    //LivingEquipmentChange
    //LivingHurtEvent
    //LivingHealEvent


    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingTickEvent event) {
        Entity entity = event.getEntity();
        if(!(entity instanceof  LivingEntity livingEntity)) {
            return;
        }

        if (entity.level().isClientSide()) {
            return;
        }

        livingEntity.getCapability(EntityStatsProvider.ENTITY_STATS).ifPresent(stats -> {
            //if(stats.isInitialised()) {
                StatUpdater.updateStats(livingEntity, stats);
                ModMessages.sentToTrackingEntity(new EntityStatsSyncPacket(livingEntity), livingEntity);
            //}
        });

    }
    //can be used to check baby animals growth??
    //need another event ctrl + h
}

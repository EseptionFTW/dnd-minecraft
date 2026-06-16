package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.network.ModMessages;
import net.eseption.dnd_minecraft.network.packets.EntityStatsSyncPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//updates entities to have server stats

@Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID)
public class TrackingEvents {

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        if (!(event.getTarget() instanceof LivingEntity entity)) {
            return;
        }

        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        ModMessages.sendToPlayer(
                new EntityStatsSyncPacket(entity),
                player
        );
    }
}
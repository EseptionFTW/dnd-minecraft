package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//handles player death stat loading so it does not get overridden by default

@Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID)
public class PlayerEvents {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {

        if (!event.isWasDeath()) {
            return;
        }

        event.getOriginal().reviveCaps();

        event.getOriginal().getCapability(EntityStatsProvider.ENTITY_STATS)
                .ifPresent(oldStats -> {
                    event.getEntity().getCapability(EntityStatsProvider.ENTITY_STATS)
                            .ifPresent(newStats -> {
                                newStats.copyData(oldStats);
                            });
                });

        event.getOriginal().invalidateCaps();
    }
}

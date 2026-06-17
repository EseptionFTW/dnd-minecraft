package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//depreciated due to

@Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID)
public class CapabilityEvents {

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof LivingEntity entity) {

            EntityStatsProvider provider = new EntityStatsProvider();
            ResourceLocation location = new ResourceLocation(Dnd_minecraft.MODID, "entity_stats");

            event.addCapability(
                    location,
                    provider
            );
        }

    }

}

package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.eseption.dnd_minecraft.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TempClientForgeEvents {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        if (KeyBinding.OPEN_CHARACTER_SHEET.consumeClick()) {

            Player player = minecraft.player;

            player.getCapability(EntityStatsProvider.ENTITY_STATS)
                    .ifPresent(stats -> {

                        player.sendSystemMessage(
                                Component.literal(
                                        "STR=" + stats.getStrength()
                                                + " DEX=" + stats.getDexterity()
                                                + " CON=" + stats.getConstitution()
                                                + " INT=" + stats.getIntelligence()
                                                + " WIS=" + stats.getWisdom()
                                                + " CHA=" + stats.getCharisma()
                                )
                        );
                    });

            //if (minecraft.screen == null) {
            //    minecraft.setScreen(new CharacterScreen());
            //}
        }
    }
}

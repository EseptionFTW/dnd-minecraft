package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.client.screen.*;
import net.eseption.dnd_minecraft.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class ClientEvents {

    @Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Minecraft minecraft = Minecraft.getInstance();

            if (minecraft.player == null) {
                return;
            }

            if (minecraft.screen != null) {
                return;
            }
            if (KeyBinding.OPEN_CHARACTER_SHEET.consumeClick()) {
                minecraft.setScreen(new CharacterScreen());
            }
            if (KeyBinding.OPEN_SKILLS_MENU.consumeClick()) {
                minecraft.setScreen(new SkillsScreen());
            }
            if (KeyBinding.OPEN_STATS_MENU.consumeClick()) {
                minecraft.setScreen(new StatsScreen());
            }
            if (KeyBinding.OPEN_SPELLS_MENU.consumeClick()) {
                minecraft.setScreen(new SpellsScreen());
            }
            if (KeyBinding.OPEN_LEVEL_MENU.consumeClick()) {
                minecraft.setScreen(new LevelScreen());
            }
        }

        @Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ClientModBusEvents {
            @SubscribeEvent
            public static void onKeyRegister(RegisterKeyMappingsEvent event) {
                event.register(KeyBinding.OPEN_CHARACTER_SHEET);
                event.register(KeyBinding.OPEN_SKILLS_MENU);
                event.register(KeyBinding.OPEN_SPELLS_MENU);
                event.register(KeyBinding.OPEN_SPELLS_MENU);
                event.register(KeyBinding.OPEN_LEVEL_MENU);
            }
        }




    }
}

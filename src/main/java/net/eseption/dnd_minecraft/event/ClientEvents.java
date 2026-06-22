package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.client.inventory.CharacterScreenMenu;
import net.eseption.dnd_minecraft.client.inventory.ModMenus;
import net.eseption.dnd_minecraft.client.screen.*;
import net.eseption.dnd_minecraft.network.ModMessages;
import net.eseption.dnd_minecraft.network.packets.OpenMenusSyncPacket;
import net.eseption.dnd_minecraft.util.KeyBindingUtil;
import net.eseption.dnd_minecraft.util.PlayerRaycastUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


public class ClientEvents {

    @Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID, value = Dist.CLIENT)//, bus = Mod.EventBusSubscriber.Bus.FORGE)
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
            if (KeyBindingUtil.OPEN_CHARACTER_SHEET.consumeClick()) {
                //minecraft.setScreen(new CharacterScreen());
                ModMessages.sendToServer(new OpenMenusSyncPacket());
            }
            if (KeyBindingUtil.OPEN_BESTIARY_MENU.consumeClick()) {
               if (PlayerRaycastUtil.getLivingEntityLookedAt(minecraft.player, 16) == null || minecraft.player.isShiftKeyDown()) {
                    minecraft.setScreen(new BestiaryScreen(null));
                    //create logic for null entity by showing full registry, also if player is holding shift key
                } else {
                   minecraft.setScreen(new BestiaryScreen(PlayerRaycastUtil.getLivingEntityLookedAt(minecraft.player, 16.0d)));
               }
            }
            if (KeyBindingUtil.OPEN_SKILLS_MENU.consumeClick()) {
                //remake used as base for tempscreen
                //minecraft.setScreen(new SkillsScreen());
            }
            if (KeyBindingUtil.OPEN_SPELLS_MENU.consumeClick()) {
                minecraft.setScreen(new SpellsScreen());
            }
            if (KeyBindingUtil.OPEN_LEVEL_MENU.consumeClick()) {
                minecraft.setScreen(new LevelScreen());
            }
        }

        @Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ClientModBusEvents {
            @SubscribeEvent
            public static void onKeyRegister(RegisterKeyMappingsEvent event) {
                event.register(KeyBindingUtil.OPEN_CHARACTER_SHEET);
                event.register(KeyBindingUtil.OPEN_BESTIARY_MENU);
                event.register(KeyBindingUtil.OPEN_SKILLS_MENU);
                event.register(KeyBindingUtil.OPEN_SPELLS_MENU);
                event.register(KeyBindingUtil.OPEN_LEVEL_MENU);
            }

            @SubscribeEvent
            public static void  onClientSetup(FMLClientSetupEvent event) {
                event.enqueueWork(() ->
                        MenuScreens.register(
                                ModMenus.CHARACTER_SCREEN_MENU.get(),
                                CharacterScreen::new
                        ));
            };

        }
    }
}

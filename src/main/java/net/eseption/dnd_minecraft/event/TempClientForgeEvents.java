package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.util.KeyBindingUtil;
import net.minecraft.client.Minecraft;
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

        if (KeyBindingUtil.OPEN_CHARACTER_SHEET.consumeClick()) {

            //if (minecraft.screen == null) {
            //    minecraft.setScreen(new CharacterScreen());
            //}
        }
    }
}

package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.command.StatsCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID)
public class CommandEvents {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {

        StatsCommand.register(event.getDispatcher());
    }
}

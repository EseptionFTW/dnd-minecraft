package net.eseption.dnd_minecraft.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.eseption.dnd_minecraft.network.ModMessages;
import net.eseption.dnd_minecraft.network.packets.EntityStatsSyncPacket;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class StatsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("stats")
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            player.getCapability(EntityStatsProvider.ENTITY_STATS)
                                    .ifPresent(stats -> {
                                        context.getSource().sendSuccess(
                                                () -> Component.literal(
                                                        "STR = " + stats.getStrength() + "\n" +
                                                                "DEX = " + stats.getDexterity() + "\n" +
                                                                "CON = " + stats.getConstitution() + "\n" +
                                                                "INT = " + stats.getIntelligence() + "\n" +
                                                                "WIS = " + stats.getWisdom() + "\n" +
                                                                "CHA = " + stats.getCharisma()),
                                                false

                                        );
                                    });
                            return 1;
                        })
        );
        dispatcher.register(
                Commands.literal("setstrstats")
                        .then(
                                Commands.argument(
                                                "value",
                                                IntegerArgumentType.integer()
                                        )
                                        .executes(context -> {
                                            int value = IntegerArgumentType.getInteger(
                                                    context, "value"
                                            );

                                            ServerPlayer player = context.getSource().getPlayerOrException();
                                            player.getCapability(EntityStatsProvider.ENTITY_STATS).ifPresent(stats -> {

                                                stats.setStrength(value);
                                                ModMessages.sendToPlayer(new EntityStatsSyncPacket(player), player);

                                                context.getSource().sendSuccess(() -> Component.literal("Strength Set To " + value),
                                                        false
                                                );
                                            });
                                            return 1;
                                        })
                        )
        );
    }
}


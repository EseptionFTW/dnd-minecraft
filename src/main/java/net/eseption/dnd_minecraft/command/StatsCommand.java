package net.eseption.dnd_minecraft.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.eseption.dnd_minecraft.network.ModMessages;
import net.eseption.dnd_minecraft.network.packets.EntityStatsSyncPacket;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class StatsCommand {

    private static final List<String> STATS = List.of(
            "strength",
            "dexterity",
            "constitution",
            "intelligence",
            "wisdom",
            "charisma"
    );

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        //check current stats (rename, add <base/current>)
        //stats
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
                                                                "CHA = " + stats.getCharisma() + "\n" +
                                                                "AC = " + stats.getArmorClass()),
                                                false

                                        );
                                    });
                            return 1;
                        })
        );

        //setbasestats (rename)
        //setstats <stat> <value>
        dispatcher.register(
                Commands.literal("setstats")
                        .then(
                                Commands.argument(
                                                "stat",
                                                StringArgumentType.word()
                                        )
                                        .suggests((context, builder) -> {
                                            STATS.forEach(builder::suggest);
                                            return builder.buildFuture();
                                        })
                                        .then(Commands.argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    ServerPlayer player = context.getSource().getPlayerOrException();

                                                    String stat = StringArgumentType.getString(context, "stat");

                                                    int value = IntegerArgumentType.getInteger(context, "value");

                                                    player.getCapability(EntityStatsProvider.ENTITY_STATS)
                                                            .ifPresent(stats -> {

                                                                switch (stat) {
                                                                    case "strength" -> stats.setBaseStrength(value);
                                                                    case "dexterity" -> stats.setBaseDexterity(value);
                                                                    case "constitution" -> stats.setBaseConstitution(value);
                                                                    case "intelligence" -> stats.setBaseIntelligence(value);
                                                                    case "wisdom" -> stats.setBaseWisdom(value);
                                                                    case "charisma" -> stats.setBaseCharisma(value);
                                                                }

                                                                ModMessages.sendToPlayer(new EntityStatsSyncPacket(player), player);
                                                            });

                                                    context.getSource().sendSuccess(() -> Component.literal(stat + " set to " + value), false);

                                                    return 1;
                                                })))
                        .requires(source -> source.hasPermission(2))
        );
    }
}
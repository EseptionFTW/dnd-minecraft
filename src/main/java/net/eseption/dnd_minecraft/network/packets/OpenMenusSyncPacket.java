package net.eseption.dnd_minecraft.network.packets;

import net.eseption.dnd_minecraft.client.inventory.CharacterScreenMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

public class OpenMenusSyncPacket {

    public static void encode(OpenMenusSyncPacket msg, FriendlyByteBuf buf) {}

    public static OpenMenusSyncPacket decode(FriendlyByteBuf buf) {
        return new OpenMenusSyncPacket();
    }

    public static void handle(OpenMenusSyncPacket mas, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer serverPlayer = context.get().getSender();

            if (serverPlayer == null) {return;}

            NetworkHooks.openScreen(
                    serverPlayer,
                    new SimpleMenuProvider(
                            (id, inventory, player) -> new CharacterScreenMenu(id, inventory),
                            Component.literal("Character") //Change to real component translatable
                    )
            );

        });

        context.get().setPacketHandled(true);
    }
}


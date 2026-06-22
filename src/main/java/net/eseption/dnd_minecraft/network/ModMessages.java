package net.eseption.dnd_minecraft.network;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.network.packets.EntityStatsSyncPacket;
import net.eseption.dnd_minecraft.network.packets.OpenMenusSyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetID = 0;
    private static int id() {return packetID++; }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Dnd_minecraft.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(EntityStatsSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EntityStatsSyncPacket::new)
                .encoder(EntityStatsSyncPacket::toBytes)
                .consumerMainThread(EntityStatsSyncPacket::handle)
                .add();

        net.registerMessage(
                id(),
                OpenMenusSyncPacket.class,
                OpenMenusSyncPacket::encode,
                OpenMenusSyncPacket::decode,
                OpenMenusSyncPacket::handle
        );
    }

    public static <MSG> void sendToServer(MSG message) {INSTANCE.sendToServer(message);}

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sentToTrackingEntity(MSG message, Entity entity) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
    }

    //usage send tracked entity data to player to see its stats
    //ModMessages.sendToTrackingEntity(
    //        new EntityStatsSyncPacket(entity),
    //entity
    //);

    public static <MSG> void sendToAll(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    //debugger usage


    //public static <MSG> void sendToEntity(MSG message, ServerEntity)
}

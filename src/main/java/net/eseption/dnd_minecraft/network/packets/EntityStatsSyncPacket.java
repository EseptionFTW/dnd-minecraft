package net.eseption.dnd_minecraft.network.packets;


import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;


public class EntityStatsSyncPacket {

    private int entityID;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int level;

    private int armorClass;

    public EntityStatsSyncPacket(Entity entity) {
        this.entityID = entity.getId();

        entity.getCapability(EntityStatsProvider.ENTITY_STATS)
                .ifPresent(stats -> {
                    this.strength = stats.getStrength();
                    this.dexterity = stats.getDexterity();
                    this.constitution = stats.getConstitution();
                    this.intelligence = stats.getIntelligence();
                    this.wisdom = stats.getWisdom();
                    this.charisma = stats.getCharisma();
                    this.level = stats.getLevel();
                    this.armorClass = stats.getArmorClass();
                });



    }

    public EntityStatsSyncPacket(FriendlyByteBuf buf) {
        entityID = buf.readInt();

        strength = buf.readInt();
        dexterity = buf.readInt();
        constitution = buf.readInt();
        intelligence = buf.readInt();
        wisdom = buf.readInt();
        charisma = buf.readInt();

        level = buf.readInt();

        armorClass = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {

        buf.writeInt(entityID);

        buf.writeInt(strength);
        buf.writeInt(dexterity);
        buf.writeInt(constitution);
        buf.writeInt(intelligence);
        buf.writeInt(wisdom);
        buf.writeInt(charisma);

        buf.writeInt(level);

        buf.writeInt(armorClass);

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //default checks

            Minecraft minecraft = Minecraft.getInstance();

            if (minecraft.level == null) {
                return;
            }

            Entity entity = minecraft.level.getEntity(entityID);

            if (entity == null) {
                return;
            }
            // Update On Client

            entity.getCapability(EntityStatsProvider.ENTITY_STATS)
                    .ifPresent(stats -> {
                        stats.setStrength(strength);
                        stats.setDexterity(dexterity);
                        stats.setConstitution(constitution);
                        stats.setIntelligence(intelligence);
                        stats.setWisdom(wisdom);
                        stats.setCharisma(charisma);
                        stats.setArmorClass(armorClass);
                        stats.setLevel(level);
                    });

        });

        return true;

    }


}

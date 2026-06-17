package net.eseption.dnd_minecraft.network.packets;


import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//dictates what is sent between server to client (base and bonus stats, final stats to be added in client)
//only issue is it's hard to define where the base and bonus stats come from (Baldur's Gate 3 style hover over text)

public class EntityStatsSyncPacket {

    private int entityID;

    private int baseStrength;
    private int bonusStrength;
    private int baseDexterity;
    private int bonusDexterity;
    private int baseConstitution;
    private int bonusConstitution;
    private int baseIntelligence;
    private int bonusIntelligence;
    private int baseWisdom;
    private int bonusWisdom;
    private int baseCharisma;
    private int bonusCharisma;

    private int level;

    private int armorClass;

    public EntityStatsSyncPacket(Entity entity) {
        this.entityID = entity.getId();

        entity.getCapability(EntityStatsProvider.ENTITY_STATS)
                .ifPresent(stats -> {
                    this.baseStrength = stats.getBaseStrength();
                    this.bonusStrength = stats.getBonusStrength();
                    this.baseDexterity = stats.getBaseDexterity();
                    this.bonusDexterity = stats.getBonusDexterity();
                    this.baseConstitution = stats.getBaseConstitution();
                    this.bonusConstitution = stats.getBonusConstitution();
                    this.baseIntelligence = stats.getBaseIntelligence();
                    this.bonusIntelligence = stats.getBonusIntelligence();
                    this.baseWisdom = stats.getBaseWisdom();
                    this.bonusWisdom = stats.getBonusWisdom();
                    this.baseCharisma = stats.getBaseCharisma();
                    this.bonusCharisma = stats.getBonusCharisma();
                    this.level = stats.getLevel();
                    this.armorClass = stats.getArmorClass();
                });



    }

    public EntityStatsSyncPacket(FriendlyByteBuf buf) {
        entityID = buf.readInt();

        baseStrength = buf.readInt();
        bonusStrength = buf.readInt();
        baseDexterity = buf.readInt();
        bonusDexterity = buf.readInt();
        baseConstitution = buf.readInt();
        bonusConstitution = buf.readInt();
        baseIntelligence = buf.readInt();
        bonusIntelligence = buf.readInt();
        baseWisdom = buf.readInt();
        bonusWisdom = buf.readInt();
        baseCharisma = buf.readInt();
        bonusCharisma = buf.readInt();

        level = buf.readInt();

        armorClass = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {

        buf.writeInt(entityID);

        buf.writeInt(baseStrength);
        buf.writeInt(bonusStrength);
        buf.writeInt(baseDexterity);
        buf.writeInt(bonusDexterity);
        buf.writeInt(baseConstitution);
        buf.writeInt(bonusConstitution);
        buf.writeInt(baseIntelligence);
        buf.writeInt(bonusIntelligence);
        buf.writeInt(baseWisdom);
        buf.writeInt(bonusWisdom);
        buf.writeInt(baseCharisma);
        buf.writeInt(bonusCharisma);

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
                        stats.setBaseStrength(baseStrength);
                        stats.setBaseDexterity(baseDexterity);
                        stats.setBaseConstitution(baseConstitution);
                        stats.setBaseIntelligence(baseIntelligence);
                        stats.setBaseWisdom(baseWisdom);
                        stats.setBaseCharisma(baseCharisma);
                        stats.setArmorClass(armorClass);
                        stats.setLevel(level);
                    });
        });

        return true;
    }

}

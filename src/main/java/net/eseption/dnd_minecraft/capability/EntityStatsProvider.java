package net.eseption.dnd_minecraft.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//Used to synchronize entity data from client to server and back

public class EntityStatsProvider implements ICapabilitySerializable<CompoundTag> {

    public static final Capability<EntityStatsHolder> ENTITY_STATS =
            CapabilityManager.get(new CapabilityToken<>() {});

    private final EntityStatsHolder entityStatsHolder = new EntityStatsHolder();

    private final LazyOptional<EntityStatsHolder> optional =
            LazyOptional.of(() -> entityStatsHolder);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ENTITY_STATS) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return entityStatsHolder.saveStatNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        entityStatsHolder.loadStatNBT(tag);
    }

}

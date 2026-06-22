package net.eseption.dnd_minecraft.client.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class InventoryMenu extends MenuType {

    public InventoryMenu(MenuSupplier p_267054_, FeatureFlagSet p_266909_) {
        super(p_267054_, p_266909_);
    }

    @Override
    public AbstractContainerMenu create(int p_39986_, Inventory p_39987_) {
        return super.create(p_39986_, p_39987_);
    }
}

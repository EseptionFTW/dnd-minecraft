package net.eseption.dnd_minecraft.client.inventory;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.items.SlotItemHandler;
import org.apache.commons.compress.utils.Lists;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

public class CharacterScreenMenu extends AbstractContainerMenu {

    private final List<DataSlot> dataSlots = Lists.newArrayList();

    private final int containterId;

    private static final int EQUIPMENT_SLOTS = 5;
    private static final int SHIELD_SLOT = EQUIPMENT_SLOTS;
    private static final int INVENTORY_START = SHIELD_SLOT + 1;

    public CharacterScreenMenu(int containterId, Inventory inventory) {
        super(ModMenus.CHARACTER_SCREEN_MENU.get(), containterId);
        this.containterId = containterId;

        int curioPosX = 55;
        int curioPosY= -7;
        int spacing = 18;

        int invStartx = -14;
        int invStarty = 112;

        Player player = inventory.player;

        CuriosApi.getCuriosInventory(player).ifPresent( handler -> {

            var ringHandler = handler.getCurios().get("ring");
            var necklaceHandler = handler.getCurios().get("necklace");
            var capeHandler = handler.getCurios().get("back"); //cape or back
            var headHandler = handler.getCurios().get("head");

            if (headHandler != null) {
                addSlot(new SlotItemHandler(
                        headHandler.getStacks(),
                        0,
                        curioPosX,
                        curioPosY));
            }

            if (necklaceHandler != null) {
                addSlot(new SlotItemHandler(
                        necklaceHandler.getStacks(),
                        0,
                        curioPosX,
                        curioPosY + spacing));
            }

            if (capeHandler != null) {
                addSlot(new SlotItemHandler(
                        capeHandler.getStacks(),
                        0,
                        curioPosX,
                        curioPosY + spacing * 2));
            }

            //slot handler, itemstackhandler, index (list of), x, y
            if (ringHandler != null) {
                addSlot(new SlotItemHandler(
                        ringHandler.getStacks(),
                        0,
                        curioPosX,
                        curioPosY + spacing * 3));
                addSlot(new SlotItemHandler(
                        ringHandler.getStacks(),
                        1,
                        curioPosX,
                        curioPosY + spacing * 4));
            }

        });

        //armor
        for (int i = 0; i < 4; i++) {
            addSlot(new Slot(player.getInventory(), 36 + i,
                    invStartx,
                    invStarty -119 + (3 - i) * 18
            ));
        }

        //shield slot
        addSlot(new Slot(player.getInventory(), 40, -14, curioPosY + spacing * 4));

        //main inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(player.getInventory(),
                        9 + col + row * 9,
                        invStartx + col * spacing,
                        invStarty + row * spacing
                ));
            }
        }

        //hotbar
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(player.getInventory(), i,
                    invStartx + i * spacing,
                    invStarty + 58
            ));
        }

    }

//    @Override
//    public ItemStack quickMoveStack(Player player, int slotIndex) {
//        ItemStack itemstack = ItemStack.EMPTY;
//        Slot slot = this.slots.get(slotIndex);
//
//        if (slot != null && slot.hasItem()) {
//            ItemStack itemstack1 = slot.getItem();
//            itemstack = itemstack1.copy();
//
//            // Index Constants matching your specific registration order
//            int curioStart = 0;
//            int curioEnd = 5;       // 5 slots (0, 1, 2, 3, 4)
//
//            int armorStart = 5;
//            int armorEnd = 9;       // 4 slots (5, 6, 7, 8)
//
//            int offhandSlot = 9;    // 1 slot (9)
//
//            int playerInvStart = 10;
//            int playerHotbarStart = 37;
//            int playerInvEnd = 46;  // 36 slots total (10 to 45)
//
//            // CASE A: Item is shift-clicked INSIDE your 5 custom curio slots
//            if (slotIndex >= curioStart && slotIndex < curioEnd) {
//                // Move from Curio -> Player Inventory (Main + Hotbar)
//                if (!this.moveItemStackTo(itemstack1, playerInvStart, playerInvEnd, true)) {
//                    return ItemStack.EMPTY;
//                }
//            }
//            // CASE B: Item is shift-clicked INSIDE Armor or Offhand slots
//            else if ((slotIndex >= armorStart && slotIndex < armorEnd) || slotIndex == offhandSlot) {
//                // Move from Armor/Offhand -> Player Inventory
//                if (!this.moveItemStackTo(itemstack1, playerInvStart, playerInvEnd, false)) {
//                    return ItemStack.EMPTY;
//                }
//            }
//            // CASE C: Item is shift-clicked INSIDE the Main Player Inventory or Hotbar
//            else {
//                // 1. First, try to move the item into your 5 custom Curio slots
//                // (Note: moveItemStackTo checks slot.mayPlace() internally)
//                if (!this.moveItemStackTo(itemstack1, curioStart, curioEnd, false)) {
//
//                    // 2. If it doesn't fit in Curios, handle vanilla inventory shifting
//                    if (slotIndex >= playerInvStart && slotIndex < playerHotbarStart) {
//                        // From Main Storage -> Hotbar
//                        if (!this.moveItemStackTo(itemstack1, playerHotbarStart, playerInvEnd, false)) {
//                            return ItemStack.EMPTY;
//                        }
//                    } else if (slotIndex >= playerHotbarStart && slotIndex < playerInvEnd) {
//                        // From Hotbar -> Main Storage
//                        if (!this.moveItemStackTo(itemstack1, playerInvStart, playerHotbarStart, false)) {
//                            return ItemStack.EMPTY;
//                        }
//                    }
//                }
//            }
//
//            // Standard cleanup logic
//            if (itemstack1.isEmpty()) {
//                slot.setByPlayer(ItemStack.EMPTY);
//            } else {
//                slot.setChanged();
//            }
//
//            if (itemstack1.getCount() == itemstack.getCount()) {
//                return ItemStack.EMPTY;
//            }
//
//            slot.onTake(player, itemstack1);
//        }
//
//        return itemstack;
//    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            // Exact index configuration based on your registration order
            int curioStart = 0;
            int curioEnd = 5;

            int armorStart = 5;
            int armorEnd = 9;

            int offhandSlot = 9;

            int playerInvStart = 10;
            int playerHotbarStart = 37;
            int playerInvEnd = 46;

            // CASE A: Clicked inside Curio slots
            if (slotIndex >= curioStart && slotIndex < curioEnd) {
                if (!this.moveItemStackTo(itemstack1, playerInvStart, playerInvEnd, true)) {
                    return ItemStack.EMPTY;
                }
            }
            // CASE B: Clicked inside Armor or Offhand slots
            else if ((slotIndex >= armorStart && slotIndex < armorEnd) || slotIndex == offhandSlot) {
                if (!this.moveItemStackTo(itemstack1, playerInvStart, playerInvEnd, false)) {
                    return ItemStack.EMPTY;
                }
            }
            // CASE C: Clicked inside Main Inventory or Hotbar
            else {
                // 1. Intercept Armor items first!
                EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(itemstack);
                if (equipmentslot.getType() == EquipmentSlot.Type.ARMOR) {
                    // Map the EquipmentSlot type to your exact registered index
                    // Note: Vanilla Armor slots are indexed from HEAD down to FEET, or FEET up to HEAD.
                    int targetArmorSlot = armorStart + equipmentslot.getIndex();

                    if (!this.moveItemStackTo(itemstack1, targetArmorSlot, targetArmorSlot + 1, false)) {
                        // If armor slot is full, check Curio slots next
                        if (!this.moveItemStackTo(itemstack1, curioStart, curioEnd, false)) {
                            // If Curio is also full, perform standard inventory quick-shuffling
                            if (slotIndex < playerHotbarStart) {
                                if (!this.moveItemStackTo(itemstack1, playerHotbarStart, playerInvEnd, false)) return ItemStack.EMPTY;
                            } else {
                                if (!this.moveItemStackTo(itemstack1, playerInvStart, playerHotbarStart, false)) return ItemStack.EMPTY;
                            }
                        }
                    }
                }
                // 2. Intercept Shields / Offhand items next!
                else if (itemstack1.getItem() instanceof ShieldItem || itemstack1.canEquip(EquipmentSlot.OFFHAND, player)) {
                    if (!this.moveItemStackTo(itemstack1, offhandSlot, offhandSlot + 1, false)) {
                        if (!this.moveItemStackTo(itemstack1, curioStart, curioEnd, false)) {
                            if (slotIndex < playerHotbarStart) {
                                if (!this.moveItemStackTo(itemstack1, playerHotbarStart, playerInvEnd, false)) return ItemStack.EMPTY;
                            } else {
                                if (!this.moveItemStackTo(itemstack1, playerInvStart, playerHotbarStart, false)) return ItemStack.EMPTY;
                            }
                        }
                    }
                }
                // 3. General Items (Curios, blocks, materials)
                else {
                    // If it's a Curio item, it goes here. Your custom Slot wrapper should handle filtering.
                    if (!this.moveItemStackTo(itemstack1, curioStart, curioEnd, false)) {
                        if (slotIndex >= playerInvStart && slotIndex < playerHotbarStart) {
                            if (!this.moveItemStackTo(itemstack1, playerHotbarStart, playerInvEnd, false)) {
                                return ItemStack.EMPTY;
                            }
                        } else if (slotIndex >= playerHotbarStart && slotIndex < playerInvEnd) {
                            if (!this.moveItemStackTo(itemstack1, playerInvStart, playerHotbarStart, false)) {
                                return ItemStack.EMPTY;
                            }
                        }
                    }
                }
            }

            // Standard validation/cleanup loop
            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.isAlive();
    }

    @Override
    protected DataSlot addDataSlot(DataSlot slot) {
        return super.addDataSlot(slot);
    }


}

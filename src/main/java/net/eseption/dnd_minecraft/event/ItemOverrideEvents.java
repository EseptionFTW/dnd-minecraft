package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.util.BaseItemCollectionUtil;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.eseption.dnd_minecraft.util.BaseItemCollectionUtil.EQUIPABLE_MODIFIER_UUID_PER_SLOT;

@Mod.EventBusSubscriber(modid = Dnd_minecraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ItemOverrideEvents {

    private static final java.util.UUID BASE_ATTACK_DAMAGE_UUID = java.util.UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private static final java.util.UUID BASE_ATTACK_SPEED_UUID = java.util.UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

    // ArmorItem.ARMOR_MODIFIER_UUID_PER_SLOT[slotIndex]
    //int slotIndex = slot.getIndex(); // Feet=0, Legs=1, Chest=2, Head=3
    private static final java.util.UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new java.util.UUID[]{
            java.util.UUID.fromString("8452248B-1130-4C17-9154-015C8225BC14"),
            java.util.UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            java.util.UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            java.util.UUID.fromString("2AD3E313-BE5A-400A-8A29-644E65034B0B")
    };

    @SubscribeEvent
    public static void onItemAttributes(ItemAttributeModifierEvent event) {
        Item item = event.getItemStack().getItem();

        int[] weaponModifier6 = new int[] {0,0,0,1,1,2};
        int i;

        //changes all sword damage values
        if (item.builtInRegistryHolder().is(ItemTags.SWORDS)) {
            if (event.getSlotType() == EquipmentSlot.MAINHAND) {
                i = 0;
                for (Item swordMaterial : BaseItemCollectionUtil.BaseSwords) {
                    if (item == swordMaterial) {
                        event.removeAttribute(Attributes.ATTACK_DAMAGE);
                        event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                                BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                                1 + weaponModifier6[i], AttributeModifier.Operation.ADDITION));
                    }
                    i++;
                }
            } else if (event.getSlotType() == EquipmentSlot.OFFHAND) {
                i = 0;
                for (Item swordMaterial : BaseItemCollectionUtil.BaseSwords) {
                    event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                            BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                            Math.max((weaponModifier6[i]), 1), AttributeModifier.Operation.ADDITION));
                    i++;
                }
            }
        }

        //changes axes damage values
        if (item.builtInRegistryHolder().is(ItemTags.AXES)) {
            if (event.getSlotType() == EquipmentSlot.MAINHAND) {
                i = 0;
                for (Item swordMaterial : BaseItemCollectionUtil.BaseAxes) {
                    if (item == swordMaterial) {
                        event.removeAttribute(Attributes.ATTACK_DAMAGE);
                        event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                                BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                                1 + weaponModifier6[i], AttributeModifier.Operation.ADDITION));
                    }
                    i++;
                }
            }
        }

        //Adds Shield Armor Value
        if (item == BaseItemCollectionUtil.Shields[0]) {
            if (event.getSlotType() == EquipmentSlot.OFFHAND) {
                event.addModifier(Attributes.ARMOR, new AttributeModifier(
                    EQUIPABLE_MODIFIER_UUID_PER_SLOT[0], "Armor modifier",
                2, AttributeModifier.Operation.ADDITION));
            }
        }
        

    }
}
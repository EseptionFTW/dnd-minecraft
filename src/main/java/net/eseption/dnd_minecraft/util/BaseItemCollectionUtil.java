package net.eseption.dnd_minecraft.util;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

//BaseItemModificationUtil
public class BaseItemCollectionUtil {


    private static final java.util.UUID BASE_ATTACK_DAMAGE_UUID = java.util.UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private static final java.util.UUID BASE_ATTACK_SPEED_UUID = java.util.UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

    //int slotIndex = slot.getIndex(); // Feet=0, Legs=1, Chest=2, Head=3, Main=44, Off=5
    public static final java.util.UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new java.util.UUID[]{
            java.util.UUID.fromString("8452248B-1130-4C17-9154-015C8225BC14"),
            java.util.UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            java.util.UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            java.util.UUID.fromString("2AD3E313-BE5A-400A-8A29-644E65034B0B")
    };

    //Main=0, Off=1, Neck=2, Cape=3, Ring1=4, Ring2=5
    //capitalise later
    public static final java.util.UUID[] EQUIPABLE_MODIFIER_UUID_PER_SLOT = new java.util.UUID[] {
            java.util.UUID.fromString("94CBCB82-033A-4FCF-B9BE-4452B67E211E"),
            java.util.UUID.fromString("77F9E86B-BB18-4A6D-ADED-647AD9F85239"),
            java.util.UUID.fromString("d68ea139-8a59-4e6a-8d76-87f98c1ce357"),
            java.util.UUID.fromString("3db57d92-0e7b-49b1-8471-1669e17624ea"),
            java.util.UUID.fromString("adbd63c5-3411-4cf7-91fa-e90dc0a3613c"),
            java.util.UUID.fromString("5bb7f693-fc78-4999-bf33-9c8263f38c0c")
    };



    public static final Item[] BaseSwords = new Item[]{
            Items.WOODEN_SWORD,
            Items.STONE_SWORD,
            Items.GOLDEN_SWORD,
            Items.IRON_SWORD,
            Items.DIAMOND_SWORD,
            Items.NETHERITE_SWORD
    };

    public static final Item[] BasePickaxes = new Item[]{
            Items.WOODEN_PICKAXE,
            Items.STONE_PICKAXE,
            Items.GOLDEN_PICKAXE,
            Items.IRON_PICKAXE,
            Items.DIAMOND_PICKAXE,
            Items.NETHERITE_PICKAXE
    };

    public static final Item[] BaseAxes = new Item[]{
            Items.WOODEN_AXE,
            Items.STONE_AXE,
            Items.GOLDEN_AXE,
            Items.IRON_AXE,
            Items.DIAMOND_AXE,
            Items.NETHERITE_AXE
    };

    public static final Item[] BaseShovels = new Item[]{
            Items.WOODEN_SHOVEL,
            Items.STONE_SHOVEL,
            Items.GOLDEN_SHOVEL,
            Items.IRON_SHOVEL,
            Items.DIAMOND_SHOVEL,
            Items.NETHERITE_SHOVEL
    };

    public static final Item[] BaseHoes = new Item[]{
            Items.WOODEN_HOE,
            Items.STONE_HOE,
            Items.GOLDEN_HOE,
            Items.IRON_HOE,
            Items.DIAMOND_HOE,
            Items.NETHERITE_HOE
    };

    public static final Item[] BaseHelms = new Item[] {
            Items.LEATHER_HELMET,
            Items.GOLDEN_HELMET,
            Items.CHAINMAIL_HELMET,
            Items.TURTLE_HELMET,
            Items.IRON_HELMET,
            Items.DIAMOND_HELMET,
            Items.NETHERITE_HELMET
    };

    public static final Item[] BaseChestplates = new Item[] {
            Items.LEATHER_CHESTPLATE,
            Items.GOLDEN_CHESTPLATE,
            Items.CHAINMAIL_CHESTPLATE,
            Items.IRON_CHESTPLATE,
            Items.DIAMOND_CHESTPLATE,
            Items.NETHERITE_CHESTPLATE
    };

    public static final Item[] BaseLeggs = new Item[] {
            Items.LEATHER_LEGGINGS,
            Items.GOLDEN_LEGGINGS,
            Items.CHAINMAIL_LEGGINGS,
            Items.IRON_LEGGINGS,
            Items.DIAMOND_LEGGINGS,
            Items.NETHERITE_LEGGINGS
    };

    public static final Item[] BaseBoots = new Item[] {
            Items.LEATHER_BOOTS,
            Items.GOLDEN_BOOTS,
            Items.CHAINMAIL_BOOTS,
            Items.IRON_BOOTS,
            Items.DIAMOND_BOOTS,
            Items.NETHERITE_BOOTS
    };

    public static final Item[] Shields = new Item[] {
            Items.SHIELD
    };

    public static final EquipmentSlot[] Equipables = new EquipmentSlot[] {
            EquipmentSlot.MAINHAND,
            EquipmentSlot.OFFHAND
            //Neck
            //Cape
            //Ring1
            //Ring2
    };

}

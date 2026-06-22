package net.eseption.dnd_minecraft.item.curios.ring;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.eseption.dnd_minecraft.util.BaseItemCollectionUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class ProtectionRing extends Item implements ICurioItem {
    public ProtectionRing(Properties properties) {
        super(properties);
    }

    private static final AttributeModifier ARMOR_MODIFIER = new AttributeModifier(
            BaseItemCollectionUtil.EQUIPABLE_MODIFIER_UUID_PER_SLOT[3],
            "Curio Armor Modifier",
            2.0,
        AttributeModifier.Operation.ADDITION
    );

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player && !player.level().isClientSide()) {
            var armorAttribute = player.getAttribute(Attributes.ARMOR);
            if (armorAttribute != null && !armorAttribute.hasModifier(ARMOR_MODIFIER)){
                armorAttribute.addTransientModifier(ARMOR_MODIFIER);

            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player && !player.level().isClientSide()) {
            boolean isWearing = CuriosApi.getCuriosHelper().findFirstCurio(player, this).isPresent();
            var armourAttrubute = player.getAttribute(Attributes.ARMOR);
            if(!isWearing && armourAttrubute != null) {
                armourAttrubute.removeModifier(ARMOR_MODIFIER);
            }
        }
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {

        tooltip.add(Component.translatable("tooltip.dnd_minecraft.item.curio.protection_ring").withStyle(ChatFormatting.BLUE));
        super.appendHoverText(stack, level, tooltip, flagIn);
    }

    //adds armour to
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

        if (slotContext.identifier().equals("ring")) {
            //modifiers.put(Attributes.ARMOR, ARMOR_MODIFIER);
        }

        return modifiers;
    }

    //add void or make tag and access tag in other classes to make saving throw bonus

    @Override
    public boolean hasCurioCapability(ItemStack stack) {
        return ICurioItem.super.hasCurioCapability(stack);
    }
}

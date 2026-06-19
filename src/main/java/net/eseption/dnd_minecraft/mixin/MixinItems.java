package net.eseption.dnd_minecraft.mixin;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class MixinItems {

    @Inject(method = "getDefaultAttributeModifiers", at = @At(value = "RETURN"), cancellable = true)
    protected void overrideAttributeModifiers(EquipmentSlot slot, CallbackInfoReturnable<Multimap<Attribute, AttributeModifier>> cir) {
        Item item = (Item)((Object) this);

        Multimap<Attribute, AttributeModifier> modifiers = cir.getReturnValue();

        Multimap<Attribute, AttributeModifier> newModifiers = HashMultimap.create(modifiers);

        /*//if its in hand and it has sword tag
        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND) {
            if (item.builtInRegistryHolder().is(ItemTags.SWORDS)) {
                newModifiers.removeAll(Attributes.ATTACK_DAMAGE);

                newModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                        Item.BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 20.0D, AttributeModifier.Operation.ADDITION));
            }
        }*/





    }



}

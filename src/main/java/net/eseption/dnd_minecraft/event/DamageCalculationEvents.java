package net.eseption.dnd_minecraft.event;

import net.eseption.dnd_minecraft.util.ModifierCalculatorUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DamageCalculationEvents {

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        DamageSource damageSource = event.getSource();
        float damage = 1;

        //need to make sure all entities can use this, will be done after tooltip changes
        //to be used in replacement of attribute swapping when relevant classes come into service
        if (damageSource.getEntity() instanceof Player player) {
            ItemStack mainhandWeapon = player.getMainHandItem();
            ItemStack offhandWeapon = player.getOffhandItem();

            damage = mainhandWeapon.getDamageValue() + ModifierCalculatorUtil.getModifierOnType(player, 1);

            System.out.println("Damage : " + damage + " of Weapon - " + mainhandWeapon);
        }
        System.out.println("Damage : " + damage + " of Weapon - ");
        event.setAmount(damage);
    }
}

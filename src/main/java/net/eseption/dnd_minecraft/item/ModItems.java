package net.eseption.dnd_minecraft.item;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.item.curios.ring.ProtectionRing;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {


    //add moditemgroup ???
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Dnd_minecraft.MODID);

    public static final RegistryObject<Item> PROTECTION_RING = ITEMS.register("protection_ring",
            () -> new ProtectionRing(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

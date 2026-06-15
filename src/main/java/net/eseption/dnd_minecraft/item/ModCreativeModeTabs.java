package net.eseption.dnd_minecraft.item;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.network.chat.Component;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> Creative_Mode_Tabs =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Dnd_minecraft.MODID);

    public static final RegistryObject<CreativeModeTab> DND_MINECRAFT_TA = Creative_Mode_Tabs.register("dnd_minecraft_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.STICK.asItem()))
                    .title(Component.translatable("creativetab.dnd_mod_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(Items.STICK.asItem());

                    })
                    .build());

    public static void register(IEventBus eventBus) {Creative_Mode_Tabs.register(eventBus);}
}
package net.eseption.dnd_minecraft.client.inventory;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Dnd_minecraft.MODID);

    public static final RegistryObject<MenuType<CharacterScreenMenu>> CHARACTER_SCREEN_MENU =
            MENUS.register("character_screen",
                    () -> IForgeMenuType.create(
                            (id, inv, buf) ->
                                    new CharacterScreenMenu(id, inv)
                    ));

}

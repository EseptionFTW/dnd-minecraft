package net.eseption.dnd_minecraft.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindingUtil {
    public static final String KEY_CATEGORY_DND_MINECRAFT = "key.category.dnd_minecraft.dnd_minecraft";
    public static final String KEY_OPEN_CHARACTER_SHEET = "key.dnd_minecraft.open_character_sheet";
    public static final String KEY_OPEN_BESTIARY_MENU = "key.dnd_minecraft.open_bestiary_menu";
    public static final String KEY_OPEN_SKILLS_MENU = "key.dnd_minecraft.open_skills_menu";
    public static final String KEY_OPEN_SPELLS_MENU = "key.dnd_minecraft.open_spells_menu";
    public static final String KEY_OPEN_LEVEL_MENU = "key.dnd_minecraft.open_level_menu";

    public static final KeyMapping OPEN_CHARACTER_SHEET =
            new KeyMapping(KEY_OPEN_CHARACTER_SHEET, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_DND_MINECRAFT);
    public static final KeyMapping OPEN_BESTIARY_MENU =
            new KeyMapping(KEY_OPEN_BESTIARY_MENU, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORY_DND_MINECRAFT);
    public static final KeyMapping OPEN_SKILLS_MENU =
            new KeyMapping(KEY_OPEN_SKILLS_MENU, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_DND_MINECRAFT);
    public static final KeyMapping OPEN_SPELLS_MENU =
            new KeyMapping(KEY_OPEN_SPELLS_MENU, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY_DND_MINECRAFT);
    public static final KeyMapping OPEN_LEVEL_MENU =
            new KeyMapping(KEY_OPEN_LEVEL_MENU, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_L, KEY_CATEGORY_DND_MINECRAFT);

}

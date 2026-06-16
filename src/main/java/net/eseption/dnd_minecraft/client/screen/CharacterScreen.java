package net.eseption.dnd_minecraft.client.screen;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CharacterScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen");

    private final int imageWidth, imageHeight;
    private int leftPos, topPos;

    public CharacterScreen() {
        super(TITLE);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
    }
}

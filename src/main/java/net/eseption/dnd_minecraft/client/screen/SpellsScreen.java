package net.eseption.dnd_minecraft.client.screen;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SpellsScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui." + Dnd_minecraft.MODID + ".spells_screen");

    private final int imageWidth, imageHeight;
    private int leftPos, topPos;

    public SpellsScreen() {
        super(TITLE);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }
}
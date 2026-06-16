package net.eseption.dnd_minecraft.client.screen;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BaseScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui." + Dnd_minecraft.MODID + ".base_screen");

    //For Block Entity Screens
    //private final BlockPos blockPos;
    //private BlockEntity blockEntity;
    //private CraftingAlterBlockEntity blockEntity;


    private final int imageWidth, imageHeight;

    private int leftPos, topPos;

    public BaseScreen() {
        super(TITLE);

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
    }
}

package net.eseption.dnd_minecraft.client.screen;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.client.inventory.CharacterScreenMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;

public class TempScreen extends AbstractContainerScreen<CharacterScreenMenu> {
    private static final Component TITLE = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen");
    private static final Component NULL = Component.literal("");
    private static final ResourceLocation BACKGROUND = new ResourceLocation(Dnd_minecraft.MODID, "textures/gui/character_screen1.png");

    private static int str;
    private static int dex;
    private static int con;
    private static int inte;
    private static int wis;
    private static int cha;
    private static int ac;
    private static int lvl;
    private static int lxp;
    private static int xp;

    private final int imageWidth, imageHeight;
    private int leftPos, topPos;

    //abstract container, inventory linked to it, title, width, height
    public TempScreen(CharacterScreenMenu playerMenu, Inventory playerInventory, Component component) {
        super(playerMenu, playerInventory, NULL);
        this.imageWidth = 220;
        this.imageHeight = 222;
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        if (this.minecraft == null) {return;}
        Level level = this.minecraft.level;
        if( level == null) {return;}

        LocalPlayer player = this.minecraft.player;
        if (player == null) {return;}

    }

    @Override
    protected void renderBg(GuiGraphics graphic, float partialTicks, int mouseX, int mouseY) {
        renderBackground(graphic);
        graphic.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }


    //potentially useful methods

    //for changing the color of highlighted slots
    private int slotColor = -2130706433;

    @Override
    public int getSlotColor(int index) {
        return super.getSlotColor(index);
    }

    public void setSlotColor(int slotColor) {
        this.slotColor = slotColor;
    }
}
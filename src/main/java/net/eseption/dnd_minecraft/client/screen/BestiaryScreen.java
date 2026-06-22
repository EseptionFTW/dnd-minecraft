package net.eseption.dnd_minecraft.client.screen;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.eseption.dnd_minecraft.util.StatUpdaterUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.joml.Quaternionf;

public class BestiaryScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui." + Dnd_minecraft.MODID + ".bestiary_screen");
    private static final ResourceLocation BACKGROUND = new ResourceLocation(Dnd_minecraft.MODID, "textures/gui/bestiary.png");
    private static final ResourceLocation BACKGROUND_BLANK = new ResourceLocation(Dnd_minecraft.MODID, "textures/gui/blank_inventory.png");

    private static int str;
    private static int dex;
    private static int con;
    private static int inte;
    private static int wis;
    private static int cha;
    private static int ac;
    private static int lvl;

    private final int imageWidth, imageHeight;
    private int leftPos, topPos;
    private LivingEntity targetEntity;

    private Button selectedEntity;
    private Button defaultBestiary;

    public BestiaryScreen(LivingEntity entity) {
        super(TITLE);
        this.imageWidth = 220;
        this.imageHeight = 222;
        this.targetEntity = entity;
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        if (this.minecraft == null) {return;}
        Level level = this.minecraft.level;
        if( level == null) {return;}

    }

    @Override
    public void render(GuiGraphics graphic, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphic);
        if (this.minecraft == null) {return;}
        LivingEntity entity = this.targetEntity;
        if (entity == null) {
            graphic.blit(BACKGROUND_BLANK, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        } else {
            graphic.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        }
        //super.render()
        for(Renderable renderable : this.renderables) {
            renderable.render(graphic, mouseX, mouseY, partialTicks);
        }

        //title
        graphic.drawString(this.font, TITLE, this.leftPos + 63, this.topPos + 7, 0x404040, false);

        if (entity == null) {;

            graphic.drawString(this.font, "Test", this.leftPos + 100, this.topPos + 50, 0x404040, false);


        } else {
            //Render entity in inventory
            InventoryScreen.renderEntityInInventory(graphic, this.leftPos, this.topPos, 30, new Quaternionf(0, 0, 0, 1), new Quaternionf(0, 0, 0, 1), entity);

            entity.getCapability(EntityStatsProvider.ENTITY_STATS).ifPresent(stats -> {
                StatUpdaterUtil.updateStats(entity, stats);
                str = stats.getStrength();
                dex = stats.getDexterity();
                con = stats.getConstitution();
                inte = stats.getIntelligence();
                wis = stats.getWisdom();
                cha = stats.getCharisma();
                ac = stats.getArmorClass();
                lvl = stats.getLevel();
            });

            Component STR_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.strength", str);
            Component DEX_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.dexterity", dex);
            Component CON_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.constitution", con);
            Component INT_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.intelligence", inte);
            Component WIS_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.wisdom", wis);
            Component CHA_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.charisma", cha);
            Component AC_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.armor_class", ac);

            Component LVL_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.level", lvl);

            //Ability Scores
            graphic.drawString(this.font, STR_TEXT, this.leftPos + 101, this.topPos + 25, 0x404040, false);
            graphic.drawString(this.font, DEX_TEXT, this.leftPos + 101, this.topPos + 40, 0x404040, false);
            graphic.drawString(this.font, CON_TEXT, this.leftPos + 101, this.topPos + 55, 0x404040, false);
            graphic.drawString(this.font, INT_TEXT, this.leftPos + 101, this.topPos + 70, 0x404040, false);
            graphic.drawString(this.font, WIS_TEXT, this.leftPos + 101, this.topPos + 85, 0x404040, false);
            graphic.drawString(this.font, CHA_TEXT, this.leftPos + 101, this.topPos + 100, 0x404040, false);

            //Armor Class
            graphic.drawString(this.font, AC_TEXT, this.leftPos + 34, this.topPos + 98, 0x404040, false);

            //Level
            graphic.drawString(this.font, LVL_TEXT, this.leftPos + 12, this.topPos + 115, 0x404040, false);

            //Default Bestiary button
            this.defaultBestiary = addRenderableWidget(
                    Button.builder(
                            TITLE,
                            this::defaultBestiaryScreen)
                            .bounds(this.leftPos + 171, this.topPos + 195, 45, 20)
                            .build()
            );

        }
    }

    private void selectEntityHandler(Button button) {
        LivingEntity renderedHoverEntity;
        //entity = get entity from renderer underneath button???
        //bool? so else if entity != null || selectEntity == true
        //button. get x and y pos to collect rendered entity

        //assert minecraft != null;
        //minecraft.setScreen(new BestiaryScreen(renderedHoverEntity));

    }

    private void defaultBestiaryScreen(Button button) {
        assert minecraft != null;
        minecraft.setScreen(new BestiaryScreen(null));
    }


    @Override
    public boolean isPauseScreen() {
        return super.isPauseScreen();
    }
}
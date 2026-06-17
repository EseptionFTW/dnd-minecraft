package net.eseption.dnd_minecraft.client.screen;

import net.eseption.dnd_minecraft.Dnd_minecraft;
import net.eseption.dnd_minecraft.capability.EntityStatsProvider;
import net.eseption.dnd_minecraft.util.StatUpdater;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import org.joml.Quaternionf;

public class CharacterScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen");
    private static final ResourceLocation BACKGROUND = new ResourceLocation(Dnd_minecraft.MODID, "textures/gui/character_screen1.png");

    private static final Component NEXT_PAGE_BUTTON = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.next_page_button");

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

    //AbstractContainerMenu menu, Inventory playerInventory, Component TITLE
    public CharacterScreen() {
        super(TITLE);
        this.imageWidth = 220;
        this.imageHeight = 222;
    }

    private Button button;

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

        this.button = addRenderableWidget(
                Button.builder(
                        NEXT_PAGE_BUTTON,
                        this::nextPageButtonHandler)
                        .bounds(this.leftPos + 173, this.topPos + 195 , 40, 20)
                        //.tooltip(Tooltip.create(NEXT_PAGE_BUTTON)) use for stats to say strength ect (this is not dynamic)
                        .build()
        );

        //EditBox
        //ScrollPanel
    }

    @Override
    public void render(GuiGraphics graphic, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphic);
        graphic.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        //super.render()
        for(Renderable renderable : this.renderables) {
            renderable.render(graphic, mouseX, mouseY, partialTicks);
        }

        //false for shadow
        //title (font, component, left->right (Xpos), top->bottom (Ypos), color, shadow)
        graphic.drawString(this.font, TITLE, this.leftPos + 63, this.topPos + 7, 0x404040, false);

        if (this.minecraft == null) {return;}
        LocalPlayer player = this.minecraft.player;
        if (player == null) {return;}

        //Render player in inventory
        InventoryScreen.renderEntityInInventory(graphic, this.leftPos, this.topPos, 30, new Quaternionf(0,0,0,1), new Quaternionf(0,0,0,1), player);

        player.getCapability(EntityStatsProvider.ENTITY_STATS).ifPresent(stats -> {
            StatUpdater.updateStats(player, stats);
            str = stats.getStrength();
            dex = stats.getDexterity();
            con = stats.getConstitution();
            inte =  stats.getIntelligence();
            wis = stats.getWisdom();
            cha = stats.getCharisma();
            ac = stats.getArmorClass();
            lvl = stats.getLevel();
            lxp = stats.getXpToLevel();
            xp = stats.getXp();
        });


        Component STR_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.strength", str);
        Component DEX_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.dexterity", dex);
        Component CON_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.constitution", con);
        Component INT_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.intelligence", inte);
        Component WIS_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.wisdom", wis);
        Component CHA_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.charisma", cha);
        Component AC_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.armor_class", ac);

        Component LVL_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.level", lvl);
        Component LXP_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.level_xp", lxp);
        Component XP_TEXT = Component.translatable("gui." + Dnd_minecraft.MODID + ".character_screen.listing.xp", xp);

        //graphic.drawCenteredString(this.font, TITLE, );

        //Ability Scores
        graphic.drawString(this.font, STR_TEXT, this.leftPos + 101, this.topPos + 25, 0x404040, false);
        graphic.drawString(this.font, DEX_TEXT, this.leftPos + 101, this.topPos + 40, 0x404040, false);
        graphic.drawString(this.font, CON_TEXT, this.leftPos + 101, this.topPos + 55, 0x404040, false);
        graphic.drawString(this.font, INT_TEXT, this.leftPos + 101, this.topPos + 70, 0x404040, false);
        graphic.drawString(this.font, WIS_TEXT, this.leftPos + 101, this.topPos + 85, 0x404040, false);
        graphic.drawString(this.font, CHA_TEXT, this.leftPos + 101, this.topPos + 100, 0x404040, false);

        //Armor Class
        graphic.drawString(this.font, AC_TEXT, this.leftPos + 34, this.topPos + 98, 0x404040, false);

        //Levels
        graphic.drawString(this.font, LVL_TEXT, this.leftPos + 12, this.topPos + 115, 0x404040, false);
        graphic.drawString(this.font, LXP_TEXT, this.leftPos + 148, this.topPos + 127, 0x404040, false);
        graphic.drawString(this.font, XP_TEXT, this.leftPos + 12, this.topPos + 127, 0x404040, false);

        //Statuses
        int yOffset = 0;
        String timeString = "--:--";
        for (MobEffectInstance effect : player.getActiveEffects()) {
            TextureAtlasSprite textureAtlasSprite = Minecraft.getInstance().getMobEffectTextures().get(effect.getEffect());
            Component effectName = effect.getEffect().getDisplayName();
            MutableComponent tooltipText = effectName.copy();
            if (effect.getAmplifier() > 0 && effect.getAmplifier() < 6) {
                tooltipText.append(" ").append(Component.translatable("potion.potency." + effect.getAmplifier()));
            }

            //x, y, z, width, height, atlasSprite
            //make sure not to display more than what is available 8 effect slots available, constantly refreshing due to active loop
            if (yOffset < 160) {
                //draw effect texture
                graphic.blit(this.leftPos + 174, this.topPos + 26 + yOffset, 0, 11, 11, textureAtlasSprite);
                //draw effect name + amplifier
                if ((mouseX >= this.leftPos + 174) && (mouseX < this.leftPos + 174 + 11) &&
                        (mouseY >= this.topPos + 26 + yOffset) && (mouseY < this.leftPos + 26 + yOffset + 11)) {
                    graphic.renderTooltip(this.font, tooltipText, this.leftPos + 160, this.topPos + 40 + yOffset);
                }
                if (effect.getDuration() == -1) {
                    timeString = "  ∞";
                } else {
                    //calculates minutes and seconds
                    timeString = String.format("%02d:%02d", Math.floorDiv(effect.getDuration()/20, 60), Math.floorMod(effect.getDuration()/20 ,60));
                }

                //draw effect duration
                graphic.drawString(this.font, timeString,this.leftPos + 187, this.topPos + 28 + yOffset, 0x404040, false);
                yOffset += 20;
            }
        }


    }

    private void nextPageButtonHandler(Button button) {
        //render next page
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

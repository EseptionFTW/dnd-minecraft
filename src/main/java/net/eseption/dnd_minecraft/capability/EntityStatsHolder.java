package net.eseption.dnd_minecraft.capability;

import net.minecraft.nbt.CompoundTag;

//holds default entity stats and entity stats editing and nbt saving functions

public class EntityStatsHolder {
    private int strength = 10;
    private int dexterity = 10;
    private int constitution = 10;
    private int intelligence = 10;
    private int wisdom = 10;
    private int charisma = 10;

    private int bonusStrength = 10;
    private int bonusDexterity = 10;
    private int bonusConstitution = 10;
    private int bonusIntelligence = 10;
    private int bonusWisdom = 10;
    private int bonusCharisma = 10;

    private int level = 1;
    private int xp = 0;
    private int xpToLevel = 100 * level;

    private int armorClass = 10;

    private boolean initialised = false;


    public void copyData(EntityStatsHolder curStats) {
        this.strength = curStats.strength;
        this.dexterity = curStats.dexterity;
        this.constitution = curStats.constitution ;
        this.intelligence = curStats.intelligence ;
        this.wisdom = curStats.wisdom ;
        this.charisma = curStats.charisma ;

        this.level = curStats.level ;
        this.xp = curStats.xp ;
        this.xpToLevel = curStats.xpToLevel ;

        this.armorClass = curStats.armorClass;
    }

    public CompoundTag saveStatNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putInt("STR", strength);
        tag.putInt("DEX", dexterity);
        tag.putInt("CON", constitution);
        tag.putInt("INT", intelligence);
        tag.putInt("WIS", wisdom);
        tag.putInt("CHA", charisma);

        tag.putInt("LVL", level);
        tag.putInt("XP", xp);
        tag.putInt("LevelXP", xpToLevel);

        tag.putInt("AC", armorClass);
        return tag;
    }

    public void loadStatNBT(CompoundTag tag) {
        strength = tag.getInt("STR");
        dexterity = tag.getInt("DEX");
        constitution = tag.getInt("CON");
        intelligence = tag.getInt("INT");
        wisdom = tag.getInt("WIS");
        charisma = tag.getInt("CHA");

        level = tag.getInt("LVL");
        xp = tag.getInt("XP");
        xpToLevel = tag.getInt("LevelXP");

        armorClass = tag.getInt("AC");
    }

    public void setAll(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, int level, int armorClass) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.level = level;
        this.armorClass = armorClass;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getXpToLevel() {
        return xpToLevel;
    }

    public void setXpToLevel(int xpToLevel) {
        this.xpToLevel = 100 * level;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public boolean isInitialised() {
        return initialised;
    }

    public void setInitialised(boolean initialised) {
        this.initialised = initialised;
    }
}

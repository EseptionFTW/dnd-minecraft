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

    private int bonusStrength = 0;
    private int bonusDexterity = 0;
    private int bonusConstitution = 0;
    private int bonusIntelligence = 0;
    private int bonusWisdom = 0;
    private int bonusCharisma = 0;

    private int level = 1;
    private int xp = 0;
    private int xpToLevel = 100 * level;

    private int proficiency = 2; //might need to save this data into nbt below and server

    private int naturalArmorClass = 10;
    private int armorClass = 0;


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

    //Strength
    public int getBaseStrength() {
        return strength;
    }
    public int getBonusStrength() {return bonusStrength;}
    public int getStrength() {
        return Math.max(strength + bonusStrength, 0);
    }

    public void setBaseStrength(int strength) {
        this.strength = strength;
    }
    public void setBonusStrength(int bonusStrength) {this.bonusStrength = bonusStrength;}

    //Dexterity
    public int getBaseDexterity() {
        return dexterity;
    }
    public int getBonusDexterity() {
        return bonusDexterity;
    }
    public int getDexterity() {
        return Math.max(dexterity + bonusDexterity, 0);
    }

    public void setBaseDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public void setBonusDexterity(int bonusDexterity) {
        this.bonusDexterity = bonusDexterity;
    }

    //Constitution
    public int getBaseConstitution() {
        return constitution;
    }
    public int getBonusConstitution() {
        return bonusConstitution;
    }
    public int getConstitution() {
        return Math.max(constitution + bonusConstitution, 0);
    }

    public void setBaseConstitution(int constitution) {
        this.constitution = constitution;
    }
    public void setBonusConstitution(int bonusConstitution) {
        this.bonusConstitution = bonusConstitution;
    }

    //Intelligence
    public int getBaseIntelligence() {
        return intelligence;
    }
    public int getBonusIntelligence() {
        return bonusIntelligence;
    }
    public int getIntelligence() {
        return Math.max(intelligence + bonusIntelligence, 0);
    }

    public void setBaseIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setBonusIntelligence(int bonusIntelligence) {
        this.bonusIntelligence = bonusIntelligence;
    }

    //Wisdom
    public int getBaseWisdom() {
        return wisdom;
    }
    public int getBonusWisdom() {
        return bonusWisdom;
    }
    public int getWisdom() {return Math.max(wisdom + bonusWisdom, 0);}

    public void setBaseWisdom(int wisdom) {this.wisdom = wisdom;}
    public void setBonusWisdom(int bonusWisdom) {this.bonusWisdom = bonusWisdom;}

    //Charisma
    public int getBaseCharisma() {return charisma;}
    public int getBonusCharisma() {return bonusCharisma;}
    public int getCharisma() {return Math.max(charisma + bonusCharisma, 0);}

    public void setBaseCharisma(int charisma) {this.charisma = charisma;}
    public void setBonusCharisma(int bonusCharisma) {this.bonusCharisma = bonusCharisma;}

    //Armor Class
    public int getArmorClass() {return armorClass;}
    public int getNaturalArmorClass() {return this.naturalArmorClass;}

    public void setArmorClass(int armorClass) {this.armorClass = armorClass;}
    public void setNaturalArmorClass(int naturalArmorClass) {this.naturalArmorClass = naturalArmorClass;}



    //Levels


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



    public boolean isInitialised() {
        return initialised;
    }

    public void setInitialised(boolean initialised) {
        this.initialised = initialised;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }
}

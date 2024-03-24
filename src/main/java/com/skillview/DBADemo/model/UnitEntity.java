package com.skillview.DBADemo.model;

public class UnitEntity {

    String name;

    String type;

    Integer combatFoot;
    Integer combatMounted;

    Integer movementGG;
    Integer movementBG;
    String special;


    // Default constructor
    public UnitEntity() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCombatFoot() {
        return combatFoot;
    }

    public void setCombatFoot(Integer combatFoot) {
        this.combatFoot = combatFoot;
    }

    public Integer getCombatMounted() {
        return combatMounted;
    }

    public void setCombatMounted(Integer combatMounted) {
        this.combatMounted = combatMounted;
    }

    public Integer getMovementGG() {
        return movementGG;
    }

    public void setMovementGG(Integer movementGG) {
        this.movementGG = movementGG;
    }

    public Integer getMovementBG() {
        return movementBG;
    }

    public void setMovementBG(Integer movementBG) {
        this.movementBG = movementBG;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}

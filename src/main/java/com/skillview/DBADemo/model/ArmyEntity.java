package com.skillview.DBADemo.model;

import java.util.ArrayList;
import java.util.List;

public class ArmyEntity {

    private List<List<UnitEntity>> army;

    public ArmyEntity() {
        this.army = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            this.army.add(new ArrayList<>());
        }
    }

    public ArmyEntity(List<List<UnitEntity>> army) {
        this.army = army;
    }


    public void addUnitToSlot(int slotIndex, UnitEntity unit) {
        if (slotIndex >= 0 && slotIndex < 12) {
            this.army.get(slotIndex).add(unit);
        } else {
            throw new IllegalArgumentException("Invalid slot index: " + slotIndex);
        }
    }

    // Getter method to retrieve units in a specific slot
    public List<UnitEntity> getUnitsInSlot(int slotIndex) {
        if (slotIndex >= 0 && slotIndex < 12) {
            return this.army.get(slotIndex);
        } else {
            throw new IllegalArgumentException("Invalid slot index: " + slotIndex);
        }
    }

    public void printArmyDetails() {
        for (int i = 0; i < 12; i++) {
            System.out.println("Slot " + (i + 1) + ":");
            List<UnitEntity> units = army.get(i);
            if (!units.isEmpty()) {
                for (UnitEntity unit : units) {
                    System.out.println(unit);
                }
            } else {
                System.out.println("No units in this slot");
            }
            System.out.println();
        }
    }

    public List<List<UnitEntity>> getArmy() {
        return army;
    }

    public void setArmy(List<List<UnitEntity>> army) {
        this.army = army;
    }
}

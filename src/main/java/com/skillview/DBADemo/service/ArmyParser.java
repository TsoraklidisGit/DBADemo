package com.skillview.DBADemo.service;

import com.skillview.DBADemo.model.ArmyEntity;
import com.skillview.DBADemo.model.UnitEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class ArmyParser {

    @Autowired
    UnitParser unitParser;

    public ResponseEntity<Object> readJsonFile(String path) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            ArmyEntity army = parseArmy(jsonObject);
            return ResponseEntity.ok(army);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to parse JSON file: " + e.getMessage());
        }
    }
    public ArmyEntity parseArmy(JSONObject armyObject) {
        ArmyEntity army = new ArmyEntity();

        JSONArray slotsArray = (JSONArray) armyObject.get("army");
        for (int i = 0; i < slotsArray.size(); i++) {
            JSONArray slotUnitsArray = (JSONArray) slotsArray.get(i);
            for (int j = 0; j < slotUnitsArray.size(); j++) {
                JSONObject unitObject = (JSONObject) slotUnitsArray.get(j);
                UnitEntity unit = unitParser.parseUnit(unitObject);
                army.addUnitToSlot(i, unit);
            }
        }

        printArmy(army); // Print the army details
        return army;
    }



    private void printArmy(ArmyEntity army) {
        // Print army details
        System.out.println("Printing Army Details:");
        for (int i = 0; i < 12; i++) { // Assuming 12 slots
            List<UnitEntity> units = army.getUnitsInSlot(i);
            System.out.println("Slot " + (i + 1) + ":");
            if (!units.isEmpty()) {
                if (units.size() > 1) {
                    for (int j = 0; j < units.size(); j++) {
                        UnitEntity unit = units.get(j);
                        System.out.println("Choice " + (j + 1) + ":");
                        printUnitDetails(unit);
                    }
                } else {
                    printUnitDetails(units.get(0));
                }
            } else {
                System.out.println("No units in this slot.");
            }
        }
    }

    private void printUnitDetails(UnitEntity unit) {
        System.out.println("Unit: " + unit.getName());
        System.out.println("Type: " + unit.getType());
        System.out.println("Combat (Foot): " + unit.getCombatFoot());
        System.out.println("Combat (Mounted): " + unit.getCombatMounted());
        System.out.println("Movement (Good Going): " + unit.getMovementGG());
        System.out.println("Movement (Bad Going): " + unit.getMovementBG());
        System.out.println("Special: " + unit.getSpecial());
        System.out.println();
    }
}

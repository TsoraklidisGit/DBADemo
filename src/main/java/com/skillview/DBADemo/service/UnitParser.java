package com.skillview.DBADemo.service;

import com.skillview.DBADemo.model.UnitEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class UnitParser {

    public ResponseEntity<List<UnitEntity>> readJsonFile(String path) {
        JSONParser jsonParser = new JSONParser();
        List<UnitEntity> units = new ArrayList<>();
        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);

            if (obj instanceof JSONObject) {
                // If the JSON content is an object, parse it as a single unit
                JSONObject jsonObject = (JSONObject) obj;
                UnitEntity unit = parseUnit(jsonObject);
                units.add(unit);
                printUnitDetails(unit);
            } else if (obj instanceof JSONArray) {
                // If the JSON content is an array, parse it as an array of units
                JSONArray jsonArray = (JSONArray) obj;
                for (Object unitObj : jsonArray) {
                    JSONObject unitJson = (JSONObject) unitObj;
                    UnitEntity unit = parseUnit(unitJson);
                    units.add(unit);
                    printUnitDetails(unit);
                }
            } else {
                // Unsupported JSON format
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

            return ResponseEntity.ok(units);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public UnitEntity parseUnit(JSONObject unitObject) {
        UnitEntity unit = new UnitEntity();

        // Reflection to set fields dynamically
        Field[] fields = UnitEntity.class.getDeclaredFields();
        // Iterate over each field
        for (Field field : fields) {
            String fieldName = field.getName();
            // Get the value of the field from the JSON object
            Object value = unitObject.get(fieldName);
            // Set the value of the field in the entity
            if (value != null) {
                try {
                    field.setAccessible(true);
                    if (field.getType().equals(Integer.class)) {
                        field.set(unit, ((Number) value).intValue());
                    } else {
                        field.set(unit, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return unit;
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

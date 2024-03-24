package com.skillview.DBADemo.controller;

import com.skillview.DBADemo.model.UnitEntity;
import com.skillview.DBADemo.service.ArmyParser;
import com.skillview.DBADemo.service.UnitParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonController {

    @Autowired
    private final UnitParser unitParser;

    @Autowired
    private final ArmyParser armyParser;

    public JsonController(UnitParser unitParser, ArmyParser armyParser) {
        super();
        this.unitParser = unitParser;
        this.armyParser = armyParser;
    }


    @GetMapping(path = "/unit")
    public ResponseEntity<List<UnitEntity>> getUnit() {
        String path = "/Users/at/IdeaProjects/DBADemo/src/main/resources/json/unitEntity.json";
        return unitParser.readJsonFile(path);
    }

    @GetMapping(path = "/army")
    public ResponseEntity<Object> getArmy(){
        String path = "/Users/at/IdeaProjects/DBADemo/src/main/resources/json/armyEntity.json";
        return armyParser.readJsonFile(path);
    }
}

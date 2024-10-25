package org.example.virtualregulatorcontroller.controller;

import org.example.service.Regulator; //*
import org.example.service.VirtualRegulatorService; //*
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class RegulatorController {

    private final Regulator regulator;
    public RegulatorController() {
        this.regulator = new VirtualRegulatorService();;
    }

    @PostMapping("/set")
    public ResponseEntity<Void> setTemperature(@RequestHeader("test") float inData) {
        regulator.adjustTemp((byte) 0b00000100, inData , null, 1);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/current")
    public ResponseEntity<Integer> getCurrentTemperature(@RequestHeader("test") float inData) {
        return ResponseEntity.ok(regulator.adjustTemp((byte) 0b00010000, inData, null, 1));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Integer>> getRecent(@RequestHeader("test") float inData, @RequestHeader("test") int offsetOut) {
        return ResponseEntity.ok(Collections.singletonList(regulator.adjustTemp((byte) 0b00010000, inData, null, offsetOut)));
    }

    @PostMapping("/clear")
    public ResponseEntity<Void> clearList(@RequestHeader("test") int inData) {
        regulator.adjustTemp((byte) 0b10000000, inData, null, 3);
        return ResponseEntity.ok().build();
    }
}

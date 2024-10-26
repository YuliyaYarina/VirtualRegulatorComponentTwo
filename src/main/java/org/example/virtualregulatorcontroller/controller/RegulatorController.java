package org.example.virtualregulatorcontroller.controller;

import org.example.service.Regulator; //*
import org.example.service.VirtualRegulatorService; //*
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegulatorController {

    private final Regulator regulator;

    public RegulatorController() {
        this.regulator = new VirtualRegulatorService();;
    }

    @PostMapping("/set")
    public ResponseEntity<Integer> setTemperature(@RequestHeader("test") List<Float> outData) {
        return ResponseEntity.ok(regulator.setTemperatura(outData));
    }

    @GetMapping("/current")
    public ResponseEntity<Integer> getCurrentTemperature() {
        return ResponseEntity.ok(regulator.getValuesTemperature());
    }

    @GetMapping("/recent")
    public ResponseEntity<Integer> getRecent( @RequestHeader("test") int offsetOut) {
        return ResponseEntity.ok(regulator.getLastValues(offsetOut));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Integer> clearList() {
        return ResponseEntity.ok(regulator.deleteValuesTemperature());
    }
}

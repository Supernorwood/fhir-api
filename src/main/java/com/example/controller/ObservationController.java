package com.example.controller;

import java.util.List;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fhir/Observation")
public class ObservationController {

    @GetMapping("/{id}")
    public ResponseEntity<Observation> getObservationById(@PathVariable("id") String id) {
        Observation observation = new Observation();
        observation.setId(id);
        observation.setStatus(Observation.ObservationStatus.FINAL);
        observation.setCategory((List<CodeableConcept>) new CodeableConcept().addCoding(new Coding(
                "http://terminology.hl7.org/CodeSystem/observation-category", "vital-signs", "Vital Signs")));
        observation.setCode(new CodeableConcept().addCoding(new Coding("http://loinc.org", "8867-4", "Heart rate")));
        return new ResponseEntity<>(observation, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Observation> createObservation(@RequestBody Observation observation) {
        // Assume the observation is created and return the object with an ID
        observation.setId("1");
        return new ResponseEntity<>(observation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Observation> updateObservation(@PathVariable("id") String id,
            @RequestBody Observation observation) {
        // For simplicity, assume the observation is updated with the provided data
        observation.setId(id);
        return new ResponseEntity<>(observation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObservation(@PathVariable("id") String id) {
        // Assume the observation with this ID is deleted
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
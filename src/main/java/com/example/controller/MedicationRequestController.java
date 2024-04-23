package com.example.controller;

import org.hl7.fhir.r4.model.MedicationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fhir/MedicationRequest")
public class MedicationRequestController {

    @GetMapping("/{id}")
    public ResponseEntity<MedicationRequest> getMedicationRequestById(@PathVariable("id") String id) {
        MedicationRequest medicationRequest = new MedicationRequest();
        medicationRequest.setId(id);
        medicationRequest.setStatus(MedicationRequest.MedicationRequestStatus.ACTIVE);
        // Here we could add more details such as prescribing doctor, medication
        // details, etc.
        return new ResponseEntity<>(medicationRequest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicationRequest> createMedicationRequest(@RequestBody MedicationRequest medicationRequest) {
        // Assume the medication request is created and return the same object with an
        // ID
        medicationRequest.setId("1");
        return new ResponseEntity<>(medicationRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationRequest> updateMedicationRequest(@PathVariable("id") String id,
            @RequestBody MedicationRequest medicationRequest) {
        // For simplicity, assume the medication request is updated with the provided
        // data
        medicationRequest.setId(id);
        return new ResponseEntity<>(medicationRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicationRequest(@PathVariable("id") String id) {
        // Assume the medication request with this ID is deleted
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
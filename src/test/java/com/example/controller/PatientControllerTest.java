package com.example.controller;

import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Test
    public void getPatientById_whenCalledWithValidId_returnsPatient() {
        String patientId = "1";

        ResponseEntity<Patient> response = patientController.getPatientById(patientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(patientId, response.getBody().getId());
        assertEquals("Doe", response.getBody().getName().get(0).getFamily());
        assertEquals("John", response.getBody().getName().get(0).getGivenAsSingleString());
    }

    @Test
    public void createPatient_whenPostRequestWithPatient_returnsSavedPatient() {
        Patient newPatient = new Patient();
        newPatient.addName().setFamily("Doe").addGiven("Jane");

        ResponseEntity<Patient> response = patientController.createPatient(newPatient);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getId());
    }

    @Test
    public void updatePatient_whenPutRequestWithPatient_updatesAndReturnsPatient() {
        String patientId = "1";
        Patient updatedPatient = new Patient();
        updatedPatient.addName().setFamily("Doe").addGiven("John");

        ResponseEntity<Patient> response = patientController.updatePatient(patientId, updatedPatient);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(patientId, response.getBody().getId());
    }

    @Test
    public void deletePatient_whenDeleteRequestWithValidId_returnsNoContent() {
        String patientId = "1";

        ResponseEntity<Void> response = patientController.deletePatient(patientId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
package com.example.controller;

import org.hl7.fhir.r4.model.MedicationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MedicationRequestControllerTest {

    @InjectMocks
    private MedicationRequestController medicationRequestController;

    @Test
    public void getMedicationRequestById_whenCalledWithValidId_returnsMedicationRequest() {
        String requestId = "1";

        ResponseEntity<MedicationRequest> response = medicationRequestController.getMedicationRequestById(requestId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(requestId, response.getBody().getId());
        assertEquals(MedicationRequest.MedicationRequestStatus.ACTIVE, response.getBody().getStatus());
    }

    @Test
    public void createMedicationRequest_whenPostRequestWithMedicationRequest_returnsSavedMedicationRequest() {
        MedicationRequest newRequest = new MedicationRequest();
        newRequest.setStatus(MedicationRequest.MedicationRequestStatus.ACTIVE);

        ResponseEntity<MedicationRequest> response = medicationRequestController.createMedicationRequest(newRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getId()); // Assumes that the ID is set to "1" as in the example controller
    }

    @Test
    public void updateMedicationRequest_whenPutRequestWithMedicationRequest_updatesAndReturnsMedicationRequest() {
        String requestId = "1";
        MedicationRequest updatedRequest = new MedicationRequest();
        updatedRequest.setStatus(MedicationRequest.MedicationRequestStatus.ONHOLD);

        ResponseEntity<MedicationRequest> response = medicationRequestController.updateMedicationRequest(requestId,
                updatedRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(requestId, response.getBody().getId());
        assertEquals(MedicationRequest.MedicationRequestStatus.ONHOLD, response.getBody().getStatus());
    }

    @Test
    public void deleteMedicationRequest_whenDeleteRequestWithValidId_returnsNoContent() {
        String requestId = "1";

        ResponseEntity<Void> response = medicationRequestController.deleteMedicationRequest(requestId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
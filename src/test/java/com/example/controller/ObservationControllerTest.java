package com.example.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hl7.fhir.r4.model.Observation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class ObservationControllerTest {

    @InjectMocks
    private ObservationController observationController;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        // Set up any common configuration for your mocked request or response here
    }

    @Test
    public void getObservationById_whenCalledWithValidId_returnsObservation() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/fhir/Observation/1");

        Observation observation = new Observation();
        observation.setId("1");
        observation.setStatus(Observation.ObservationStatus.FINAL);

        ResponseEntity<Observation> result = observationController.getObservationById("1");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("1", result.getBody().getId());
    }

    @Test
    public void createObservation_whenPostRequestWithObservation_returnsSavedObservation() {
        Observation observation = new Observation();
        observation.setStatus(Observation.ObservationStatus.FINAL);

        ResponseEntity<Observation> result = observationController.createObservation(observation);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("1", result.getBody().getId());
    }

    @Test
    public void updateObservation_whenPutRequestWithObservation_updatesAndReturnsObservation() {
        Observation observation = new Observation();
        observation.setId("1");
        observation.setStatus(Observation.ObservationStatus.FINAL);

        ResponseEntity<Observation> result = observationController.updateObservation("1", observation);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("1", result.getBody().getId());
    }

    @Test
    public void deleteObservation_whenDeleteRequestWithValidId_returnsNoContent() {
        ResponseEntity<Void> result = observationController.deleteObservation("1");
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}
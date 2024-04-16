package com.example.controller;

import org.hl7.fhir.r4.model.Observation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ObservationController.class)
public class ObservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObservationController observationController;

    @Test
    public void testGetObservationById() throws Exception {
        mockMvc.perform(get("/fhir/Observation/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(observationController).getObservationById("1");
    }

    @Test
    public void testCreateObservation() throws Exception {
        String observationJson = "{\"id\":\"1\"}";
        mockMvc.perform(post("/fhir/Observation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(observationJson))
                .andExpect(status().isCreated());
        verify(observationController).createObservation(any(Observation.class));
    }

    @Test
    public void testUpdateObservation() throws Exception {
        String observationJson = "{\"id\":\"1\"}";
        mockMvc.perform(put("/fhir/Observation/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(observationJson))
                .andExpect(status().isOk());
        verify(observationController).updateObservation(eq("1"), any(Observation.class));
    }

    @Test
    public void testDeleteObservation() throws Exception {
        mockMvc.perform(delete("/fhir/Observation/1"))
                .andExpect(status().isNoContent());
        verify(observationController).deleteObservation("1");
    }
}
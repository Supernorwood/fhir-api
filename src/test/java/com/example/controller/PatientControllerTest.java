package com.example.controller;

import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientController patientController;

    @Test
    public void testGetPatientById() throws Exception {

        mockMvc.perform(get("/fhir/Patient/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name[0].family").value("Doe"))
                .andExpect(jsonPath("$.name[0].given[0]").value("John"));
        verify(patientController).getPatientById("1");
    }

    @Test
    public void testCreatePatient() throws Exception {
        Patient patient = new Patient();
        patient.setId("1");
        String patientJson = "{\"id\":\"1\"}";

        mockMvc.perform(post("/fhir/Patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
        verify(patientController).createPatient(any(Patient.class));
    }

    @Test
    public void testUpdatePatient() throws Exception {
        Patient patient = new Patient();
        patient.setId("1");
        String patientJson = "{\"id\":\"1\"}";

        mockMvc.perform(put("/fhir/Patient/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
        verify(patientController).updatePatient(eq("1"), any(Patient.class));
    }

    @Test
    public void testDeletePatient() throws Exception {

        mockMvc.perform(delete("/fhir/Patient/1"))
                .andExpect(status().isNoContent());
        verify(patientController).deletePatient("1");
    }
}
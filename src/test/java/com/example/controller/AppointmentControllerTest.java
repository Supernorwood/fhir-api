package com.example.controller;

import org.hl7.fhir.r4.model.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Test
    public void getAppointmentById_whenCalledWithValidId_returnsAppointment() {
        String appointmentId = "1";

        ResponseEntity<Appointment> response = appointmentController.getAppointmentById(appointmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(appointmentId, response.getBody().getId());
        assertEquals(Appointment.AppointmentStatus.BOOKED, response.getBody().getStatus());
    }

    @Test
    public void createAppointment_whenPostRequestWithAppointment_returnsSavedAppointment() {
        Appointment newAppointment = new Appointment();
        newAppointment.setStatus(Appointment.AppointmentStatus.PROPOSED);

        ResponseEntity<Appointment> response = appointmentController.createAppointment(newAppointment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getId()); // Assumes that the ID is set to "1" as in the example controller
    }

    @Test
    public void updateAppointment_whenPutRequestWithAppointment_updatesAndReturnsAppointment() {
        String appointmentId = "1";
        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setStatus(Appointment.AppointmentStatus.CANCELLED);

        ResponseEntity<Appointment> response = appointmentController.updateAppointment(appointmentId,
                updatedAppointment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(appointmentId, response.getBody().getId());
        assertEquals(Appointment.AppointmentStatus.CANCELLED, response.getBody().getStatus());
    }

    @Test
    public void deleteAppointment_whenDeleteRequestWithValidId_returnsNoContent() {
        String appointmentId = "1";

        ResponseEntity<Void> response = appointmentController.deleteAppointment(appointmentId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
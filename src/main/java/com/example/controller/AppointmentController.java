package com.example.controller;

import org.hl7.fhir.r4.model.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fhir/Appointment")
public class AppointmentController {

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") String id) {
        Appointment appointment = new Appointment();
        appointment.setId(id);
        appointment.setStatus(Appointment.AppointmentStatus.BOOKED);
        // Additional properties can be set here
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        // Assume the appointment is created and return the same object with an ID
        appointment.setId("1");
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") String id,
            @RequestBody Appointment appointment) {
        // For simplicity, assume the appointment is updated with the provided data
        appointment.setId(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") String id) {
        // Assume the appointment with this ID is deleted
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
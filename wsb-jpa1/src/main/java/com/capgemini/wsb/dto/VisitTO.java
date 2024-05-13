package com.capgemini.wsb.dto;

import java.time.LocalDateTime;

public class VisitTO {
    private Long id;
    private String description;
    private LocalDateTime time;
    private DoctorTO doctor;
    private PatientTO patient;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public DoctorTO getDoctor() {
        return doctor;
    }

    public PatientTO getPatient() {
        return patient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setDoctor(DoctorTO doctor) {
        this.doctor = doctor;
    }

    public void setPatient(PatientTO patient) {
        this.patient = patient;
    }
}

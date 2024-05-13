package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;

import java.util.List;

public interface PatientService {
    PatientTO findPatientById(Long id);
    PatientTO savePatient(PatientTO patientTO);
    void deletePatient(Long id);
    List<PatientTO> findPatientsWithMoreThanVisits(Long visitCount);
}
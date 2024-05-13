package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.mapper.PatientMapper;
import com.capgemini.wsb.persistence.dao.PatientRepository;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    @Transactional(readOnly = true)
    public PatientTO findPatientById(Long id) {
        return patientRepository.findById(id)
                .map(PatientMapper::toTO)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
    }

    @Override
    @Transactional
    public PatientTO savePatient(PatientTO patientTO) {
        PatientEntity patientEntity = PatientMapper.fromTO(patientTO);
        PatientEntity savedPatient = patientRepository.save(patientEntity);
        return PatientMapper.toTO(savedPatient);
    }

    @Override
    @Transactional
    public void deletePatient(Long id) {
        PatientEntity patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
        patientRepository.delete(patient);
    }

    @Override
    public List<PatientTO> findPatientsWithMoreThanVisits(Long visitCount) {
        return patientRepository.findPatientsWithMoreThanVisits(visitCount).stream()
                .map(PatientMapper::toTO)
                .collect(Collectors.toList());
    }

}

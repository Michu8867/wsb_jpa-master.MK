package com.capgemini.wsb.persistence.service;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.persistence.dao.PatientRepository;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Long patientId;

    @BeforeEach
    void setup() {
        patientRepository.deleteAll();

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Patrick");
        patient.setLastName("Vettel");
        patient.setDateOfBirth(LocalDate.of(1980, 1, 1));
        patient.setTelephoneNumber("987654321");
        patient.setEmail("pvete@gmail.com");
        patient.setPatientNumber("PAT" + System.currentTimeMillis());
        patient = patientRepository.save(patient);
        patientId = patient.getId();
    }

    @Test
    @Transactional
    public void testDeletePatient() {
        assertNotNull(patientRepository.findById(patientId));
        patientService.deletePatient(patientId);
        assertTrue(patientRepository.findById(patientId).isEmpty());
    }

    @Test
    @Transactional
    public void testFindPatientById() {
        PatientTO foundPatient = patientService.findPatientById(patientId);
        assertNotNull(foundPatient);
        assertEquals(patientId, foundPatient.getId());
        assertEquals("Patrick", foundPatient.getFirstName());
        assertEquals("Vettel", foundPatient.getLastName());
    }

    @Test
    @Transactional
    public void testFindPatientsWithMoreThanVisits() {
        for (int i = 0; i < 5; i++) {
            PatientEntity patient = new PatientEntity();
            patient.setFirstName("Michal" + i);
            patient.setLastName("Kalinowski" + i);
            patient.setDateOfBirth(LocalDate.now());
            patient.setTelephoneNumber("674915624" + i);
            patient.setEmail("m.kalinowski" + i + "@gmail.com");
            patient.setPatientNumber("PAT" + System.currentTimeMillis() + i);
            patient = patientRepository.save(patient);

            for (int j = 0; j < i; j++) {
                VisitEntity visit = new VisitEntity();
                visit.setPatient(patient);
                visit.setTime(LocalDate.now().atStartOfDay()); // Tutaj ustawiamy czas wizyty
                entityManager.persist(visit);
            }
        }
        entityManager.flush();

        List<PatientTO> patientsWithMoreVisits = patientService.findPatientsWithMoreThanVisits(3L);
        assertEquals(1, patientsWithMoreVisits.size());
        assertTrue(patientsWithMoreVisits.get(0).getFirstName().startsWith("Michal"));
    }

}

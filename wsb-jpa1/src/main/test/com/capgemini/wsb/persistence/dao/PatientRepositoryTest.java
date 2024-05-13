package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PatientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PatientRepository patientRepository;

    //Znajdowanie po nazwisku
    @Test
    void findByLastNameShouldReturnPatients() {

        PatientEntity patient = new PatientEntity();
        patient.setLastName("Andrzejczyk");
        patient.setFirstName("Jan");
        patient.setTelephoneNumber("965709856");
        patient.setEmail("jan.andrzejczyk@gmail.com");
        patient.setPatientNumber("PN123");
        entityManager.persist(patient);
        entityManager.flush();


        List<PatientEntity> foundPatients = patientRepository.findByLastName("Andrzejczyk");


        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getLastName()).isEqualTo("Andrzejczyk");
    }
    //Znajdowanie pacjentów, którzy mieli więcej wizyt niż X
    @Test
    void findPatientsWithMoreThanVisitsShouldReturnCorrectPatients() {
        PatientEntity patient = new PatientEntity();
        patient.setLastName("Koczar");
        patient.setFirstName("Adam");
        patient.setTelephoneNumber("987654321");
        patient.setEmail("adam.koczar@gmail.com");
        patient.setPatientNumber("PN124");
        entityManager.persist(patient);

        for (int i = 0; i < 5; i++) {
            VisitEntity visit = new VisitEntity();
            visit.setPatient(patient);
            entityManager.persist(visit);
        }
        entityManager.flush();

        List<PatientEntity> foundPatients = patientRepository.findPatientsWithMoreThanVisits(3L);

        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getLastName()).isEqualTo("Koczar");
    }

}

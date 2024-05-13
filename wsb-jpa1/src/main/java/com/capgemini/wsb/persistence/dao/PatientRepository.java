package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    @Query("SELECT p FROM PatientEntity p WHERE p.lastName = :lastName")
    List<PatientEntity> findByLastName(String lastName);

    @Query("SELECT p FROM PatientEntity p WHERE (SELECT COUNT (v) FROM VisitEntity v WHERE v.patient = p) > :visitCount")
    List<PatientEntity> findPatientsWithMoreThanVisits(@Param("visitCount") Long visitCount);

    @Query("SELECT p FROM PatientEntity p WHERE p.dateOfBirth < :date")
    List<PatientEntity> findPatientsBornBefore(@Param("date")LocalDate date);
}

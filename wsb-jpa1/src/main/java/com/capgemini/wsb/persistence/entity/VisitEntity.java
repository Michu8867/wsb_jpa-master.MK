package com.capgemini.wsb.persistence.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	// Dwustronna relacja z DoctorEntity (strona dziecka)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCTOR_ID")
	private DoctorEntity doctor;

	// Dwustronna relacja z PatientEntity (strona dziecka)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID")
	private PatientEntity patient;

	// Dwustronna relacja z MedicalTreatmentEntity (strona dziecka)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEDICAL_TREATMENT_ID")
	private MedicalTreatmentEntity medicalTreatment;

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public void setMedicalTreatment(MedicalTreatmentEntity medicalTreatmentEntity) {
	}
}

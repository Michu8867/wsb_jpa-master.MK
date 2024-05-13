package com.capgemini.wsb.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.capgemini.wsb.persistence.enums.TreatmentType;

@Entity
@Table(name = "MEDICAL_TREATMENT")
public class MedicalTreatmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String treatmentDescription;

	@Enumerated(EnumType.STRING)
	private TreatmentType type;

	// Dwustronna relacja z VisitEntity (rodzic)
	@OneToMany(mappedBy = "medicalTreatment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VisitEntity> visits = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTreatmentDescription() {
		return treatmentDescription;
	}

	public void setTreatmentDescription(String treatmentDescription) {
		this.treatmentDescription = treatmentDescription;
	}

	public TreatmentType getType() {
		return type;
	}

	public void setType(TreatmentType type) {
		this.type = type;
	}

	public Set<VisitEntity> getVisits() {
		return visits;
	}

	public void setVisits(Set<VisitEntity> visits) {
		this.visits = visits;
	}

	public void addVisit(VisitEntity visit) {
		visits.add(visit);
		visit.setMedicalTreatment(this);
	}

	public void removeVisit(VisitEntity visit) {
		visits.remove(visit);
		visit.setMedicalTreatment(null);
	}
}

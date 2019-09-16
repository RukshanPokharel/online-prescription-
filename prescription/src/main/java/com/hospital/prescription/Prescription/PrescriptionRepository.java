package com.hospital.prescription.Prescription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{

	public Prescription findByPrescription(String prescription);
	public Prescription findById(int id);
}

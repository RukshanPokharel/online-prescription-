package com.hospital.prescription.patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

	public Patient findByUserName(String userName);
	public Patient findByFullName(String fullName);
	public Patient findById(int id);
}

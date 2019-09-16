package com.hospital.prescription.doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository  extends JpaRepository<Doctor, Integer>{
	public Doctor findByUserName(String userName);
	public Doctor findByFullName(String fullName);
	public Doctor findById(int id);
}

package com.hospital.prescription.Treatment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hospital.prescription.Appointments.Appointment;
import com.hospital.prescription.patient.Patient;


@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer>{

	public Treatment findById(int id);
	public List<Treatment> findByPatientOrderByDate(Patient patient);
	
	@Query(value="select treatment_area, DATE_FORMAT(date,\"%b-%Y\"), count(*) FROM treatment group by treatment_area, DATE_FORMAT(date,\"%b-%Y\")", nativeQuery=true)
	public List<Object[]> readTreatment();
}

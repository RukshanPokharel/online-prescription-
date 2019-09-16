package com.hospital.prescription.Appointments;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.prescription.doctor.Doctor;
import com.hospital.prescription.patient.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	public Appointment findById(int id);
	
	public List<Appointment> findByPatientOrderByDate(Patient patient);
	public List<Appointment> findByDoctorOrderByDate(Doctor doctor);
	public List<Appointment> findByDoctorAndDateEquals(Doctor doctor, Date date);
	public List<Appointment> findByDoctorAndDateAfterOrDateEquals(Doctor doctor, Date date1, Date date2);
	public List<Appointment> findByDoctorAndDateAndTimeEquals(Doctor doctor, Date date, String time);
}

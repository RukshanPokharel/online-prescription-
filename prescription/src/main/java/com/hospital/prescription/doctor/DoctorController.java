package com.hospital.prescription.doctor;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.prescription.Appointments.Appointment;
import com.hospital.prescription.Appointments.AppointmentRepository;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	DoctorRepository dRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	private Date currentDate;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Doctor> readAllDoctors() {
		List<Doctor> doctors = dRepository.findAll();
		return doctors;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Doctor readDoctor(@PathVariable int id) {
		Doctor doctor = dRepository.findById(id);
		return doctor;
	}

	@RequestMapping(path = "/{id}/appointments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> readAllAppointment(@PathVariable int id) {
		Doctor doctor = dRepository.findById(id);
		if (doctor == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Calendar currenttime = Calendar.getInstance();
		currentDate = new Date((currenttime.getTime()).getTime());

		List<Appointment> currentAppointments = appointmentRepository.findByDoctorAndDateAfterOrDateEquals(doctor, currentDate, currentDate);

		return ResponseEntity.ok().body(currentAppointments);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Doctor createDoctor(@RequestBody Doctor doctors) {
		Doctor savedDoctors = dRepository.save(doctors);
		return savedDoctors;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Doctor updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) {
		Doctor existingDoctor = dRepository.findById(id);
		if (existingDoctor == null) {
			return null;
		}

		if (doctor.getFullName() != null) {
			existingDoctor.setFullName(doctor.getFullName());
		}

		if (doctor.getDob() != null) {
			existingDoctor.setDob(doctor.getDob());
		}

		if (doctor.getGender() != null) {
			existingDoctor.setGender(doctor.getGender());
		}

		if (doctor.getPhoneNumber() != null) {
			existingDoctor.setPhoneNumber(doctor.getPhoneNumber());
		}

		if (doctor.getEmail() != null) {
			existingDoctor.setEmail(doctor.getEmail());
		}

		if (doctor.getSpecialist() != null) {
			existingDoctor.setSpecialist(doctor.getSpecialist());
		}

		if (doctor.getUserName() != null) {
			existingDoctor.setUserName(doctor.getUserName());
		}

		if (doctor.getPassword() != null) {
			existingDoctor.setPassword(doctor.getPassword());
		}

		if (doctor.getAddress() != null) {
			existingDoctor.setAddress(doctor.getAddress());
		}

		dRepository.save(existingDoctor);
		return existingDoctor;

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Doctor deleteDoctor(@PathVariable int id) {
		Doctor existingDoctor = dRepository.findById(id);
		if (existingDoctor == null) {
			return null;
		}
		dRepository.delete(id);
		return existingDoctor;
	}

}

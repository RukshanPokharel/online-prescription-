package com.hospital.prescription.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.prescription.doctor.Doctor;
import com.hospital.prescription.doctor.DoctorRepository;
import com.hospital.prescription.patient.Patient;
import com.hospital.prescription.patient.PatientRepository;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	PatientRepository repository;
	
	@Autowired
	DoctorRepository doctorRepository;

	@RequestMapping(path="/patient", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> login(@RequestBody Patient patient) {
		Patient existingPatient = repository.findByUserName(patient.getUserName());
		if (existingPatient == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		if (!existingPatient.getPassword().equals(patient.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else
			return ResponseEntity.ok().body(existingPatient);
	}
	
	@RequestMapping(path="/doctor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Doctor> login(@RequestBody Doctor doctor) {
		Doctor existingDoctor = doctorRepository.findByUserName(doctor.getUserName());
		if (existingDoctor == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		if (!existingDoctor.getPassword().equals(doctor.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else
			return ResponseEntity.ok().body(existingDoctor);
	}
}

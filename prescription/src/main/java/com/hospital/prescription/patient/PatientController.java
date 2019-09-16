package com.hospital.prescription.patient;

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
import com.hospital.prescription.Treatment.Treatment;
import com.hospital.prescription.Treatment.TreatmentRepository;

@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private TreatmentRepository treatmentRepository;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Patient> readAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		return patients;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient readPatient(@PathVariable int id) {
		Patient patient = patientRepository.findById(id);
		return patient;
	}

	@RequestMapping(path = "/{id}/appointments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> readAllAppointment(@PathVariable int id) {
		Patient patient = patientRepository.findById(id);

		if (patient == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		List<Appointment> appointments = appointmentRepository.findByPatientOrderByDate(patient);

		return ResponseEntity.ok().body(appointments);
	}

	@RequestMapping(path = "/{id}/treatments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Treatment>> readAllTreatment(@PathVariable int id) {
		Patient patient = patientRepository.findById(id);

		if (patient == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		List<Treatment> treatments = treatmentRepository.findByPatientOrderByDate(patient);

		return ResponseEntity.ok().body(treatments);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient createPatient(@RequestBody Patient patients) {
		Patient savedPatients = patientRepository.save(patients);
		return savedPatients;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient updatePatient(@PathVariable int id, @RequestBody Patient patient) {
		Patient existingPatient = patientRepository.findById(id);
		if (existingPatient == null) {
			return null;
		}

		if (patient.getFullName() != null) {
			existingPatient.setFullName(patient.getFullName());
		}

		if (patient.getDob() != null) {
			existingPatient.setDob(patient.getDob());
		}

		if (patient.getGender() != null) {
			existingPatient.setGender(patient.getGender());
		}

		if (patient.getPhoneNumber() != null) {
			existingPatient.setPhoneNumber(patient.getPhoneNumber());
		}

		if (patient.getEmail() != null) {
			existingPatient.setEmail(patient.getEmail());
		}

		if (patient.getAge() != 0) {
			existingPatient.setAge(patient.getAge());
		}

		if (patient.getUserName() != null) {
			existingPatient.setUserName(patient.getUserName());
		}

		if (patient.getPassword() != null) {
			existingPatient.setPassword(patient.getPassword());
		}

		if (patient.getAddress() != null) {
			existingPatient.setAddress(patient.getAddress());
		}

		patientRepository.save(existingPatient);
		return existingPatient;

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient deletePatient(@PathVariable int id) {
		Patient existingPatient = patientRepository.findById(id);
		if (existingPatient == null) {
			return null;
		}
		patientRepository.delete(id);
		return existingPatient;
	}

}

package com.hospital.prescription.Appointments;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.prescription.doctor.Doctor;
import com.hospital.prescription.doctor.DoctorRepository;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	DoctorRepository dRepository; 


	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Appointment> getAppointment() {
		List<Appointment> appointments = appointmentRepository.findAll();
		return appointments;
	}
	
	
	@RequestMapping(path = "/check_appointment/doctor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> readAllAppointment(@PathVariable int id, @RequestParam(value="date") Date date, @RequestParam(value="time") String time) {
		Doctor doctor = dRepository.findById(id);
		List<Appointment> times1 = appointmentRepository.findByDoctorAndDateAndTimeEquals(doctor,date,time);
		
		return ResponseEntity.ok().body(times1);
		
	}

	// @RequestMapping(path="/{appointments}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	// public Appointment getAppointment(@PathVariable("appointments") String
	// appointment){
	// Appointment appointment1 =
	// appointmentRepository.findByPatientName(appointment);
	// return appointment1;
	//
	// }

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Appointment createAppointment(@RequestBody Appointment appointments) {
		Appointment savedAppointments = appointmentRepository.save(appointments);
		return savedAppointments;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Appointment updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
		Appointment existingAppointment = appointmentRepository.findById(id);
		if (existingAppointment == null) {
			return null;
		}

		// if(prescription.getPatientName()!=null){
		// existingPrescription.setPatientName(prescription.getPatientName());
		// }
		//
		// if(prescription.getDoctorName()!=null){
		// existingPrescription.setDoctorName(prescription.getDoctorName());
		// }
		//
		// if(prescription.getMedicines()!=null){
		// existingPrescription.setMedicines(prescription.getMedicines());
		// }
		//
		// if(prescription.getPrescription()!=null){
		// existingPrescription.setPrescription(prescription.getPrescription());
		// }

		appointmentRepository.save(existingAppointment);
		return existingAppointment;

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Appointment deleteAppointment(@PathVariable int id) {
		Appointment existingAppointment = appointmentRepository.findById(id);
		if (existingAppointment == null) {
			return null;
		}
		appointmentRepository.delete(id);
		return existingAppointment;
	}
}

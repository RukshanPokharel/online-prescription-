/**
 * 
 */
package com.hospital.prescription.analytics;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.prescription.Treatment.Treatment;
import com.hospital.prescription.Treatment.TreatmentRepository;
import com.hospital.prescription.common.Address;
import com.hospital.prescription.doctor.Doctor;
import com.hospital.prescription.doctor.DoctorRepository;
import com.hospital.prescription.patient.Patient;
import com.hospital.prescription.patient.PatientRepository;

@RestController
@RequestMapping("/generateTestData")
public class TestDataGenerator {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private TreatmentRepository treatmentRepository;

	private String[] diseases = { "HIV/AIDS", "Tuberculosis", "Malaria", "Neglected tropical diseases", "Cholera",
			"Influenza", "Meningitis", "STD", "Antimicrobial resistance (AMR)", "country self-assessment",
			"Noncommunicable diseases", "Mental health", "COPD", "Cerebral vascular accident", "Birth asphyxia",
			"HIV/AIDS", "Septicemia", "NIDDM", "Pneumonia", "Myocardial infarction", "Acute Death Syndrome",
			"Ischaemic heart disease", "Pneumonia", "Appendicitis", "COPD", "Respitory tract infections",
			"Injury / Dislocation", "Hepatitis", "Pyrexia", "Sepsis" };
	private String[] gender = { "Male", "Female" };
	private String passowrd = "1234";

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> generateTestData() {
		generateData();
		return ResponseEntity.ok().body("100 patiens, 100 doctors and 10000 treatments test data generated");
	}

	public void generateData() {
		List<Patient> patients = new ArrayList<>();
		List<Doctor> doctors = new ArrayList<>();

		DataFactory df = new DataFactory();

		// generate 100 patients and doctors each
		for (int i = 0; i < 100; i++) {
			Patient patient = patient(df);
			Doctor doctor = doctor(df);

			patients.add(patient);
			doctors.add(doctor);
		}

		// generate treatment data
		for (int i = 0; i < 1000; i++) {
			Patient patient = patients.get(df.getNumberBetween(0, 100));
			Doctor doctor = doctors.get(df.getNumberBetween(0, 100));
			saveTreatment(patient, doctor, df);
		}
	}

	public Patient patient(DataFactory df) {
		Patient patient = new Patient();
		patient.setAddress(address(df));
		patient.setAge(df.getNumberUpTo(100));
		patient.setEmail(df.getEmailAddress());
		patient.setDob(new Date(df.getBirthDate().getTime()));
		patient.setGender(df.getItem(gender));
		patient.setFullName(df.getName());
		patient.setPhoneNumber(df.getNumberText(9));
		patient.setUserName(df.getFirstName());
		patient.setPassword(passowrd);

		patientRepository.save(patient);
		return patient;
	}

	public Doctor doctor(DataFactory df) {
		Doctor doctor = new Doctor();
		doctor.setAddress(address(df));
		doctor.setDob(new Date(df.getBirthDate().getTime()));
		doctor.setEmail(df.getEmailAddress());
		doctor.setFullName(df.getName());
		doctor.setGender(df.getItem(gender));
		doctor.setPhoneNumber(df.getNumberText(9));
		doctor.setSpecialist(df.getRandomText(6, 12));
		doctor.setUserName(df.getFirstName());
		doctor.setPassword(passowrd);

		doctorRepository.save(doctor);
		return doctor;
	}

	public Treatment saveTreatment(Patient patient, Doctor doctor, DataFactory df) {
		Treatment treatment = new Treatment();
		treatment.setDate(new Date(df.getDateBetween(new GregorianCalendar(2016, 1, 1).getTime(),
				new GregorianCalendar(2018, 3, 1).getTime()).getTime()));
		treatment.setDoctor(doctor);
		treatment.setPatient(patient);
		treatment.setTreatmentArea(df.getItem(diseases));
		treatment.setNote(df.getRandomWord(1, 17));
		treatment.setPrescription(df.getRandomWord(1, 17));

		treatmentRepository.save(treatment);
		return treatment;
	}

	public Address address(DataFactory df) {
		Address address = new Address();
		address.setAddressLine1(df.getAddress());
		address.setCity(df.getCity());
		address.setState(df.getCity());
		return address;
	}
}

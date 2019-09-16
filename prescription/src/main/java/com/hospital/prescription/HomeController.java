package com.hospital.prescription;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(){
		return "login";
	}
	
	@RequestMapping("/signupPatient")
	public String signupPatient(){
		return "signup_patient";
	}
	
	@RequestMapping("/signupDoctor")
	public String signupDoctor(){
		return "signup_doctor";
	}
	
	@RequestMapping("/patient/{id}")
	 public String patientHome(){
	  return "patient_home";
	 }
	
	@RequestMapping("/doctor/{id}")
	 public String doctorHome(){
	  return "doctor_home";
	 }
	
	@RequestMapping("/treatment/{dId}/{pId}")
	 public String prescriptionHome(){
	  return "prescription_home";
	 }
	
	@RequestMapping("/patient/update_patient/{id}")
	 public String updatePatient(){
	  return "update_patient";
	 }
	
	@RequestMapping("/doctor/update_doctor/{id}")
	 public String updateDoctor(){
	  return "update_doctor";
	 }
	
	@RequestMapping("/patient/patient_history/{id}")
	 public String patientHistory(){
	  return "patient_history";
	 }

	@RequestMapping("/analytics")
	public String analytics() {
		return "analytics";
	}
}

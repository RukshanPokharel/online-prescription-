package com.hospital.prescription.Treatment;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treatment")
public class TreatmentController {
	@Autowired
	TreatmentRepository treatmentRepository; 
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Treatment> getTreatment(){
	List<Treatment> Treatments = treatmentRepository.findAll();
	return Treatments;
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Treatment getTreatment(@PathVariable int id){
		Treatment Treatment1 = treatmentRepository.findById(id);
	return Treatment1;
	}
	
	
	@RequestMapping(method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public Treatment createTreatment(@RequestBody Treatment treatment){
		Calendar currenttime = Calendar.getInstance();
	    Date currentDate = new Date((currenttime.getTime()).getTime());
		treatment.setDate(currentDate);
		Treatment savedTreatments = treatmentRepository.save(treatment);
		return savedTreatments;
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE)
	public Treatment updateTreatment(@PathVariable int id, @RequestBody Treatment prescription){
		Treatment existingTreatment = treatmentRepository.findById(id);
		if(existingTreatment==null){
			return null;
		}
		
//		if(Treatment.getPatientName()!=null){
//			existingTreatment.setPatientName(Treatment.getPatientName());
//		}
//		
//		if(Treatment.getDoctorName()!=null){
//			existingTreatment.setDoctorName(Treatment.getDoctorName());
//		}
//		
//		if(Treatment.getMedicines()!=null){
//			existingTreatment.setMedicines(Treatment.getMedicines());
//		}
//		
//		if(Treatment.getTreatment()!=null){
//			existingTreatment.setTreatment(Treatment.getTreatment());
//		}
	
		 treatmentRepository.save(existingTreatment);
		 return existingTreatment;
		
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Treatment deleteTreatment(@PathVariable int id){
		Treatment existingTreatment = treatmentRepository.findById(id);
		if(existingTreatment==null){
			return null;
		}
		treatmentRepository.delete(id);
		return existingTreatment;
	}
}

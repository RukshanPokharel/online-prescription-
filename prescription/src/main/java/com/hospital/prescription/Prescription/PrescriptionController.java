package com.hospital.prescription.Prescription;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
	@Autowired
	PrescriptionRepository pRepository; 
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Prescription> getPrescription(){
	List<Prescription> prescriptions = pRepository.findAll();
	return prescriptions;
	}
	
	@RequestMapping(path="/{prescriptions}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Prescription getPrescription(@PathVariable("prescriptions") String prescription){
		Prescription prescription1 = pRepository.findByPrescription(prescription);
	return prescription1;
	}
	
	@RequestMapping(method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public Prescription createUser(@RequestBody Prescription prescriptions){
		Prescription savedPrescriptions = pRepository.save(prescriptions);
		return savedPrescriptions;
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE)
	public Prescription updatePrescription(@PathVariable int id, @RequestBody Prescription prescription){
		Prescription existingPrescription = pRepository.findById(id);
		if(existingPrescription==null){
			return null;
		}
		
		if(prescription.getPatientName()!=null){
			existingPrescription.setPatientName(prescription.getPatientName());
		}
		
		if(prescription.getDoctorName()!=null){
			existingPrescription.setDoctorName(prescription.getDoctorName());
		}
		
		if(prescription.getMedicines()!=null){
			existingPrescription.setMedicines(prescription.getMedicines());
		}
		
		if(prescription.getPrescription()!=null){
			existingPrescription.setPrescription(prescription.getPrescription());
		}
		
		 pRepository.save(existingPrescription);
		 return existingPrescription;
		
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Prescription deletePrescription(@PathVariable int id){
		Prescription existingPrescription = pRepository.findById(id);
		if(existingPrescription==null){
			return null;
		}
		pRepository.delete(id);
		return existingPrescription;
	}
}

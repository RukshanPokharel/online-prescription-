package com.hospital.prescription.Treatment;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.hospital.prescription.doctor.Doctor;
import com.hospital.prescription.patient.Patient;

@Entity
public class Treatment implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="patient_id", nullable = false)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="doctor_id", nullable = false)
	private Doctor doctor;
	
	private Date date;
	private String treatmentArea;
	private String note;
	private String prescription;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient= patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getTreatmentArea() {
		return treatmentArea;
	}
	public void setTreatmentArea(String treatmentArea) {
		this.treatmentArea = treatmentArea;
	}
	
	
	
		

}

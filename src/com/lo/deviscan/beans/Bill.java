package com.lo.deviscan.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "BILL")
public class Bill {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="BILL_SEQ")
	@SequenceGenerator(name="BILL_SEQ", sequenceName="BILL_SEQ")
	private int id;
	private String type;
	@ManyToOne( cascade = {CascadeType.PERSIST}, targetEntity=Patient.class )
    @JoinColumn(name="PATIENT")
	private Patient patient;
	@ManyToOne( cascade = {CascadeType.PERSIST}, targetEntity=Doctor.class )
    @JoinColumn(name="REFDOCTOR")
	private Doctor refDoctor;
	@ManyToOne( cascade = {CascadeType.PERSIST}, targetEntity=Doctor.class )
    @JoinColumn(name="STAFFDOCTOR")
	private Doctor staffDoctor;
	private Double amount;
	@ManyToOne( cascade = {CascadeType.PERSIST}, targetEntity=User.class )
    @JoinColumn(name="CREATEDBY")
	private User createdBy;
	@Column(name="CREATEDDATE")
	private Date createdDate = Calendar.getInstance().getTime();
	@Transient
	private List<BillTest> billTests = new ArrayList<BillTest>();
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<BillTest> getBillTests() {
		return billTests;
	}
	public void setBillTests(List<BillTest> billTests) {
		this.billTests = billTests;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getRefDoctor() {
		return refDoctor;
	}
	public void setRefDoctor(Doctor refDoctor) {
		this.refDoctor = refDoctor;
	}
	public Doctor getStaffDoctor() {
		return staffDoctor;
	}
	public void setStaffDoctor(Doctor staffDoctor) {
		this.staffDoctor = staffDoctor;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}

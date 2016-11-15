package com.lo.deviscan.beans;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "BILL_TEST")
public class BillTest {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="BILL_TEST_SEQ")
	@SequenceGenerator(name="BILL_TEST_SEQ", sequenceName="BILL_TEST_SEQ")
	private int id;
	@ManyToOne( cascade = {CascadeType.PERSIST}, targetEntity=Bill.class )
    @JoinColumn(name="BILL")
	private Bill bill;
	@Column(name="CREATEDDATE")
	private Date createdDate = Calendar.getInstance().getTime();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}

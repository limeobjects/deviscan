package com.lo.deviscan.beans;

import java.util.ArrayList;
import java.util.List;

public class CollectionReport {

	private User user;
	private List<Bill> bills = new ArrayList<Bill>();
	private Double amount = new Double(0);
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}

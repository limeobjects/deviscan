package com.lo.deviscan.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CENTER")
public class Center {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="CENTER_SEQ")
	@SequenceGenerator(name="CENTER_SEQ", sequenceName="CENTER_SEQ")
	private int id;
	private String name;
	private String details;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
}

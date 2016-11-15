package com.lo.deviscan.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TEST")
public class Test {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="TEST_SEQ")
	@SequenceGenerator(name="TEST_SEQ", sequenceName="TEST_SEQ")
	private int id;
	private String name;
	private String type;
	private Double rate;
	private Double jssk;
	private Double rsby;
	private Double rbsk;
	private Double tribal;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getJssk() {
		return jssk;
	}
	public void setJssk(Double jssk) {
		this.jssk = jssk;
	}
	public Double getRsby() {
		return rsby;
	}
	public void setRsby(Double rsby) {
		this.rsby = rsby;
	}
	public Double getRbsk() {
		return rbsk;
	}
	public void setRbsk(Double rbsk) {
		this.rbsk = rbsk;
	}
	public Double getTribal() {
		return tribal;
	}
	public void setTribal(Double tribal) {
		this.tribal = tribal;
	}
	
}

package com.lo.deviscan.beans;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "USR")
public class User {
	@Id
	@Column(name="username")
	private String username;
	private String fullname;
	private String password;
	private String role;
	@ManyToOne( cascade = {CascadeType.PERSIST}, targetEntity=Center.class )
    @JoinColumn(name="CENTER")
	private Center center;
	@Column(name="createddate")
	private Date createdDate = Calendar.getInstance().getTime();
	public Center getCenter() {
		return center;
	}
	public void setCenter(Center center) {
		this.center = center;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}

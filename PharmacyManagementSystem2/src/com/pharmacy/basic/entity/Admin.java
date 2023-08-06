package com.pharmacy.basic.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Admin 
{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer adminId;
@Column(length=50)
private String email;
@Column(length=50)
private String username;

@OneToMany(mappedBy="admin")
private List<Bill> bills;

public String getEmail() {
	return email;
}

public List<Bill> getBills() {
	return bills;
}

public void setBills(List<Bill> bills) {
	this.bills = bills;
}

public Integer getAdminId() {
	return adminId;
}

public void setAdminId(Integer adminId) {
	this.adminId = adminId;
}

public void setEmail(String email) {
	this.email = email;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}
	
}



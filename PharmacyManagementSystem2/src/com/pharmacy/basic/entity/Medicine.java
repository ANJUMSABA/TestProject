package com.pharmacy.basic.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Medicine {
@Id
private Integer medicineId;
private String medicineName;
private String companyName;
private  Integer Quantity;
private LocalDate manufacturingDate;
private LocalDate expiryDate;
private Double pricePerUnit;



public Integer getMedicineId() {
	return medicineId;
}
public void setMedicineId(Integer medicineId) {
	this.medicineId = medicineId;
}
public String getMedicineName() {
	return medicineName;
}
public void setMedicineName(String medicineName) {
	this.medicineName = medicineName;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public Integer getQuantity() {
	return Quantity;
}
public void setQuantity(Integer quantity) {
	Quantity = quantity;
}
public LocalDate getManufacturingDate() {
	return manufacturingDate;
}
public void setManufacturingDate(LocalDate manufacturingDate) {
	this.manufacturingDate = manufacturingDate;
}
public LocalDate getExpiryDate() {
	return expiryDate;
}
public void setExpiryDate(LocalDate expiryDate) {
	this.expiryDate = expiryDate;
}
public Double getPricePerUnit() {
	return pricePerUnit;
}
public void setPricePerUnit(Double pricePerUnit) {
	this.pricePerUnit = pricePerUnit;
}



	}





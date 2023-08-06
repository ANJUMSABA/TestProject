package com.pharmacy.basic.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
	public class Bill {
	    @Id  
	    @GeneratedValue(strategy = GenerationType.AUTO)
		private  Integer billId ;
		private Date date;
		private Double totalPrice;
		
		
		@ManyToOne
		@JoinColumn(name="adminid")
		private Admin admin;
		
		public Integer getBillId() {
			return billId;
		}
		public void setBillId(Integer billId) {
			this.billId = billId;
		}
		public Admin getAdmin() {
			return admin;
		}
		public void setAdmin(Admin admin) {
			this.admin = admin;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public Double getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(Double totalPrice) {
			this.totalPrice = totalPrice;
		}
		
		
	}


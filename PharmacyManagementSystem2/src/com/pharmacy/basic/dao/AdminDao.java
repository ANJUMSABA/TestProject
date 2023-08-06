package com.pharmacy.basic.dao;

import java.util.List;

import com.pharmacy.basic.entity.Admin;

public interface AdminDao {
	
	public boolean login(String userName,String email);
	
	public boolean register(Admin admin);
	public List<Admin> findAdminByEmail(String email);
	public boolean verifyCredentials(String userName,String email);
	public Admin findAdminById(Integer adminId);

}

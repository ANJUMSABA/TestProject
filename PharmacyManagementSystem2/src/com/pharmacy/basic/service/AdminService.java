package com.pharmacy.basic.service;

import java.util.List;

import com.pharmacy.basic.entity.Admin;

public interface AdminService {
	
public boolean login(String userName,String email);	
public boolean register(Admin admin);
public List<Admin> findAdminByEmail(String email);
public boolean verifyCredentials(String userName,String email);
public Admin findAdminById(Integer adminId);

	
}

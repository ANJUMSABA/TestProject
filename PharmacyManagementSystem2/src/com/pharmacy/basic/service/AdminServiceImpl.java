package com.pharmacy.basic.service;

import java.util.List;

import com.pharmacy.basic.dao.AdminDao;
import com.pharmacy.basic.dao.AdminDaoImpl;
import com.pharmacy.basic.entity.Admin;

public class AdminServiceImpl implements AdminService {
	
	AdminDao adminDao=new AdminDaoImpl();

	@Override
	public boolean login(String userName, String email) {
		
		return adminDao.login(userName, email);
	}

	@Override
	public boolean register(Admin admin) {
		
		return adminDao.register(admin);
	}

	@Override
	public List<Admin> findAdminByEmail(String email) {
		
		return adminDao.findAdminByEmail(email);
	}

	@Override
	public boolean verifyCredentials(String userName, String email) {
		
		return adminDao.verifyCredentials(userName, email);
	}

	@Override
	public Admin findAdminById(Integer adminId) {
		
		return adminDao.findAdminById(adminId);
	}

}

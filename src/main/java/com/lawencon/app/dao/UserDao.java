package com.lawencon.app.dao;


import com.lawencon.app.model.User;

public interface UserDao {
	public void insert(User user) throws Exception;
	public boolean findByUsernamePassword(String username, String password) throws Exception;
}

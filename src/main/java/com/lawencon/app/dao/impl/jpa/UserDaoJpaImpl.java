package com.lawencon.app.dao.impl.jpa;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.UserDao;
import com.lawencon.app.model.User;
import com.lawencon.app.repo.UserRepo;

@Transactional
@Repository("user_repo_jpa")
public class UserDaoJpaImpl implements UserDao{

	@Autowired
	UserRepo userRepo;
	
	@Override
	public void insert(User user) throws Exception {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}

	@Override
	public boolean findByUsernamePassword(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		List<User> temp = userRepo.findByUsernameAndPassword(username, password);
		if (temp.isEmpty()) {
			return false;
		}else {
			return true;
		}
		
	}

}

package com.lawencon.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.app.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	List<User> findByUsernameAndPassword(String username, String password);
}

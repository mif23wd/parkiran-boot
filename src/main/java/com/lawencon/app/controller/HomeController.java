/*
 * author M.Ihsan Fadhil
 */
package com.lawencon.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.app.model.ParkirData;
import com.lawencon.app.model.User;
import com.lawencon.app.service.ParkirDataService;

@RestController
public class HomeController extends BaseController{

	@Autowired
	ParkirDataService parkirservice;
	

	@PostMapping("/hibernate/checkIn")
	public ResponseEntity<?> checkIn(@RequestHeader("Authorization") String auth, @RequestBody String content) {
		try {
			String[] userpass = authUser(auth);
			ParkirData data = new ObjectMapper().readValue(content, ParkirData.class);
			return new ResponseEntity<>(
					parkirservice.checkInHibernate(userpass[0], userpass[1], data.getJenisKendaraan(), data.getPlatNomor()),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("salah", HttpStatus.BAD_GATEWAY);
		}

	}

	@PutMapping("/hibernate/checkOut")
	public ResponseEntity<?> checkOut(@RequestHeader("Authorization") String auth, @RequestBody String content) {
		try {
			String[] userpass = authUser(auth);
			ParkirData data = new ObjectMapper().readValue(content, ParkirData.class);
			return new ResponseEntity<>(parkirservice.checkOutHibernate(userpass[0], userpass[1], data.getPlatNomor()), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("salah", HttpStatus.BAD_GATEWAY);
		}

	}

	@GetMapping("/hibernate/listCheckIn")
	public ResponseEntity<List<ParkirData>> listCheckIn(@RequestHeader("Authorization") String auth) {
		List<ParkirData> listData = new ArrayList<>();
		try {
			String[] userpass = authUser(auth);
			listData = parkirservice.listCheckInHibernate(userpass[0], userpass[1]);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(listData, HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/hibernate/listCheckOut")
	public ResponseEntity<List<ParkirData>> listCheckOut(@RequestHeader("Authorization") String auth) {
		List<ParkirData> listData = new ArrayList<>();
		try {
			String[] userpass = authUser(auth);
			listData = parkirservice.listCheckOutHibernate(userpass[0], userpass[0]);
		} catch (Exception e) {
			return new ResponseEntity<>(listData, HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@PostMapping("/hibernate/addUser")
	public ResponseEntity<?> addUser(@RequestHeader("Authorization") String auth, @RequestBody String content) {
		try {
			String[] userpass = authUser(auth);
			User u = new ObjectMapper().readValue(content, User.class);
			return new ResponseEntity<>(parkirservice.insertUserHibernate(u.getUsername(), u.getPassword(), userpass[0], userpass[1]), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("salah", HttpStatus.BAD_GATEWAY);
		}
	}
	
	@PostMapping("/jpa/checkIn")
	public ResponseEntity<?> checkInjpa(@RequestHeader("Authorization") String auth, @RequestBody String content) {
		try {
			String[] userpass = authUser(auth);
			ParkirData data = new ObjectMapper().readValue(content, ParkirData.class);
			return new ResponseEntity<>(
					parkirservice.checkInJpa(userpass[0], userpass[1], data.getJenisKendaraan(), data.getPlatNomor()),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("salah", HttpStatus.BAD_GATEWAY);
		}

	}

	@PutMapping("/jpa/checkOut")
	public ResponseEntity<?> checkOutjpa(@RequestHeader("Authorization") String auth, @RequestBody String content) {
		try {
			String[] userpass = authUser(auth);
			ParkirData data = new ObjectMapper().readValue(content, ParkirData.class);
			return new ResponseEntity<>(parkirservice.checkOutJpa(userpass[0], userpass[1], data.getPlatNomor()), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("salah", HttpStatus.BAD_GATEWAY);
		}

	}

	@GetMapping("/jpa/listCheckIn")
	public ResponseEntity<List<ParkirData>> listCheckInjpa(@RequestHeader("Authorization") String auth) {
		List<ParkirData> listData = new ArrayList<>();
		try {
			String[] userpass = authUser(auth);
			listData = parkirservice.listCheckInJpa(userpass[0], userpass[1]);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(listData, HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	
	@PostMapping("/jpa/addUser")
	public ResponseEntity<?> addUserjpa(@RequestHeader("Authorization") String auth, @RequestBody String content) {
		try {
			String[] userpass = authUser(auth);
			User u = new ObjectMapper().readValue(content, User.class);
			return new ResponseEntity<>(parkirservice.insertUserJpa(u.getUsername(), u.getPassword(), userpass[0], userpass[1]), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("salah", HttpStatus.BAD_GATEWAY);
		}
	}

}

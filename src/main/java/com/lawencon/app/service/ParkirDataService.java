package com.lawencon.app.service;

import java.util.List;

import com.lawencon.app.model.ParkirData;

public interface ParkirDataService {
	public String checkInHibernate(String username, String password, String jenisKendaraan, String platNomor) throws Exception;

	public String checkOutHibernate(String username, String password, String platNomor) throws Exception;

	public List<ParkirData> listCheckInHibernate(String username, String password) throws Exception;

	public List<ParkirData> listCheckOutHibernate(String username, String password) throws Exception;

	public String insertUserHibernate(String username, String password, String user, String pass) throws Exception;

	public String checkInJpa(String username, String password, String jenisKendaraan, String platNomor) throws Exception;

	public String checkOutJpa(String username, String password, String platNomor) throws Exception;

	public String insertUserJpa(String username, String password, String user, String pass) throws Exception;

	public List<ParkirData> listCheckInJpa(String username, String password) throws Exception;

}

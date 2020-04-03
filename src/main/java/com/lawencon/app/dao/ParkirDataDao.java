package com.lawencon.app.dao;

import java.util.List;

import com.lawencon.app.model.ParkirData;

public interface ParkirDataDao {
	public void checkIn(ParkirData parkirData) throws Exception;

	public boolean checkOut(String platNomor, String date) throws Exception;

	public List<ParkirData> findAllCheckIn() throws Exception;

	public List<ParkirData> findAllCheckOut() throws Exception;

	public String getTimeCheckIn(String platNomor) throws Exception;
}

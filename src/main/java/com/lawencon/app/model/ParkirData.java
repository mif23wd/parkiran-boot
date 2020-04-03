package com.lawencon.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_h_parkirdata")
public class ParkirData {
	@Id
	@GeneratedValue
	private Long id;

	private String jenisKendaraan;

	private String platNomor;

	private String checkIn;
	private String checkOut;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJenisKendaraan() {
		return jenisKendaraan;
	}

	public void setJenisKendaraan(String jenisKendaraan) {
		this.jenisKendaraan = jenisKendaraan;
	}

	public String getPlatNomor() {
		return platNomor;
	}

	public void setPlatNomor(String platNomor) {
		this.platNomor = platNomor;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

}

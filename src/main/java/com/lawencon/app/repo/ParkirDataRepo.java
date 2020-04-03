package com.lawencon.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.app.model.ParkirData;

@Repository
public interface ParkirDataRepo extends JpaRepository<ParkirData, Long> {
	public List<ParkirData> findByPlatNomor(String platNomer);
}

package com.lawencon.app.dao.impl.jpa;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.lawencon.app.dao.ParkirDataDao;
import com.lawencon.app.model.ParkirData;
import com.lawencon.app.repo.ParkirDataRepo;

@Transactional
@Repository("pd_repo_jpa")
public class ParkirDataDaoJpaImpl implements ParkirDataDao {

	@Autowired
	ParkirDataRepo parkirDataRepo;

	@Override
	public void checkIn(ParkirData parkirData) throws Exception {
		// TODO Auto-generated method stub
		parkirDataRepo.save(parkirData);
		
	}

	@Override
	public boolean checkOut(String platNomor, String date) throws Exception {
		// TODO Auto-generated method stub
		List<ParkirData> datas = new ArrayList<>(); 
		datas = parkirDataRepo.findByPlatNomor(platNomor);
		if (datas.isEmpty()) {
			return false;
		}else {
			ParkirData data = datas.get(datas.size()-1);
			data.setCheckOut(date);
			parkirDataRepo.save(data);
			return true;
		}
		
	}

	@Override
	public List<ParkirData> findAllCheckIn() throws Exception {
		// TODO Auto-generated method stub
		return parkirDataRepo.findAll();
	}

	@Override
	public List<ParkirData> findAllCheckOut() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTimeCheckIn(String platNomor) throws Exception {
		// TODO Auto-generated method stub
		List<ParkirData> datas = new ArrayList<>(); 
		datas = parkirDataRepo.findByPlatNomor(platNomor);
		if (datas.isEmpty()) {
			return "empty";
		}else {
			ParkirData data = datas.get(datas.size()-1);
			return data.getCheckIn();
		}
	}

	

}

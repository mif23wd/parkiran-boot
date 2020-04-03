/*
 * author M.Ihsan Fadhil
 */
package com.lawencon.app.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.ParkirDataDao;
import com.lawencon.app.model.ParkirData;
import com.lawencon.app.repo.HibernateRepo;

@Transactional
@Repository("pd_repo_hibernate")
public class ParkirDataDaoHibernateImpl extends HibernateRepo implements ParkirDataDao{

	@Override
	public void checkIn(ParkirData parkirData) throws Exception {
		// TODO Auto-generated method stub
		em.persist(parkirData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkOut(String platNomor, String date) throws Exception {
		// TODO Auto-generated method stub
		Query q = em.createQuery("from ParkirData where platNomor = :param");
		q.setParameter("param", platNomor);
		List<ParkirData> temp = new ArrayList<>();
		temp = q.getResultList();
		if (temp.isEmpty()) {
			
			return false;
		}else {
			ParkirData data = temp.get(temp.size()-1);
			data.setCheckOut(date);
			em.merge(data);
			return true;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParkirData> findAllCheckIn() throws Exception {
		// TODO Auto-generated method stub
		Query q = em.createQuery("from ParkirData");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParkirData> findAllCheckOut() throws Exception {
		// TODO Auto-generated method stub
		Query q = em.createQuery("from ParkirData where checkOut is not null");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getTimeCheckIn(String platNomor) throws Exception {
		// TODO Auto-generated method stub
		Query q = em.createQuery("from ParkirData where platNomor = :param");
		q.setParameter("param", platNomor);
		List<ParkirData> temp = new ArrayList<>();
		temp = q.getResultList();
		if (temp.size() > 0) {
			ParkirData data = temp.get(temp.size()-1);
			return data.getCheckIn();
		}else {
			return "empty";
		}
		
	}

}

package com.lawencon.app.dao.impl.hibernate;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.lawencon.app.dao.UserDao;
import com.lawencon.app.model.User;
import com.lawencon.app.repo.HibernateRepo;

@Transactional
@Repository("user_repo_hibernate")
public class UserDaoHibernateImpl extends HibernateRepo implements UserDao {

	@Override
	public void insert(User user) throws Exception {
		// TODO Auto-generated method stub
		em.persist(user);
	}

	@Override
	public boolean findByUsernamePassword(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		Query q = em.createQuery("from User where username = :param1 and password = :param2");
		q.setParameter("param1", username);
		q.setParameter("param2", password);
		if (q.getResultList().isEmpty()) {
			return false;
		}else {
			return true;
		}
		
	}

}

package com.lawencon.app.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class HibernateRepo {
	
	@PersistenceContext
	protected EntityManager em;
}

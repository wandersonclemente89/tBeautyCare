package br.com.tbeautycare.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class BaseDAO {
	
	private SessionFactory factory;
	Session session;

	public BaseDAO() {
		factory = new Configuration().configure().buildSessionFactory();
		session = factory.openSession();
	}

}

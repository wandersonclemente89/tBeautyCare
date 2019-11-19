package br.com.tbeautycare.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BaseDAO {

	private SessionFactory factory;
	private Session session;
	private Transaction tx;
	

	public BaseDAO() {
		factory = new Configuration().configure().buildSessionFactory();
		session = factory.openSession();
		setTx(getSession().beginTransaction());
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Transaction getTx() {
		return tx;
	}

	public void setTx(Transaction tx) {
		this.tx = tx;
	}


}

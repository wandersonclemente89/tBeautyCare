package br.com.tbeautycare.dao;

import org.hibernate.Transaction;

import br.com.tbeautycare.models.Customer;

public class CustomerDAO extends BaseDAO {
	private Transaction tx;

	public CustomerDAO() {
		super();
		tx = session.beginTransaction();
	}

	public void insert(Customer custumer) {
		session.save(custumer);
		tx.commit();
		session.close();
	}

}

package br.com.tbeautycare.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Transaction;

import br.com.tbeautycare.models.Customer;

public class CustomerDAO extends BaseDAO {
	private Transaction tx;

	public CustomerDAO() {
		super();
		tx = getSession().beginTransaction();
	}

	public void insert(Customer custumer) {
		getSession()
		.save(custumer);
		tx.commit();
	}

	public void remove(Customer custumer) {
		getSession()
		.delete(custumer);
		tx.commit();
	}

	public List<Customer> readAll() {
		return getSession()
				.createQuery("SELECT a FROM Customer a", Customer.class)
				.getResultList();
	}

	public boolean hasId(long id) {
		return getSession().createQuery("FROM Customer WHERE id = :id").setParameter("id", id).getSingleResult() != null ? true : false;
	}

	public Customer getById(long id) {
		return (Customer) getSession()
				.createQuery("FROM Customer WHERE id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
}

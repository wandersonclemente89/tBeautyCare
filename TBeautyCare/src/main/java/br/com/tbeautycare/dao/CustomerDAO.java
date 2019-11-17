package br.com.tbeautycare.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Transaction;

import br.com.tbeautycare.models.Customer;

public class CustomerDAO extends BaseDAO {
	private Transaction tx;
	
	public CustomerDAO() {
		super();
		tx = getSession().beginTransaction();
	}

	public void insert(Customer customer) {
		getSession().saveOrUpdate(customer);
		tx.commit();
	}

	public void remove(Customer custumer) {
		getSession().delete(custumer);
		tx.commit();

	}

	public List<Customer> readAll() {
		return getSession().createQuery("SELECT a FROM Customer a", Customer.class).getResultList();
	}

	public Customer getById(long id) {
		return getSession().get(Customer.class, id,LockMode.PESSIMISTIC_WRITE);
	}
	
}

package br.com.tbeautycare.dao;

import java.util.List;

import br.com.tbeautycare.models.Customer;

public class CustomerDAO extends BaseDAO {

	public CustomerDAO() {
		super();
	}

	public void insert(Customer customer) {
		getSession().saveOrUpdate(customer);
		getTx().commit();
	}

	public void remove(Customer custumer) {
		getSession().delete(custumer);
		getTx().commit();

	}

	public List<Customer> readAll() {
		return getSession().createQuery("SELECT a FROM Customer a", Customer.class).getResultList();
	}

	public Customer getById(long id) {
		return getSession().get(Customer.class, id);
	}

}

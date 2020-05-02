package br.com.tbeautycare.dao;

import java.util.List;

import org.hibernate.LockMode;

import br.com.tbeautycare.models.Contact;

public class ContactDAO extends BaseDAO {

	public ContactDAO() {
		super();
	}

	public void insert(Contact contact) {
		getSession().saveOrUpdate(contact);
		getTx().commit();
		getSession().close();
	}

	public void remove(Contact contact) {
		getSession().delete(contact);
		getTx().commit();
		getSession().close();
	}

	public List<Contact> readAll() {
		return getSession().createQuery("SELECT a FROM Offer a", Contact.class).getResultList();
	}

	public Contact getById(long id) {
		return getSession().get(Contact.class, id, LockMode.PESSIMISTIC_WRITE);
	}
}

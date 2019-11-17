package br.com.tbeautycare.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Transaction;

import br.com.tbeautycare.models.Contact;

public class ContactDAO extends BaseDAO {
	private Transaction tx;
	public ContactDAO() {
		super();
		tx = getSession().beginTransaction();
	}

	public void insert(Contact contact) {
		getSession().saveOrUpdate(contact);
		tx.commit();
	}

	public void remove(Contact contact) {
		getSession().delete(contact);
		tx.commit();
	}

	public List<Contact> readAll() {
		return getSession().createQuery("SELECT a FROM Customer a", Contact.class).getResultList();
	}

	public Contact getById(long id) {
		return getSession().get(Contact.class,id,LockMode.PESSIMISTIC_WRITE);
	}
}

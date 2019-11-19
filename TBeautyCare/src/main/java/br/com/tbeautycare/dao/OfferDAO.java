package br.com.tbeautycare.dao;

import java.util.List;

import br.com.tbeautycare.models.Offer;

public class OfferDAO extends BaseDAO {

	public OfferDAO() {
		super();
	}

	public void insert(Offer offer) {
		getSession().saveOrUpdate(offer);
		getTx().commit();
	}

	public void remove(Offer offer) {
		getSession().delete(offer);
		getTx().commit();
	}

	public List<Offer> readAll() {
		return getSession().createQuery("SELECT a FROM Offer a", Offer.class).getResultList();
	}

	public Offer getById(long id) {
		return getSession().get(Offer.class, id);
	}

}

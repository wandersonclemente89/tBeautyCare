package br.com.tbeautycare.dao;

import java.util.Collection;
import java.util.List;

import br.com.tbeautycare.models.Customer;
import br.com.tbeautycare.models.Schedule;

public class ScheduleDAO extends BaseDAO{

	public ScheduleDAO() {
		super();
	}
	public List<Schedule> readAll(long customerId) {
		return (List<Schedule>) getSession().get(Customer.class, customerId).getSchedules();
	}

	public void insert(long customerId, Schedule schedule) {
		Customer customer = getSession().get(Customer.class, customerId);
		Collection <Schedule> currentSchedules =  customer.getSchedules();
		currentSchedules.add(schedule);
		customer.setSchedules(currentSchedules);
		getSession().saveOrUpdate(customer);
		getTx().commit();
		getSession().close();
	}

	public Schedule getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(Schedule schedule) {
		// TODO Auto-generated method stub

	}

}

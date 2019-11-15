package br.com.tbeautycare.services;

import br.com.tbeautycare.dao.CustomerDAO;
import br.com.tbeautycare.models.Customer;

public class Test {

	public static void main(String[] args) {
		Customer customer =  new Customer();
		customer.setCustomerEmail("cleide@inatel.br");
		customer.setCustomerName("Cleide");
		customer.setCustomerPassword("oi");
		customer.setCustomerPhoneNumber("oieoq");
		
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.insert(customer);

	}

}

package br.com.tbeautycare.services;

import javax.persistence.NoResultException;

import br.com.tbeautycare.HttpResponseCode;
import br.com.tbeautycare.dao.CustomerDAO;
import br.com.tbeautycare.models.Customer;
import br.com.tbeautycare.models.ErrorRepresentation;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("customer")
public class CustomerService {

	private ErrorRepresentation error;
	@Context
	UriInfo uriInfo;

	public CustomerService() {
		error = new ErrorRepresentation();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response readAll() {
		CustomerDAO custumerDao = new CustomerDAO();
		return Response.status(HttpResponseCode.OK).entity(custumerDao.readAll()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Request-Methods", "GET").allow("OPTIONS").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response insertCustomer(Customer customer) {
//
//		OfferDAO offerDAO = new OfferDAO();
//		List<Offer> offers =offerDAO.readAll();
//		for (Offer offer : offers) {
//			String name = offer.getName();
//			customer.getSchedules().forEach(schedule->schedule.getOffers().forEach(offer1->
//			offer1.getName().equals(name)));
//		}
//
CustomerDAO custumerDao = new CustomerDAO();
custumerDao.insert(customer);
			return Response.status(HttpResponseCode.CREATED).entity(customer).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "POST").allow("OPTIONS").build();

	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeCustomer(@PathParam("id") long id) {
		try {
			CustomerDAO custumerDao = new CustomerDAO();
			Customer customer = custumerDao.getById(id);
			if ( customer!=null) {
			custumerDao.remove(customer);
			return Response.status(HttpResponseCode.NO_CONTENT).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "DELETE").allow("OPTIONS").build();
			}else {
				throw new NoResultException();
			}
		} catch (NoResultException e) {
			error.setCode(HttpResponseCode.NOT_FOUND);
			error.setReason("CUSTOMER_NOT_FOUND");
			error.setDetails("Customer: [ " + id + " ] was not found!");
			return Response.status(HttpResponseCode.NOT_FOUND).entity(error).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "DELETE").allow("OPTIONS").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response geCustomerById(@PathParam("id") long id) {
		try {
			CustomerDAO custumerDao = new CustomerDAO();
			Customer customer = custumerDao.getById(id);
			if (customer != null) {
				return Response.status(HttpResponseCode.OK).entity(customer).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Request-Methods", "GET").allow("OPTIONS").build();
			} else {
				throw new NoResultException();
			}
		} catch (NoResultException e) {
			error.setCode(HttpResponseCode.NOT_FOUND);
			error.setReason("CUSTOMER_NOT_FOUND");
			error.setDetails("Customer: [ " + id + " ] was not found!");
			return Response.status(HttpResponseCode.NOT_FOUND).entity(error).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "GET").allow("OPTIONS").build();
		}
	}

}

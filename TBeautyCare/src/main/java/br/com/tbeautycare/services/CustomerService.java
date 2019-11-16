package br.com.tbeautycare.services;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.tbeautycare.HttpResponseCode;
import br.com.tbeautycare.dao.CustomerDAO;
import br.com.tbeautycare.models.Customer;
import br.com.tbeautycare.models.ErrorRepresentation;

@Path("customer")
public class CustomerService {

	private CustomerDAO dao;
	private ErrorRepresentation error;


	public CustomerService() {
		dao = new CustomerDAO();
		error = new ErrorRepresentation();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response readAll() {
		return Response.status(HttpResponseCode.OK).entity(dao.readAll()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Request-Methods", "GET").allow("OPTIONS").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response insertCustomer(Customer customer) {
		dao.insert(customer);
		return Response.status(HttpResponseCode.CREATED).entity(customer).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Request-Methods", "POST").allow("OPTIONS").build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeCustomer(@PathParam("id") long id) {
		try {
			dao.remove(dao.getById(id));
			return Response.status(HttpResponseCode.NO_CONTENT).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "DELETE").allow("OPTIONS").build();
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
			return Response.status(HttpResponseCode.OK).entity(dao.getById(id)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "GET").allow("OPTIONS").build();
		} catch (NoResultException e) {
			error.setCode(HttpResponseCode.NOT_FOUND);
			error.setReason("CUSTOMER_NOT_FOUND");
			error.setDetails("Customer: [ " + id + " ] was not found!");
			return Response.status(HttpResponseCode.NOT_FOUND).entity(error).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "GET").allow("OPTIONS").build();
		}
	}
}

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
import br.com.tbeautycare.dao.OfferDAO;
import br.com.tbeautycare.models.ErrorRepresentation;
import br.com.tbeautycare.models.Offer;

@Path("offer")
public class OfferService {

	private OfferDAO offerDao;
	private ErrorRepresentation error;

	public OfferService() {
		offerDao = new OfferDAO();
		error = new ErrorRepresentation();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response readAll() {
		return Response.status(HttpResponseCode.OK).entity(offerDao.readAll()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Request-Methods", "GET").allow("OPTIONS").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response insertCustomer(Offer offer) {
		offerDao.insert(offer);
			return Response.status(HttpResponseCode.CREATED).entity(offer).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "POST").allow("OPTIONS").build();
		
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeOffer(@PathParam("id") long id) {
		try {
			Offer offer = offerDao.getById(id);
			if ( offer!=null) {
			offerDao.remove(offer);
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
	public Response geOfferById(@PathParam("id") long id) {
		try {
			Offer offer = offerDao.getById(id);
			if (offer != null) {
				return Response.status(HttpResponseCode.OK).entity(offer).header("Access-Control-Allow-Origin", "*")
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

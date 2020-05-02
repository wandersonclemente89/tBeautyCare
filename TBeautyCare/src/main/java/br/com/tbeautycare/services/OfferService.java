package br.com.tbeautycare.services;

import javax.persistence.NoResultException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import br.com.tbeautycare.HttpResponseCode;
import br.com.tbeautycare.dao.OfferDAO;
import br.com.tbeautycare.models.ErrorRepresentation;
import br.com.tbeautycare.models.Offer;

@Path("offer")
public class OfferService {

	private ErrorRepresentation error;

	public OfferService() {
		error = new ErrorRepresentation();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response readAll() {
		OfferDAO offerDao = new OfferDAO();
		return Response.status(HttpResponseCode.OK).entity(offerDao.readAll()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Request-Methods", "GET").allow("OPTIONS").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response insertCustomer(Offer offer) {
		OfferDAO offerDao = new OfferDAO();
		offerDao.insert(offer);
			return Response.status(HttpResponseCode.CREATED).entity(offer).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "POST").allow("OPTIONS").build();

	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeOffer(@PathParam("id") long id) {
		try {
			OfferDAO offerDao = new OfferDAO();
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
			OfferDAO offerDao = new OfferDAO();
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

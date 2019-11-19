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
import br.com.tbeautycare.dao.ScheduleDAO;
import br.com.tbeautycare.models.ErrorRepresentation;
import br.com.tbeautycare.models.Schedule;

@Path("customer/{id}/schedule")
public class ScheduleService {

	private ScheduleDAO scheduleDao;
	private CustomerDAO cutomerDao;
	private ErrorRepresentation error;

	public ScheduleService() {
		scheduleDao = new ScheduleDAO();
		error = new ErrorRepresentation();
		cutomerDao = new CustomerDAO();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response readAll(@PathParam("id") long customerId) {
		try {
		return Response.status(HttpResponseCode.OK).entity(scheduleDao.readAll(customerId)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Request-Methods", "GET").allow("OPTIONS").build();
		} catch (NullPointerException e) {
			error.setCode(HttpResponseCode.NOT_FOUND);
			error.setReason("CUSTOMER_NOT_FOUND");
			error.setDetails("Customer: [ " + customerId + " ] was not found!");
			return Response.status(HttpResponseCode.NOT_FOUND).entity(error).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "DELETE").allow("OPTIONS").build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response insertSchedule(@PathParam("id") long customerId, Schedule schedule) {
		scheduleDao.insert(customerId, schedule);
			return Response.status(HttpResponseCode.OK).entity(cutomerDao.getById(customerId)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "POST").allow("OPTIONS").build();
		
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeSchedule(@PathParam("id") long id) {
		try {
			Schedule schedule = scheduleDao.getById(id);
			if ( schedule!=null) {
			scheduleDao.remove(schedule);
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
	public Response geScheduleById(@PathParam("id") long id) {
		try {
			Schedule schedule = scheduleDao.getById(id);
			if (schedule != null) {
				return Response.status(HttpResponseCode.OK).entity(schedule).header("Access-Control-Allow-Origin", "*")
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

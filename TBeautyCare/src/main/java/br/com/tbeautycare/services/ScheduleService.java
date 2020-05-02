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
import br.com.tbeautycare.dao.CustomerDAO;
import br.com.tbeautycare.dao.ScheduleDAO;
import br.com.tbeautycare.models.ErrorRepresentation;
import br.com.tbeautycare.models.Schedule;

@Path("customer/{id}/schedule")
public class ScheduleService {

	private ErrorRepresentation error;

	public ScheduleService() {
		error = new ErrorRepresentation();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response readAll(@PathParam("id") long customerId) {
		try {
			ScheduleDAO scheduleDao = new ScheduleDAO();
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
		ScheduleDAO scheduleDao = new ScheduleDAO();
		CustomerDAO cutomerDao = new CustomerDAO();
		scheduleDao.insert(customerId, schedule);
			return Response.status(HttpResponseCode.OK).entity(cutomerDao.getById(customerId)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Request-Methods", "POST").allow("OPTIONS").build();

	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeSchedule(@PathParam("id") long id) {
		try {
			ScheduleDAO scheduleDao = new ScheduleDAO();
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
			ScheduleDAO scheduleDao = new ScheduleDAO();
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

package controllers;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DTO.CarreraConEstudiantes;
import entities.Carrera;
import repositories.CarreraRepositoryImpl;

@Path("carreras")
public class CarreraController {
	
	public CarreraRepositoryImpl repo;
	
	public CarreraController () {
		this.repo = CarreraRepositoryImpl.getInstance();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int key) {
		try {
			Carrera c = repo.get(key);
			if (c != null) {
				return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(c).build();
			}
			return Response.status(404).header("Access-Control-Allow-Origin", "*").entity(new String("No se encontro la carrera")).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response create(Carrera c) {
		try {
			this.repo.create(c);
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(new String("Se creo la carrera")).build();
		} catch (Exception e) {
			 System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response remove(@PathParam("id") int id) {
		try {
			if(this.repo.remove(id)) {
				return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
			}else {
				return Response.status(400).header("Access-Control-Allow-Origin", "*").entity(new String("No se encontro la carrera")).build();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response update(Carrera c) {
		try {
			if(this.repo.update(c)) {
				return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(new String("Se actualizo la carrera")).build();
			}else {
				return Response.status(404).header("Access-Control-Allow-Origin", "*").entity(new String("No se encontro la carrera")).build();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	@GET
	@Path("cantidad-inscriptos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCarrerasWithEstudiantesByCantidadInscriptos(){
		try {
			List<CarreraConEstudiantes> carreraConEstudiantes = this.repo.getCarrerasWithEstudiantesByCantidadInscriptos();
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(carreraConEstudiantes).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
		
	}

}

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

import entities.Carrera;
import entities.Estudiante;
import repositories.CarreraRepositoryImpl;
import repositories.EstudianteRepositoryImpl;

@Path("estudiantes")
public class EstudianteController {
	
public EstudianteRepositoryImpl repoEstudiante;
public CarreraRepositoryImpl repoCarrera;
	
	public EstudianteController () {
		this.repoEstudiante = EstudianteRepositoryImpl.getInstance();
		this.repoCarrera = CarreraRepositoryImpl.getInstance();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		try {
			Estudiante estudiante = this.repoEstudiante.getEstudianteByLibreta(id);
			if (estudiante != null) {
				return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(estudiante).build();
			}
			return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	@GET
	@Path("/dni")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByDni() {
		try {
			List<Estudiante> list = this.repoEstudiante.getEstudiantesByDni();
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(list).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	@GET
	@Path("/genero/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByGenero(@PathParam("genero") String genero) {
		try {
			List<Estudiante> list = this.repoEstudiante.getEstudiantesByGenero(genero);
			if (list != null) {
				return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(list).build();	
			}
			return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	@GET
	@Path("/carrera-ciudad/{id-carrera}/{str-ciudad}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByCarreraAndCiudad(@PathParam("id-carrera") int carrera, @PathParam("str-ciudad") String ciudad) {
		try {
			List<Estudiante> list = this.repoEstudiante.getEstudiantesByCarreraAndCiudad(carrera, ciudad);
			if (list != null) {
				return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(list).build();
			}
			return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create (Estudiante es) {
		System.out.println("Line 92 "+es);
		try {
			this.repoEstudiante.create(es);
			return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(es).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("id") int id) {
		try {
			if(this.repoEstudiante.remove(id)) {
				return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
			}else {
				return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Estudiante c) {
		try {
			if(this.repoEstudiante.update(c)) {
				return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
			}else {
				return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	@POST
	@Path("/matricular/{id-estudiante}/{id-carrera}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response matricularse(@PathParam("id-estudiante") int estudiante, @PathParam("id-carrera") int carrera) {
		try {
			Estudiante e = this.repoEstudiante.get(estudiante);
			if(e == null){
				return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
			}
			Carrera c = this.repoCarrera.get(carrera);
			if(c == null){
				return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
			}
			this.repoEstudiante.matricularse(e, c);
			return Response.status(201).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	
	

}

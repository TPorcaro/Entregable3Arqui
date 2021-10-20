package controllers;

import java.util.List;

//import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DTO.ReporteCarrera;
import entities.Matricula;
import repositories.MatriculaRepository;
import repositories.MatriculaRepositoryImpl;

@Path("matriculas")
public class MatriculaController {

	MatriculaRepository repoMatricula;
	public MatriculaController () {
		this.repoMatricula = MatriculaRepositoryImpl.getInstance();
	}
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_HTML)
//	public String create(Matricula matricula) {
//		try {
//			this.repoMatricula.create(matricula);
//			return "Matricula creada";
//		} catch (Exception e) {
//			return e.getMessage();
//		}
//	}
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("id") Integer id) {
			try {
				if(this.repoMatricula.remove(id)) {
					return Response.status(200).header("Access-Control-Allow-Origin", "*").build();				
				}else {
					return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
			}
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") Integer id) {
		try {
			Matricula matricula = this.repoMatricula.get(id);
			if (matricula != null) {
				return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(matricula).build();
			}else {
				return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String update(Matricula matricula) {
//		if(this.repoMatricula.update(matricula)){
//			return "Matricula actualizada";
//		}else {
//			return "Error al actualizar matricula";
//		}
//	}
	@GET
	@Path("reporte")
	@Produces(MediaType.APPLICATION_JSON)
	public Response generarReporte(){
		try {
			List<ReporteCarrera> list = this.repoMatricula.generarReporte();
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(list).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
}

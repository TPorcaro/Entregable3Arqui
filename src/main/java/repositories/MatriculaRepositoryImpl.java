package repositories;

import java.util.List;

import javax.persistence.EntityManager;

import DTO.ReporteCarrera;
import EMF.EMF;
import entities.Matricula;

public class MatriculaRepositoryImpl implements MatriculaRepository {

	private EntityManager em;
	private static MatriculaRepositoryImpl instance;
	
	private MatriculaRepositoryImpl() {
		super();
		this.em = EMF.getEntityManager();
	}
	public static MatriculaRepositoryImpl getInstance() {
		if(instance == null) {
			return new MatriculaRepositoryImpl();
		}else {
			return instance;
		}
	}
	public void closeConnection() {
		this.em.close();
	}
	@Override
	public void create(Matricula matricula) {
		this.em.getTransaction().begin();
		this.em.persist(matricula);
		this.em.getTransaction().commit();
	}
	@Override
	public List<ReporteCarrera> generarReporte() {
		this.em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<ReporteCarrera> reporteCarreras = em.createQuery(
				"SELECT new DTO.ReporteCarrera(c.idCarrera, c.nombre, YEAR(m.fecha_inicio),sum(m.egresado+0), COUNT(m.estudiante))"
				+ "FROM Matricula m JOIN m.carrera c JOIN m.estudiante e GROUP BY (c.idCarrera) ORDER BY YEAR(m.fecha_inicio) ASC, c.nombre ASC") 
				.getResultList();

		if (!reporteCarreras.isEmpty()) {
			this.em.getTransaction().commit();
			return reporteCarreras;
		}
		this.em.getTransaction().commit();
		return null;
	}
	@Override
	public boolean remove(Integer key) {
		this.em.getTransaction().begin();
		Matricula auxMatricula = this.em.find(Matricula.class, key);
		if(auxMatricula != null) {
			this.em.remove(auxMatricula);
			this.em.getTransaction().commit();
			return true;
		}else {
			this.em.getTransaction().commit();
			return false;
		}
	}
	@Override
	public boolean update(Matricula entity) {
		this.em.getTransaction().begin();
		Matricula auxMatricula = this.em.find(Matricula.class, entity.getId());
		if(auxMatricula != null) {
			auxMatricula.setFechaInicio(entity.getFechaInicio());
			this.em.getTransaction().commit();
			return true;
		}else {
			this.em.getTransaction().commit();
			return false;
		}
	}
	@Override
	public Matricula get(Integer key) {
		this.em.getTransaction().begin();
		Matricula matricula = this.em.find(Matricula.class, key);
		this.em.getTransaction().commit();
		return matricula;
	}

}

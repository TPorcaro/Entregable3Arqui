package repositories;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

import EMF.EMF;
import entities.Carrera;
import entities.Estudiante;
import entities.Matricula;

/**
 * 
 * @author Juan Cruz
 * @author Nicolas
 * @author Tomas
 *
 */
public class EstudianteRepositoryImpl implements EstudianteRepository {

	private EntityManager em;
	private static EstudianteRepositoryImpl instance;

	private EstudianteRepositoryImpl() {
		super();
		this.em = EMF.getEntityManager();
	}
	public static EstudianteRepositoryImpl getInstance() {
		if(instance == null) {
			return new EstudianteRepositoryImpl();
		}else {
			return instance;
		}
	}

	public void closeConnection() {
		this.em.close();
	}

	@Override
	public void create(Estudiante estudiante) {
		this.em.getTransaction().begin();
		Estudiante aux = this.em.find(Estudiante.class, estudiante.getNmro_libreta());
		if(aux == null) {
			this.em.persist(estudiante);			
		}else {
			aux = this.em.merge(estudiante);
		}
		this.em.getTransaction().commit();

	}

	@Override
	public void matricularse(Estudiante estudiante, Carrera carrera) {
		this.em.getTransaction().begin();
		Matricula matricula = new Matricula(estudiante, carrera, new Timestamp(System.currentTimeMillis()), false);
		Estudiante estudiantePersisted = this.em.find(Estudiante.class, estudiante.getNmro_libreta());
		this.em.persist(matricula);
		estudiantePersisted.addMatricula(matricula);
		this.em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	// Probar
	public List<Estudiante> getEstudiantesByDni() {
		this.em.getTransaction().begin();
		List<Estudiante> estudiantesByDni = this.em.createQuery("SELECT e FROM Estudiante e ORDER BY e.dni")
				.getResultList();
		this.em.getTransaction().commit();
		return estudiantesByDni;
	}

	@Override
	public Estudiante getEstudianteByLibreta(int libreta) {
		this.em.getTransaction().begin();
		Estudiante estudiante = this.em.find(Estudiante.class, libreta);
		this.em.getTransaction().commit();
		return estudiante;
	}

	@SuppressWarnings("unchecked")
	@Override
	// Probar
	public List<Estudiante> getEstudiantesByGenero(String genero) {
		this.em.getTransaction().begin();
		List<Estudiante> estudiantesByGenero = this.em
				.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero").setParameter("genero", genero)
				.getResultList();
		this.em.getTransaction().commit();
		return estudiantesByGenero;
	}

	@SuppressWarnings("unchecked")
	@Override
	// Probar
	public List<Estudiante> getEstudiantesByCarreraAndCiudad(int carrera, String ciudad) {
		this.em.getTransaction().begin();
		List<Estudiante> estudiantes = em.createQuery(
				"SELECT e FROM Estudiante e JOIN e.carreras_inscriptas s WHERE s.carrera.id =: carrera AND  e.ciudad_residencia =: ciudad")
				.setParameter("carrera", carrera).setParameter("ciudad", ciudad).getResultList();
		;
		this.em.getTransaction().commit();
		return estudiantes;
	}

	@Override
	public boolean remove(Integer key) {
		this.em.getTransaction().begin();
		Estudiante auxEstudiante = this.em.find(Estudiante.class, key);
		if(auxEstudiante != null) {
			this.em.remove(auxEstudiante);
			this.em.getTransaction().commit();
			return true;
		}else {
			this.em.getTransaction().commit();
			return false;
		}
		
	}

	@Override
	public boolean update(Estudiante entity) {
		this.em.getTransaction().begin();
		Estudiante auxEstudiante = this.em.find(Estudiante.class, entity.getNmro_libreta());
		if(auxEstudiante != null) {
			auxEstudiante.setApellido(entity.getApellido());
			auxEstudiante.setNombre(entity.getNombre());
			auxEstudiante.setCiudad_residencia(entity.getCiudad_residencia());
			auxEstudiante.setGenero(entity.getGenero());
			this.em.getTransaction().commit();
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Estudiante get(Integer key) {
		this.em.getTransaction().begin();
		Estudiante estudiante = this.em.find(Estudiante.class, key);
		this.em.getTransaction().commit();
		return estudiante;
	}

}

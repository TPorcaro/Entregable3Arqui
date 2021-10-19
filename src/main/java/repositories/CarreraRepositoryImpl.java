package repositories;

import java.util.List;
import javax.persistence.EntityManager;

import DTO.CarreraConEstudiantes;
import EMF.EMF;
import entities.Carrera;
import entities.Estudiante;

/**
 * 
 * @author Juan Cruz
 * @author Nicolas
 * @author Tomas
 *
 */
public class CarreraRepositoryImpl implements CarreraRepository {

	private EntityManager em;
	private static CarreraRepositoryImpl instance;

	private CarreraRepositoryImpl() {
		super();
		this.em = EMF.getEntityManager();
	}
	public static CarreraRepositoryImpl getInstance() {
		if(instance == null) {
			return new CarreraRepositoryImpl();
		}else {
			return instance;
		}
	}

	public void closeConnection() {
		this.em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CarreraConEstudiantes> getCarrerasWithEstudiantesByCantidadInscriptos() {
		this.em.getTransaction().begin();
		List<CarreraConEstudiantes> carreraConEstudiantes = em.createQuery(
				"SELECT new DTO.CarreraConEstudiantes(c,COUNT(m.estudiante)) FROM Matricula m JOIN m.carrera c GROUP BY c.idCarrera ORDER BY COUNT(m.estudiante) DESC")
				.getResultList();

		this.em.getTransaction().commit();
		if (!carreraConEstudiantes.isEmpty()) {
			return carreraConEstudiantes;
		}
		return null;
	}

	@Override
	public void create(Carrera carrera) {
		this.em.getTransaction().begin();
		System.out.println(carrera + " repo");
		Carrera aux = this.em.find(Carrera.class, carrera.getIdCarrera());
		if(aux == null) {
			this.em.persist(carrera);			
		}else {
			aux = this.em.merge(carrera);
		}
		this.em.getTransaction().commit();
	}

	@Override
	public Carrera getCarreraById(int id) {
		this.em.getTransaction().begin();
		Carrera carrera = this.em.find(Carrera.class, id);
		this.em.getTransaction().commit();
		return carrera;
	}

	@Override
	public boolean remove(Integer key) {
		this.em.getTransaction().begin();
		Carrera auxCarrera = this.em.find(Carrera.class, key);
		if(auxCarrera != null) {
			this.em.remove(auxCarrera);
			this.em.getTransaction().commit();
			return true;
		}else {
			this.em.getTransaction().commit();
			return false;
		}
	}

	@Override
	public boolean update(Carrera entity) {
		this.em.getTransaction().begin();
		Carrera auxCarrera = this.em.find(Carrera.class, entity.getIdCarrera());
		if(auxCarrera != null) {
			auxCarrera.setNombre(entity.getNombre());
			this.em.getTransaction().commit();
			return true;
		}else {
			this.em.getTransaction().commit();
			return false;
		}
	}

	@Override
	public Carrera get(Integer key) {
		this.em.getTransaction().begin();
		Carrera carrera = this.em.find(Carrera.class, key);
		this.em.getTransaction().commit();
		return carrera;
	}

}

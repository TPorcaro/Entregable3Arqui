package repositories;

import java.util.List;

import DTO.CarreraConEstudiantes;
import entities.Carrera;

/**
 * 
 * @author Juan Cruz
 * @author Nicolas
 * @author Tomas
 */
public interface CarreraRepository extends RepositoryInterface<Carrera, Integer> {

	/**
	 * Retorna una lista de carreras con estudiantes, ordenada por la cantidad de
	 * inscriptos.
	 * 
	 * @return lista de carreras
	 */
	List<CarreraConEstudiantes> getCarrerasWithEstudiantesByCantidadInscriptos();

	Carrera getCarreraById(int id);
}

package repositories;

import java.util.List;

import entities.Carrera;
import entities.Estudiante;

	/**
	 * 
	 * @author Juan Cruz
	 * @author Nicolas
	 * @author Tomas
	 */
public interface EstudianteRepository extends RepositoryInterface<Estudiante, Integer> {

	/**
	 * Crea un registro en matricula con los datos del estudiante y la carrera
	 * 
	 * @param estudiante
	 * @param carrera
	 */
	void matricularse(Estudiante estudiante, Carrera carrera);

	/**
	 * Obtiene todos los estudiantes ordenados por su dni
	 * 
	 * @return lista de Estudiantes
	 */
	List<Estudiante> getEstudiantesByDni();

	/**
	 * Obtiene un estudiante en base a su numero de libreta
	 * 
	 * @param libreta
	 * @return estudiante
	 */
	Estudiante getEstudianteByLibreta(int libreta);

	/**
	 * Obtiene todos los estudiantes en base a su genero
	 * 
	 * @param genero
	 * @return lista de estudiantes con un genero en concreto
	 */
	List<Estudiante> getEstudiantesByGenero(String genero);

	/**
	 * Obtiene todos los estudiantes de una determinada carrera filtrado por ciudad
	 * de residencia
	 * 
	 * @param carrera
	 * @param ciudad
	 * @return lista de estudiantes
	 */
	List<Estudiante> getEstudiantesByCarreraAndCiudad(int carrera, String ciudad);
}

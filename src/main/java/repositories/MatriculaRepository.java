package repositories;

import java.util.List;

import DTO.ReporteCarrera;
import entities.Matricula;

public interface MatriculaRepository extends RepositoryInterface<Matricula, Integer> {


	/**
	 * Genera un reporte de las carreras, que para cada carrera incluya informaci�n
	 * de los inscriptos y egresados por a�o. Ordenado alfabetica y
	 * cronologicamente.
	 * 
	 * @return reportes
	 */
	List<ReporteCarrera> generarReporte();
}

package repositories;

import java.util.List;

import DTO.ReporteCarrera;
import entities.Matricula;

public interface MatriculaRepository extends RepositoryInterface<Matricula, Integer> {


	/**
	 * Genera un reporte de las carreras, que para cada carrera incluya información
	 * de los inscriptos y egresados por año. Ordenado alfabetica y
	 * cronologicamente.
	 * 
	 * @return reportes
	 */
	List<ReporteCarrera> generarReporte();
}

package DTO;

/**
 * Clase encargada de generar un reporte que engloba atributos de las entidades
 * Matricula y Carrera
 * 
 * @author Juan Cruz
 * @author Nicolas
 * @author Tomas
 */
public class ReporteCarrera {
	private int idCarrera;
	private String nombreCarrera;
	private int anio;
	private int egresados;
	private int inscriptos;

	public ReporteCarrera() {
		super();
	}

	/**
	 * Constructor de la clase
	 * 
	 * @param idCarrera
	 * @param nombreCarrera
	 * @param anio
	 * @param egresados
	 * @param inscriptos
	 */
	public ReporteCarrera(int idCarrera, String nombreCarrera, int anio, long egresados, long inscriptos) {
		super();
		this.idCarrera = idCarrera;
		this.nombreCarrera = nombreCarrera;
		this.anio = anio;
		this.egresados =  (int)egresados;
		this.inscriptos = (int)inscriptos;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	public int getEgresados() {
		return egresados;
	}

	public void setEgresados(int egresados) {
		this.egresados = egresados;
	}

	public int getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(int inscriptos) {
		this.inscriptos = inscriptos;
	}

	@Override
	public String toString() {
		return "ReporteCarreras [idCarrera=" + idCarrera + ", nombreCarrera=" + nombreCarrera + ", anio=" + anio
				+ ", egresados=" + egresados + ", inscriptos=" + inscriptos + "]";
	}

}

package DTO;

import entities.Carrera;

public class CarreraConEstudiantes {

	private Carrera carrera;
	private Long estudiantes;
	public CarreraConEstudiantes() {
		super();
	}
	public CarreraConEstudiantes(Carrera carrera, Long estudiantes) {
		super();
		this.carrera = carrera;
		this.estudiantes = estudiantes;
	}
	
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	
	public Long getEstudiantes() {
		return estudiantes;
	}
	public void setEstudiantes(Long estudiantes) {
		this.estudiantes = estudiantes;
	}
	@Override
	public String toString() {
		return "CarreraConEstudiantes [carrera=" + carrera.toString() + ", estudiantes=" + estudiantes.toString() + "]";
	}
	
	
	
	
}

package entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Carrera {
	@Id
	private int idCarrera;
	@Column
	private String nombre;
	
	public Carrera() {
		super();
	}
	public Carrera(int idCarrera, String nombre) {
		super();
		this.idCarrera = idCarrera;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdCarrera() {
		return idCarrera;
	}
	@Override
	public String toString() {
		return "Carrera [idCarrera=" + idCarrera + ", nombre=" + nombre + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrera other = (Carrera) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	
}

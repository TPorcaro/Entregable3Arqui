package entities;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Matricula {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@ManyToOne
	@JoinColumn(name = "nmro_libreta")
	Estudiante estudiante;
	@ManyToOne
	@JoinColumn(name = "idCarrera")
	Carrera carrera;
	@Column
	Timestamp fecha_inicio;
	@Column
	boolean egresado;
	public Matricula() {
		super();
	}
	public Matricula(Estudiante estudiante, Carrera carrera, Timestamp fecha_inicio, boolean egresado) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fecha_inicio = fecha_inicio;
		this.egresado = egresado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		return Objects.equals(carrera, other.carrera) && Objects.equals(estudiante, other.estudiante);
	} 
	
	public int getId() {
		return id;
	}

	public Timestamp getFechaInicio() {
		return fecha_inicio;
	}
	public void setFechaInicio(Timestamp antiguedad) {
		this.fecha_inicio = antiguedad;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	@Override
	public String toString() {
		return "Matricula [id=" + id + "]";
	}
	
	
	
	
	
}

package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estudiante {
	@Id
	int nmro_libreta;
	@Column
	String nombre;
	@Column
	String apellido;
	@Column
	int edad;
	@Column
	String genero;
	@Column
	int dni;
	@Column
	String ciudad_residencia;
	@OneToMany(mappedBy = "estudiante")
	@JsonIgnore
	List<Matricula> carreras_inscriptas;
	
	public Estudiante() {
		super();
	}
	public Estudiante(int nmro_libreta, String nombre, String apellido, int edad, String genero, int dni,
			String ciudad_residencia) {
		super();
		this.nmro_libreta = nmro_libreta;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad_residencia = ciudad_residencia;
		this.carreras_inscriptas = new ArrayList<Matricula>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getCiudad_residencia() {
		return ciudad_residencia;
	}
	public void setCiudad_residencia(String ciudad_residencia) {
		this.ciudad_residencia = ciudad_residencia;
	}
	public int getNmro_libreta() {
		return nmro_libreta;
	}
	public List<Matricula> getCarreras_inscriptas() {
		return carreras_inscriptas;
	}
	
	public void addMatricula(Matricula matricula) {
		if(!carreras_inscriptas.contains(matricula)) {
			carreras_inscriptas.add(matricula);			
		}
	}
	public void removeMatricula(Matricula matricula) {
		if(carreras_inscriptas.contains(matricula)) {
			carreras_inscriptas.remove(matricula);
		}
	}
	@Override
	public String toString() {
		return "Estudiante [nmro_libreta=" + nmro_libreta + ", nombre=" + nombre + ", apellido=" + apellido + ", edad="
				+ edad + ", genero=" + genero + ", dni=" + dni + ", ciudad_residencia=" + ciudad_residencia
				+ "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return Objects.equals(apellido, other.apellido) && dni == other.dni;
	}
	
	
	
	
	
	
}

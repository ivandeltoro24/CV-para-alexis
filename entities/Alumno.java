/**
 * 
 */
package api.practica.cv.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * 
 */
@Entity
@Table(name = "alumnos", schema = "cv", uniqueConstraints = {
		@UniqueConstraint(name = "uk_alumnos_dni", columnNames = "dni")
})
public class Alumno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAlumno")
	private Long idAlumno;

	@Column(name = "dni", nullable = false, length = 9, unique = true)
	private String dni;
	
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	@Column(name = "apellidos", nullable = false, length = 30)
	private String apellidos;
	
	@Column(name = "fechaNacimiento", nullable = false)
	private LocalDate fechaNacimiento;
	
	@Column(name = "numeroSs", nullable = false, length = 12)
	private String numeroSs;
	
	@OneToMany(mappedBy = "alumno", cascade = CascadeType.REMOVE)
	private List<Curriculum> curriculum;

	public Alumno() {
		super();
	}

	public Alumno(Long idAlumno, String dni, String nombre, String apellidos, LocalDate fechaNacimiento,
			String numeroSs, List<Curriculum> curriculum) {
		super();
		this.idAlumno = idAlumno;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.numeroSs = numeroSs;
		this.curriculum = curriculum;
	}

	public Long getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Long idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNumeroSs() {
		return numeroSs;
	}

	public void setNumeroSs(String numeroSs) {
		this.numeroSs = numeroSs;
	}

	public List<Curriculum> getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(List<Curriculum> curriculum) {
		this.curriculum = curriculum;
	}

	
}

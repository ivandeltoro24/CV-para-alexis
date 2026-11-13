/**
 * 
 */
package api.practica.cv.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * 
 */
@Entity
@Table(name = "curriculum", schema = "cv", uniqueConstraints = {
		@UniqueConstraint(name = "uk_curriculum_codigo", columnNames = "codigo")
})
public class Curriculum {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCurriculum")
	private Long idCurriculum;
	
	@Column(name = "codigo", nullable = false, length = 9, unique = true)
	private String codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_alumno", nullable = false, foreignKey = @ForeignKey(name = "fk_alumno"))
	private Alumno alumno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_experiencia", foreignKey = @ForeignKey(name = "fk_experiencia"))
	private Experiencia experiencia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_aptitudes", foreignKey = @ForeignKey(name = "fk_aptitud"))
	private Aptitud aptitudes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_formacion", foreignKey = @ForeignKey(name = "fk_formacion"))
	private Formacion formacion;

	public Curriculum() {
		super();
	}

	public Curriculum(Long idCurriculum, String codigo, Alumno alumno, Experiencia experiencia, Aptitud aptitudes) {
		super();
		this.idCurriculum = idCurriculum;
		this.codigo = codigo;
		this.alumno = alumno;
		this.experiencia = experiencia;
		this.aptitudes = aptitudes;
	}

	public Long getIdCurriculum() {
		return idCurriculum;
	}

	public void setIdCurriculum(Long idCurriculum) {
		this.idCurriculum = idCurriculum;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Experiencia getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Experiencia experiencia) {
		this.experiencia = experiencia;
	}

	public Aptitud getAptitudes() {
		return aptitudes;
	}

	public void setAptitudes(Aptitud aptitudes) {
		this.aptitudes = aptitudes;
	}
	

	
}

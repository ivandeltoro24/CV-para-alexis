/**
 * 
 */
package api.practica.cv.entities;

import java.util.List;

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
@Table(name = "formaciones", schema = "cv", uniqueConstraints = {
		@UniqueConstraint(name = "uk_formaciones_codigo", columnNames = "codigo") })
public class Formacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFormacion")
	private Long idFormacion;

	@Column(name = "codigo", nullable = false, length = 9, unique = true)
	private String codigo;

	@Column(name = "empresa", nullable = false, length = 20)
	private String empresa;

	@Column(name = "detalles", nullable = false, length = 30)
	private String detalles;

	@Column(name = "esReglada", nullable = false)
	private Boolean esReglada;

	@OneToMany(mappedBy = "curriculum")
	private List<Curriculum> curriculum;

	public Formacion() {
		super();
	}

	public Formacion(Long idFormacion, String codigo, String empresa, String detalles, Boolean esReglada,
			List<Curriculum> curriculum) {
		super();
		this.idFormacion = idFormacion;
		this.codigo = codigo;
		this.empresa = empresa;
		this.detalles = detalles;
		this.esReglada = esReglada;
		this.curriculum = curriculum;
	}

	public Long getIdFormacion() {
		return idFormacion;
	}

	public void setIdFormacion(Long idFormacion) {
		this.idFormacion = idFormacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Boolean getEsReglada() {
		return esReglada;
	}

	public void setEsReglada(Boolean esReglada) {
		this.esReglada = esReglada;
	}

	public List<Curriculum> getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(List<Curriculum> curriculum) {
		this.curriculum = curriculum;
	}

	
}

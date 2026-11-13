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
@Table(name = "experiencias", schema = "cv", uniqueConstraints = {
		@UniqueConstraint(name = "uk_experiencia_codigo", columnNames = "codigo")
})
public class Experiencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idExperiencia")
	private Long idExperiencia;

	@Column(name = "codigo", nullable = false, length = 9, unique = true)
	private String codigo;
	
	@Column(name = "empresa", nullable = false, length = 20)
	private String empresa;
	
	@Column(name = "detalles", nullable = false, length = 30)
	private String detalles;
	
	@OneToMany(mappedBy = "curriculum")
	private List<Curriculum> curriculum;

	public Experiencia() {
		super();
	}

	public Experiencia(Long idExperiencia, String codigo, String empresa, String detalles,
			List<Curriculum> curriculum) {
		super();
		this.idExperiencia = idExperiencia;
		this.codigo = codigo;
		this.empresa = empresa;
		this.detalles = detalles;
		this.curriculum = curriculum;
	}

	public Long getIdExperiencia() {
		return idExperiencia;
	}

	public void setIdExperiencia(Long idExperiencia) {
		this.idExperiencia = idExperiencia;
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

	public List<Curriculum> getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(List<Curriculum> curriculum) {
		this.curriculum = curriculum;
	}

		
}

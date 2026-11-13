/**
 * 
 */
package api.practica.cv.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import api.practica.cv.dtos.ExperienciaRequestDto;
import api.practica.cv.dtos.ExperienciaResponseDto;
import api.practica.cv.entities.Experiencia;
import api.practica.cv.exceptions.DniAlreadyExistsException;
import api.practica.cv.exceptions.EntityNotFoundException;
import api.practica.cv.repositories.ExperienciaRepository;

/**
 * 
 */
public class ExperienciaService {

	private final ExperienciaRepository experienciaRepository;

	// Inyección de dependencias por constructor
	public ExperienciaService(ExperienciaRepository experienciaRepository) {
		this.experienciaRepository = experienciaRepository;
	}
	
	@Transactional
	public ExperienciaResponseDto crear(ExperienciaRequestDto dto) {
		// Validación de regla de negocio: codigo único
		if (experienciaRepository.existsByCodigo(dto.codigo())) {
			throw new DniAlreadyExistsException("Ya existe un experiencia registrado con el DNI: " + dto.codigo());
		}

		Experiencia nuevoExperiencia = new Experiencia(
				dto.idExperiencia(),
				dto.codigo(),
				dto.empresa(),
				dto.detalles(),
				dto.curriculum()
		);

		Experiencia guardado = experienciaRepository.save(nuevoExperiencia);
		return mapToResponseDto(guardado);
	}
	
	@Transactional
	public ExperienciaResponseDto actualizar(String codigo, ExperienciaRequestDto dto) {
		Experiencia experienciaExistente = experienciaRepository.findByCodigo(codigo)
				.orElseThrow(() -> new EntityNotFoundException("Experiencia no encontrado con el codigo: " + codigo));

		// Si se modifica el codigo, comprobar que el nuevo codigo no pertenezca a otro experiencia
		if (!experienciaExistente.getCodigo().equalsIgnoreCase(dto.codigo()) && experienciaRepository.existsByCodigo(dto.codigo())) {
			throw new DniAlreadyExistsException("El codigo " + dto.codigo() + " ya está en uso por otro experiencia.");
		}

		experienciaExistente.setIdExperiencia(dto.idExperiencia());
		experienciaExistente.setCodigo(dto.codigo());
		experienciaExistente.setDetalles(dto.detalles());
		experienciaExistente.setEmpresa(dto.empresa());

		Experiencia actualizado = experienciaRepository.save(experienciaExistente);
		return mapToResponseDto(actualizado);
	}
	
	@Transactional
	public void eliminar(String codigo) {
		if (!experienciaRepository.existsByCodigo(codigo)) {
			throw new EntityNotFoundException("Imposible eliminar. Experiencia no encontrado con el codigo: " + codigo);
		}
		experienciaRepository.deleteByCodigo(codigo);
	}
	
	@Transactional(readOnly = true)
	public ExperienciaResponseDto obtenerPorCodigo(String codigo) {
		Experiencia experiencia = experienciaRepository.findByCodigo(codigo)
				.orElseThrow(() -> new EntityNotFoundException("Experiencia no encontrado con el ID: " + codigo));
		return mapToResponseDto(experiencia);
	}
	
	@Transactional(readOnly = true)
	public List<ExperienciaResponseDto> obtenerTodos() {
		return experienciaRepository.findAll().stream().map(this::mapToResponseDto).toList();
	}
	
	// Método auxiliar de mapeo de Entidad a DTO
	private ExperienciaResponseDto mapToResponseDto(Experiencia experiencia) {
		return new ExperienciaResponseDto(
				experiencia.getIdExperiencia(),
				experiencia.getCodigo(),
				experiencia.getEmpresa(),
				experiencia.getDetalles(),
				experiencia.getCurriculum()
		);
	}
}

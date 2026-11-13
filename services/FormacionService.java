/**
 * 
 */
package api.practica.cv.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import api.practica.cv.dtos.FormacionRequestDto;
import api.practica.cv.dtos.FormacionResponseDto;
import api.practica.cv.entities.Formacion;
import api.practica.cv.exceptions.DniAlreadyExistsException;
import api.practica.cv.exceptions.EntityNotFoundException;
import api.practica.cv.repositories.FormacionRepository;

/**
 * 
 */
public class FormacionService {

	private final FormacionRepository formacionRepository;

	// Inyección de dependencias por constructor
	public FormacionService(FormacionRepository formacionRepository) {
		this.formacionRepository = formacionRepository;
	}
	
	@Transactional
	public FormacionResponseDto crear(FormacionRequestDto dto) {
		// Validación de regla de negocio: DNI único
		if (formacionRepository.existsByCodigo(dto.codigo())) {
			throw new DniAlreadyExistsException("Ya existe un formacion registrado con el codigo: " + dto.codigo());
		}

		Formacion nuevoFormacion = new Formacion(
				dto.idFormacion(),
				dto.codigo(),
				dto.empresa(),
				dto.detalles(),
				dto.esReglada(),
				dto.curriculum()
		);

		Formacion guardado = formacionRepository.save(nuevoFormacion);
		return mapToResponseDto(guardado);
	}
	
	@Transactional
	public FormacionResponseDto actualizar(String codigo, FormacionRequestDto dto) {
		Formacion formacionExistente = formacionRepository.findByCodigo(codigo)
				.orElseThrow(() -> new EntityNotFoundException("Formacion no encontrado con el codigo: " + codigo));

		// Si se modifica el DNI, comprobar que el nuevo codigo no pertenezca a otro formacion
		if (!formacionExistente.getCodigo().equalsIgnoreCase(dto.codigo()) && formacionRepository.existsByCodigo(dto.codigo())) {
			throw new DniAlreadyExistsException("El codigo " + dto.codigo() + " ya está en uso por otro formacion.");
		}

		formacionExistente.setCodigo(dto.codigo());
		

		Formacion actualizado = formacionRepository.save(formacionExistente);
		return mapToResponseDto(actualizado);
	}
	
	@Transactional
	public void eliminar(String codigo) {
		if (!formacionRepository.existsByCodigo(codigo)) {
			throw new EntityNotFoundException("Imposible eliminar. Formacion no encontrado con el codigo: " + codigo);
		}
		formacionRepository.deleteByCodigo(codigo);
	}
	
	@Transactional(readOnly = true)
	public FormacionResponseDto obtenerPorCodigo(String codigo) {
		Formacion formacion = formacionRepository.findByCodigo(codigo)
				.orElseThrow(() -> new EntityNotFoundException("Formacion no encontrado con el codigo: " + codigo));
		return mapToResponseDto(formacion);
	}
	
	@Transactional(readOnly = true)
	public List<FormacionResponseDto> obtenerTodos() {
		return formacionRepository.findAll().stream().map(this::mapToResponseDto).toList();
	}
	
	// Método auxiliar de mapeo de Entidad a DTO
	private FormacionResponseDto mapToResponseDto(Formacion formacion) {
		return new FormacionResponseDto(
				formacion.getIdFormacion(),
				formacion.getCodigo(),
				formacion.getEmpresa(),
				formacion.getDetalles(),
				formacion.getEsReglada(),
				formacion.getCurriculum()
		);
	}
}

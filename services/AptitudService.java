/**
 * 
 */
package api.practica.cv.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import api.practica.cv.dtos.AptitudRequestDto;
import api.practica.cv.dtos.AptitudResponseDto;
import api.practica.cv.entities.Aptitud;
import api.practica.cv.exceptions.EntityNotFoundException;
import api.practica.cv.exceptions.DniAlreadyExistsException;
import api.practica.cv.repositories.AptitudRepository;

/**
 * 
 */
public class AptitudService {

	private final AptitudRepository aptitudesRepository;

	// Inyección de dependencias por constructor
	public AptitudService(AptitudRepository aptitudesRepository) {
		this.aptitudesRepository = aptitudesRepository;
	}
	
	@Transactional
	public AptitudResponseDto crear(AptitudRequestDto dto) {
		// Validación de regla de negocio: DNI único
		if (aptitudesRepository.existsByCodigo(dto.codigo())) {
			throw new DniAlreadyExistsException("Ya existe un aptitudes registrado con el DNI: " + dto.codigo());
		}

		Aptitud nuevoAptitudes = new Aptitud(
				dto.idAptitudes(),
				dto.codigo(),
				dto.empresa(),
				dto.detalles(),
				dto.curriculum()
		);

		Aptitud guardado = aptitudesRepository.save(nuevoAptitudes);
		return mapToResponseDto(guardado);
	}
	
	@Transactional
	public AptitudResponseDto actualizar(String codigo, AptitudRequestDto dto) {
		Aptitud aptitudesExistente = aptitudesRepository.findByCodigo(codigo)
				.orElseThrow(() -> new EntityNotFoundException("Aptitud no encontrada con el codigo: " + codigo));

		// Si se modifica el DNI, comprobar que el nuevo DNI no pertenezca a otro aptitudes
		if (!aptitudesExistente.getCodigo().equalsIgnoreCase(dto.codigo()) && aptitudesRepository.existsByCodigo(dto.codigo())) {
			throw new DniAlreadyExistsException("El DNI " + dto.codigo() + " ya está en uso por otro aptitudes.");
		}

		aptitudesExistente.setCodigo(dto.codigo());

		Aptitud actualizado = aptitudesRepository.save(aptitudesExistente);
		return mapToResponseDto(actualizado);
	}
	
	@Transactional
	public void eliminar(String codigo) {
		if (!aptitudesRepository.existsByCodigo(codigo)) {
			throw new EntityNotFoundException("Imposible eliminar. Aptitud no encontrada con el codigo: " + codigo);
		}
		aptitudesRepository.deleteByCodigo(codigo);
	}
	
	@Transactional(readOnly = true)
	public AptitudResponseDto obtenerPorCodigo(String codigo) {
		Aptitud aptitudes = aptitudesRepository.findByCodigo(codigo)
				.orElseThrow(() -> new EntityNotFoundException("Aptitud no encontrada con el codigo: " + codigo));
		return mapToResponseDto(aptitudes);
	}
	
	@Transactional(readOnly = true)
	public List<AptitudResponseDto> obtenerTodos() {
		return aptitudesRepository.findAll().stream().map(this::mapToResponseDto).toList();
	}
	
	// Método auxiliar de mapeo de Entidad a DTO
	private AptitudResponseDto mapToResponseDto(Aptitud aptitudes) {
		return new AptitudResponseDto(
				aptitudes.getIdAptitudes(),
				aptitudes.getCodigo(),
				aptitudes.getEmpresa(),
				aptitudes.getDetalles(),
				aptitudes.getCurriculum()
		);
	}
}

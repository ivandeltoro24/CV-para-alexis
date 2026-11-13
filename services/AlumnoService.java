/**
 * 
 */
package api.practica.cv.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import api.practica.cv.dtos.AlumnoRequestDto;
import api.practica.cv.dtos.AlumnoResponseDto;
import api.practica.cv.entities.Alumno;
import api.practica.cv.exceptions.DniAlreadyExistsException;
import api.practica.cv.exceptions.EntityNotFoundException;
import api.practica.cv.repositories.AlumnoRepository;

/**
 * 
 */
public class AlumnoService {

	private final AlumnoRepository alumnoRepository;

	// Inyección de dependencias por constructor
	public AlumnoService(AlumnoRepository alumnoRepository) {
		this.alumnoRepository = alumnoRepository;
	}
	
	@Transactional
	public AlumnoResponseDto crear(AlumnoRequestDto dto) {
		// Validación de regla de negocio: DNI único
		if (alumnoRepository.existsByDni(dto.dni())) {
			throw new DniAlreadyExistsException("Ya existe un alumno registrado con el DNI: " + dto.dni());
		}

		Alumno nuevoAlumno = new Alumno(
				dto.idAlumno(),
				dto.dni(),
				dto.nombre(),
				dto.apellidos(),
				dto.fechaNacimiento(),
				dto.numeroSs(),
				dto.curriculum()
		);

		Alumno guardado = alumnoRepository.save(nuevoAlumno);
		return mapToResponseDto(guardado);
	}
	
	@Transactional
	public AlumnoResponseDto actualizar(String dni, AlumnoRequestDto dto) {
		Alumno alumnoExistente = alumnoRepository.findByDni(dni)
				.orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con el ID: " + dni));

		// Si se modifica el DNI, comprobar que el nuevo DNI no pertenezca a otro alumno
		if (!alumnoExistente.getDni().equalsIgnoreCase(dto.dni()) && alumnoRepository.existsByDni(dto.dni())) {
			throw new DniAlreadyExistsException("El DNI " + dto.dni() + " ya está en uso por otro alumno.");
		}

		alumnoExistente.setDni(dto.dni());
		alumnoExistente.setNombre(dto.nombre());
		alumnoExistente.setApellidos(dto.apellidos());
		alumnoExistente.setFechaNacimiento(dto.fechaNacimiento());
		alumnoExistente.setNumeroSs(dto.numeroSs());

		Alumno actualizado = alumnoRepository.save(alumnoExistente);
		return mapToResponseDto(actualizado);
	}
	
	@Transactional
	public void eliminar(String dni) {
		if (!alumnoRepository.existsByDni(dni)) {
			throw new EntityNotFoundException("Imposible eliminar. Alumno no encontrado con el ID: " + dni);
		}
		alumnoRepository.deleteByDni(dni);
	}
	
	@Transactional(readOnly = true)
	public AlumnoResponseDto obtenerPorDni(String dni) {
		Alumno alumno = alumnoRepository.findByDni(dni)
				.orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con el ID: " + dni));
		return mapToResponseDto(alumno);
	}
	
	@Transactional(readOnly = true)
	public List<AlumnoResponseDto> obtenerTodos() {
		return alumnoRepository.findAll().stream().map(this::mapToResponseDto).toList();
	}
	
	// Método auxiliar de mapeo de Entidad a DTO
	private AlumnoResponseDto mapToResponseDto(Alumno alumno) {
		return new AlumnoResponseDto(
				alumno.getIdAlumno(),
				alumno.getDni(),
				alumno.getNombre(),
				alumno.getApellidos(),
				alumno.getFechaNacimiento(),
				alumno.getNumeroSs(),
				alumno.getCurriculum()
		);
	}
}

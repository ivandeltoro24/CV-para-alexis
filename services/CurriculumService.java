package api.practica.cv.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import api.practica.cv.dtos.CurriculumRequestDto;
import api.practica.cv.dtos.CurriculumResponseDto;
import api.practica.cv.entities.Curriculum;
import api.practica.cv.exceptions.CodeAlreadyExistsException;
import api.practica.cv.exceptions.EntityNotFoundException;
import api.practica.cv.repositories.CurriculumRepository;

public class CurriculumService {

	private final CurriculumRepository curriculumRepository;

	public CurriculumService(CurriculumRepository curriculumRepository) {
		this.curriculumRepository = curriculumRepository;
	}

	@Transactional
	public CurriculumResponseDto crear(CurriculumRequestDto dto) {
		if (curriculumRepository.existsByCodigo(dto.codigo())) {
			throw new CodeAlreadyExistsException(
					"Ya existe un currículum registrado con el código: " + dto.codigo());
		}

		Curriculum nuevoCurriculum = new Curriculum(dto.idCurriculum(), dto.codigo(), dto.alumno(), dto.experiencia(),
				dto.aptitudes());

		return mapToResponseDto(curriculumRepository.save(nuevoCurriculum));
	}

	@Transactional
	public CurriculumResponseDto actualizar(String codigo, CurriculumRequestDto dto) {
		Curriculum curriculumExistente = curriculumRepository.findByCodigo(codigo).orElseThrow(
				() -> new EntityNotFoundException("No se encontró un currículum con el código: " + codigo));

		if (!curriculumExistente.getCodigo().equalsIgnoreCase(dto.codigo())
				&& curriculumRepository.existsByCodigo(dto.codigo())) {
			throw new CodeAlreadyExistsException(
					"El código " + dto.codigo() + " ya está en uso por otro currículum.");
		}

		curriculumExistente.setIdCurriculum(dto.idCurriculum());
		curriculumExistente.setCodigo(dto.codigo());
		curriculumExistente.setAlumno(dto.alumno());
		curriculumExistente.setExperiencia(dto.experiencia());
		curriculumExistente.setAptitudes(dto.aptitudes());

		return mapToResponseDto(curriculumRepository.save(curriculumExistente));
	}

	@Transactional
	public void eliminar(String codigo) {
		if (!curriculumRepository.existsByCodigo(codigo)) {
			throw new EntityNotFoundException(
					"No se puede eliminar: no se encontró un currículum con el código: " + codigo);
		}
		curriculumRepository.deleteByCodigo(codigo);
	}

	@Transactional(readOnly = true)
	public CurriculumResponseDto obtenerPorCodigo(String codigo) {
		Curriculum curriculum = curriculumRepository.findByCodigo(codigo).orElseThrow(
				() -> new EntityNotFoundException("No se encontró un currículum con el código: " + codigo));
		return mapToResponseDto(curriculum);
	}

	@Transactional(readOnly = true)
	public List<CurriculumResponseDto> obtenerTodos() {
		return curriculumRepository.findAll().stream().map(this::mapToResponseDto).toList();
	}

	private CurriculumResponseDto mapToResponseDto(Curriculum curriculum) {
		return new CurriculumResponseDto(curriculum.getIdCurriculum(), curriculum.getCodigo(), curriculum.getAlumno(),
				curriculum.getExperiencia(), curriculum.getAptitudes());
	}
}

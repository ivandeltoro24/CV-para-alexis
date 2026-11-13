/**
 * 
 */
package api.practica.cv.dtos;

import api.practica.cv.entities.Alumno;
import api.practica.cv.entities.Aptitud;
import api.practica.cv.entities.Experiencia;

/**
 * 
 */
public record CurriculumResponseDto(
		Long idCurriculum,
		String codigo,
		Alumno alumno,
		Experiencia experiencia,
		Aptitud aptitudes) {

}

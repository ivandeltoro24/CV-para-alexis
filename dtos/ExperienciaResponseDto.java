/**
 * 
 */
package api.practica.cv.dtos;

import java.util.List;

import api.practica.cv.entities.Curriculum;

/**
 * 
 */
public record ExperienciaResponseDto(
		Long idExperiencia,
		String codigo,
		String empresa,
		String detalles,
		List<Curriculum> curriculum) {

}

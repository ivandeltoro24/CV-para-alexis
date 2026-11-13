/**
 * 
 */
package api.practica.cv.dtos;

import java.util.List;

import api.practica.cv.entities.Curriculum;

/**
 * 
 */
public record FormacionRequestDto(
		Long idFormacion,
		String codigo,
		String empresa,
		String detalles,
		Boolean esReglada,
		List<Curriculum> curriculum) {

}

/**
 * 
 */
package api.practica.cv.dtos;

import java.util.List;

import api.practica.cv.entities.Curriculum;

/**
 * 
 */
public record FormacionResponseDto(
		Long idFormacion,
		String codigo,
		String empresa,
		String detalles,
		Boolean esReglada,
		List<Curriculum> curriculum) {

}

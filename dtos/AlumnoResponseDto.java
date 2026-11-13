/**
 * 
 */
package api.practica.cv.dtos;

import java.time.LocalDate;
import java.util.List;

import api.practica.cv.entities.Curriculum;

/**
 * 
 */
public record AlumnoResponseDto(
		Long idAlumno,
		String dni,
		String nombre,
		String apellidos,
		LocalDate fechaNacimiento,
		String numeroSs,
		List<Curriculum> curriculum) {

}

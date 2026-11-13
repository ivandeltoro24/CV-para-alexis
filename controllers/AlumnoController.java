package api.practica.cv.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.practica.cv.dtos.AlumnoRequestDto;
import api.practica.cv.dtos.AlumnoResponseDto;
import api.practica.cv.services.AlumnoService;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

	private final AlumnoService alumnoService;

	public AlumnoController(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

	// CREATE - POST /api/alumnos
	@PostMapping
	public ResponseEntity<AlumnoResponseDto> crear(@RequestBody AlumnoRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.crear(dto));
	}

	// UPDATE - PUT /api/alumnos/{dni}
	@PutMapping("/{dni}")
	public ResponseEntity<AlumnoResponseDto> actualizar(@PathVariable String dni,
			@RequestBody AlumnoRequestDto requestDto) {
		AlumnoResponseDto actualizado = alumnoService.actualizar(dni, requestDto);
		return ResponseEntity.ok(actualizado); // HTTP 200 OK
	}

	// READ ALL - GET /api/alumnos
	@GetMapping
	public ResponseEntity<List<AlumnoResponseDto>> obtenerTodos() {
		return ResponseEntity.ok(alumnoService.obtenerTodos());
	}

	// READ BY ID - GET /api/alumnos/{dni}
	@GetMapping("/{dni}")
	public ResponseEntity<AlumnoResponseDto> obtenerPorId(@PathVariable String dni) {
		return ResponseEntity.ok(alumnoService.obtenerPorDni(dni));
	}

	// DELETE - DELETE /api/alumnos/{dni}
	@DeleteMapping("/{dni}")
	public ResponseEntity<Void> eliminar(@PathVariable String dni) {
		alumnoService.eliminar(dni);
		return ResponseEntity.noContent().build();
	}
}

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

import api.practica.cv.dtos.ExperienciaRequestDto;
import api.practica.cv.dtos.ExperienciaResponseDto;
import api.practica.cv.services.ExperienciaService;

@RestController
@RequestMapping("/api/experiencias")
public class ExperienciaController {

	private final ExperienciaService experienciaService;

	public ExperienciaController(ExperienciaService experienciaService) {
		this.experienciaService = experienciaService;
	}

	// CREATE - POST /api/experiencias
	@PostMapping
	public ResponseEntity<ExperienciaResponseDto> crear(@RequestBody ExperienciaRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(experienciaService.crear(dto));
	}

	// UPDATE - PUT /api/experiencias/{codigo}
	@PutMapping("/{codigo}")
	public ResponseEntity<ExperienciaResponseDto> actualizar(@PathVariable String codigo,
			@RequestBody ExperienciaRequestDto requestDto) {
		ExperienciaResponseDto actualizado = experienciaService.actualizar(codigo, requestDto);
		return ResponseEntity.ok(actualizado); // HTTP 200 OK
	}

	// READ ALL - GET /api/experiencias
	@GetMapping
	public ResponseEntity<List<ExperienciaResponseDto>> obtenerTodos() {
		return ResponseEntity.ok(experienciaService.obtenerTodos());
	}

	// READ BY ID - GET /api/experiencias/{codigo}
	@GetMapping("/{codigo}")
	public ResponseEntity<ExperienciaResponseDto> obtenerPorId(@PathVariable String codigo) {
		return ResponseEntity.ok(experienciaService.obtenerPorCodigo(codigo));
	}

	// DELETE - DELETE /api/experiencias/{codigo}
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
		experienciaService.eliminar(codigo);
		return ResponseEntity.noContent().build();
	}
}

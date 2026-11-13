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

import api.practica.cv.dtos.CurriculumRequestDto;
import api.practica.cv.dtos.CurriculumResponseDto;
import api.practica.cv.services.CurriculumService;

@RestController
@RequestMapping("/api/curriculums")
public class CurriculumController {

	private final CurriculumService curriculumService;

	public CurriculumController(CurriculumService curriculumService) {
		this.curriculumService = curriculumService;
	}

	// CREATE - POST /api/curriculums
	@PostMapping
	public ResponseEntity<CurriculumResponseDto> crear(@RequestBody CurriculumRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(curriculumService.crear(dto));
	}

	// UPDATE - PUT /api/curriculums/{codigo}
	@PutMapping("/{codigo}")
	public ResponseEntity<CurriculumResponseDto> actualizar(@PathVariable String codigo,
			@RequestBody CurriculumRequestDto requestDto) {
		CurriculumResponseDto actualizado = curriculumService.actualizar(codigo, requestDto);
		return ResponseEntity.ok(actualizado); // HTTP 200 OK
	}

	// READ ALL - GET /api/curriculums
	@GetMapping
	public ResponseEntity<List<CurriculumResponseDto>> obtenerTodos() {
		return ResponseEntity.ok(curriculumService.obtenerTodos());
	}

	// READ BY ID - GET /api/curriculums/{codigo}
	@GetMapping("/{codigo}")
	public ResponseEntity<CurriculumResponseDto> obtenerPorId(@PathVariable String codigo) {
		return ResponseEntity.ok(curriculumService.obtenerPorCodigo(codigo));
	}

	// DELETE - DELETE /api/curriculums/{codigo}
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
		curriculumService.eliminar(codigo);
		return ResponseEntity.noContent().build();
	}
}

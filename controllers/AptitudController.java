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

import api.practica.cv.dtos.AptitudRequestDto;
import api.practica.cv.dtos.AptitudResponseDto;
import api.practica.cv.services.AptitudService;

@RestController
@RequestMapping("/api/aptituds")
public class AptitudController {

	private final AptitudService aptitudService;

	public AptitudController(AptitudService aptitudService) {
		this.aptitudService = aptitudService;
	}

	// CREATE - POST /api/aptituds
	@PostMapping
	public ResponseEntity<AptitudResponseDto> crear(@RequestBody AptitudRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(aptitudService.crear(dto));
	}

	// UPDATE - PUT /api/aptituds/{codigo}
	@PutMapping("/{codigo}")
	public ResponseEntity<AptitudResponseDto> actualizar(@PathVariable String codigo,
			@RequestBody AptitudRequestDto requestDto) {
		AptitudResponseDto actualizado = aptitudService.actualizar(codigo, requestDto);
		return ResponseEntity.ok(actualizado); // HTTP 200 OK
	}

	// READ ALL - GET /api/aptituds
	@GetMapping
	public ResponseEntity<List<AptitudResponseDto>> obtenerTodos() {
		return ResponseEntity.ok(aptitudService.obtenerTodos());
	}

	// READ BY ID - GET /api/aptituds/{codigo}
	@GetMapping("/{codigo}")
	public ResponseEntity<AptitudResponseDto> obtenerPorId(@PathVariable String codigo) {
		return ResponseEntity.ok(aptitudService.obtenerPorCodigo(codigo));
	}

	// DELETE - DELETE /api/aptituds/{codigo}
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
		aptitudService.eliminar(codigo);
		return ResponseEntity.noContent().build();
	}
}

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

import api.practica.cv.dtos.FormacionRequestDto;
import api.practica.cv.dtos.FormacionResponseDto;
import api.practica.cv.services.FormacionService;

@RestController
@RequestMapping("/api/formacions")
public class FormacionController {

	private final FormacionService formacionService;

	public FormacionController(FormacionService formacionService) {
		this.formacionService = formacionService;
	}

	// CREATE - POST /api/formacions
	@PostMapping
	public ResponseEntity<FormacionResponseDto> crear(@RequestBody FormacionRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(formacionService.crear(dto));
	}

	// UPDATE - PUT /api/formacions/{codigo}
	@PutMapping("/{codigo}")
	public ResponseEntity<FormacionResponseDto> actualizar(@PathVariable String codigo,
			@RequestBody FormacionRequestDto requestDto) {
		FormacionResponseDto actualizado = formacionService.actualizar(codigo, requestDto);
		return ResponseEntity.ok(actualizado); // HTTP 200 OK
	}

	// READ ALL - GET /api/formacions
	@GetMapping
	public ResponseEntity<List<FormacionResponseDto>> obtenerTodos() {
		return ResponseEntity.ok(formacionService.obtenerTodos());
	}

	// READ BY ID - GET /api/formacions/{codigo}
	@GetMapping("/{codigo}")
	public ResponseEntity<FormacionResponseDto> obtenerPorId(@PathVariable String codigo) {
		return ResponseEntity.ok(formacionService.obtenerPorCodigo(codigo));
	}

	// DELETE - DELETE /api/formacions/{codigo}
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
		formacionService.eliminar(codigo);
		return ResponseEntity.noContent().build();
	}
}

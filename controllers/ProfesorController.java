package api.practica.cv.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import api.practica.cv.dtos.CurriculumResponseDto;
import api.practica.cv.services.CurriculumService;

public class ProfesorController {

	private final CurriculumService curriculumService;

	public ProfesorController(CurriculumService curriculumService) {
		this.curriculumService = curriculumService;
	}

// READ ALL - GET /api/curriculum
	@GetMapping
	public ResponseEntity<List<CurriculumResponseDto>> obtenerTodos() {
		return ResponseEntity.ok(curriculumService.obtenerTodos());
	}
	
	// READ BY ID - GET /api/alumnos/{codigo}
	@GetMapping("/{codigo}")
	public ResponseEntity<CurriculumResponseDto> obtenerPorId(@PathVariable String codigo) {
		return ResponseEntity.ok(curriculumService.obtenerPorCodigo(codigo));
	}
}

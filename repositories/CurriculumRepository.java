/**
 * 
 */
package api.practica.cv.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.practica.cv.entities.Curriculum;

/**
 * 
 */
@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

	// --- CREATE / VALIDACIÓN ---
	// Comprobar si existe por la clave única antes de crear
	boolean existsByCodigo(String codigo);

	// --- READ ---
	// Buscar por la clave única (retorna Optional en vez de List al ser única)
	Optional<Curriculum> findByCodigo(String codigo);

	// --- DELETE ---
	// Eliminar por la clave única (retorna la cantidad de registros eliminados o void)
	void deleteByCodigo(String codigo);
}
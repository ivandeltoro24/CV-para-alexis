/**
 * 
 */
package api.practica.cv.exceptions;

/**
 * 
 */

public class DniAlreadyExistsException extends RuntimeException {
	public DniAlreadyExistsException(String mensaje) {
		super(mensaje);
	}
}

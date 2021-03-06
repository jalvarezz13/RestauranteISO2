package es_uclm_esi_isoft2_restaurante_reservaMesas_dominio;
/**
 * Clase Camarero, extiende de Persona
 *
 */

public class Camarero extends Persona {
/**
 * Constructor de camarero, tiene los atributos de persona
 * @param dni
 * @param nombre
 * @param apellidos
 * @param idRestaurante
 */
	public Camarero(String dni, String nombre, String apellidos, int idRestaurante) {
		super(dni, nombre, apellidos, idRestaurante);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Método toString para mostrar la información del camarero
	 */
	public String toString() {
		return "Camarero [getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getApellidos()=" + getApellidos()
				+ ", getIdRestaurante()=" + getIdRestaurante() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	

}
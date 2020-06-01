package frsf.isi.died.guia08.problema02.modelo;

import java.time.LocalDate;

public class Jugador {
	public enum Pierna{IZQUIERDA, DERECHA};
	public enum Posicion{ARQUERO, DEFENSOR, VOLANTE, DELANTERO};
	
	
	private String nombre;
	private Integer dni;
	private LocalDate fechaNac;
	private Posicion posicion;
	private Double altura;
	private Double peso;
	private Pierna piernaHabil;
	
	public Jugador() {}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	
}

package frsf.isi.died.guia08.problema02.modelo;

import java.util.List;

public class Equipo {

	private String nombre;
	private List<Jugador> plantel;
	private List<Partido> partidosJugados;
	
	public Equipo() {}
	
	public Equipo(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer puntos() {
		Integer suma;
		suma = this.partidosJugados.stream()
							.filter(part -> part.getLocal().getNombre() == this.getNombre()) //es el local
							.mapToInt(Partido::golesPuntosLocal)
							.sum();
		
		return suma + this.partidosJugados.stream()
							.filter(part -> part.getVisitante().getNombre() == this.getNombre()) //es el visitante
							.mapToInt(Partido::golesPuntosVisitante)
							.sum();
	}

	public Integer partidosGanados() {
		return (int) this.partidosJugados.stream()
										.filter(part -> part.getLocal().getNombre() == this.getNombre())
										.count();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

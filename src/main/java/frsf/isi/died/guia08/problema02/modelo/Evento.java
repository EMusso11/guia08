package frsf.isi.died.guia08.problema02.modelo;

public abstract class Evento {
		
	protected Equipo equipo;
	protected Jugador jugador;
	protected Integer minuto;
	
	public Evento() {}
	
	public Evento(Equipo equipo, Jugador jugador, Integer minuto) {
		this.equipo = equipo;
		this.jugador = jugador;
		this.minuto = minuto;
	}
	
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public Integer getMinuto() {
		return minuto;
	}
	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

}

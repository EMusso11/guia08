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
	
	public void addJugador(Jugador j) throws LimiteJugadoresException {
		if(plantel.size()>=21) {
			throw new LimiteJugadoresException();
		} else if(j.getPosicion() == frsf.isi.died.guia08.problema02.modelo.Jugador.Posicion.ARQUERO
				&& this.cantArqueros() >= 3) {
			throw new LimiteJugadoresException();
		} else if(j.getPosicion() == frsf.isi.died.guia08.problema02.modelo.Jugador.Posicion.DEFENSOR
				&& this.cantDefensores() >= 9) {
			throw new LimiteJugadoresException();
		} else if(j.getPosicion() == frsf.isi.died.guia08.problema02.modelo.Jugador.Posicion.VOLANTE
				&& this.cantVolantes() >= 9) {
			throw new LimiteJugadoresException();
		} else if(j.getPosicion() == frsf.isi.died.guia08.problema02.modelo.Jugador.Posicion.DELANTERO
				&& this.cantDelanteros() >= 6) {
			throw new LimiteJugadoresException();
		}
		this.plantel.add(j);
	}
	
	public Integer cantArqueros() {
		return (int) this.plantel.stream()
							.filter( j -> j.getPosicion()==frsf.isi.died.guia08.problema02.modelo.Jugador.Posicion.ARQUERO)
							.count();
	}
	
	public Integer cantDefensores() {
		return (int) this.plantel.stream()
							.filter( j -> j.getPosicion()==frsf.isi.died.guia08.problema02.modelo.Jugador.Posicion.DEFENSOR)
							.count();
	}
	
	public Integer cantVolantes() {
		return (int) this.plantel.stream()
							.filter( j -> j.getPosicion()==frsf.isi.died.guia08.problema02.modelo.Jugador.Posicion.	VOLANTE)
							.count();
	}
	
	public Integer cantDelanteros() {
		return (int) this.plantel.stream()
							.filter( j -> j.getPosicion()==frsf.isi.died.guia08.problema02.modelo.Jugador.Posicion.DELANTERO)
							.count();
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

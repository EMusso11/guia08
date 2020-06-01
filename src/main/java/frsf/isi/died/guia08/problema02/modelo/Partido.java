package frsf.isi.died.guia08.problema02.modelo;

import java.util.ArrayList;
import java.util.List;

public class Partido {

	private Equipo local;
	private Equipo visitante;
	private List<Evento> eventos;
	
	public Partido(Equipo local, Equipo visitante) {
		this.local = local;
		this.visitante = visitante;
	}


	public void addEvento(Evento e) {
		if(this.eventos == null) this.eventos = new ArrayList<Evento>();
		this.eventos.add(e);
	}
	
	
	public List<Evento> getEventos() {
		return eventos;
	}


	public Integer golesLocal() {
		return (int) this.getEventos().stream()
					.filter(e1 -> e1 instanceof EventoGol && e1.getEquipo().getNombre() == this.getLocal().getNombre()) // que sea gol del equipo local
					.map(e1 -> (EventoGol) e1)
					.count();
	}

	public Integer golesVisitante() {
		return (int) this.getEventos().stream()
				.filter(e1 -> e1 instanceof EventoGol && e1.getEquipo().getNombre() == this.getVisitante().getNombre()) // que sea gol del equipo visitante
				.map(e1 -> (EventoGol) e1)
				.count();
	}

	public Integer golesPuntosLocal() {
		if(this.golesLocal() > this.golesVisitante())
			return 3;
		else if(this.golesLocal() == this.golesVisitante())
			return 1;
		else
			return 0;
	}

	public Integer golesPuntosVisitante() {
		if(this.golesLocal() < this.golesVisitante())
			return 3;
		else if(this.golesLocal() == this.golesVisitante())
			return 1;
		else
			return 0;
	}

	public Equipo getLocal() {
		return local;
	}

	public void setLocal(Equipo local) {
		this.local = local;
	}

	public Equipo getVisitante() {
		return visitante;
	}

	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

}

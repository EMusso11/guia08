package frsf.isi.died.guia08.problema02.modelo;

public class EventoGol extends Evento {
	private Jugador jugadorMarco;
	private Jugador jugadorAsistio;
	private Boolean autoGol;
	
	public EventoGol() {
		super();
	}

	public EventoGol(Equipo e1, Integer minuto) {
		super(e1, null, minuto);
	}
}

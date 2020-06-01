package frsf.isi.died.guia08.problema02;

import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.guia08.exceptions.problema02.EquipoNoExisteException;
import frsf.isi.died.guia08.exceptions.problema02.LimiteEquiposException;
import frsf.isi.died.guia08.problema02.modelo.Equipo;
import frsf.isi.died.guia08.problema02.modelo.Fecha;
import frsf.isi.died.guia08.problema02.modelo.Partido;

public class LigaApp {

	private List<Fecha> calendario;
	private List<Equipo> equipos;
	public static void main(String[] args) {
		
	}
	
	public void agregarEquipo(Equipo e) throws LimiteEquiposException {
		if(equipos.size() >= 10) throw new LimiteEquiposException("La liga ya contiene 10 equipos.");
		if(equipos.isEmpty()) equipos = new ArrayList<Equipo>();
		equipos.add(e);
	}
	
	public void quitarEquipo(Equipo e) throws EquipoNoExisteException {
		if(!equipos.contains(e)) equipos.remove(e);
		else throw new EquipoNoExisteException("El equipo no existe en la liga.");
	}
	
	public void agregarFecha(List<Fecha> fechas) {
		if(calendario.isEmpty()) calendario = new ArrayList<Fecha>();
		fechas.stream()
				.forEach( f -> {
					f.setNumero(this.calendario.size()+1);
					calendario.add(f);
				});
	}
}

package frsf.isi.died.guia08.problema02.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EquipoTest {
	Equipo e1, e2;
	Partido p1;
	Evento gol;
	
	@Before
	public void setUp() {
		e1 = new Equipo("Barcelona");
		e2 = new Equipo("Chelsea");
		p1 = new Partido(e1, e2);
		gol = new EventoGol(e1, null, 15);
		p1.addEvento(gol); // gana 1-0
	}
	
	@Test
	public void puntos() {
		Integer resp = e1.puntos();
		System.out.println("Puntos : "+resp);
		assertSame(resp, 3);
	}

}

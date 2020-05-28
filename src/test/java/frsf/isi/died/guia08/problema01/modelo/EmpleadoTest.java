package frsf.isi.died.guia08.problema01.modelo;

import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class EmpleadoTest {

	Tarea t1, t2, t3, t4, t5, t6;
	Empleado e1, e2, e3;
	List<Tarea> tareas;
	
	@Before
	public void setUp() {
		t1 = new Tarea(LocalDateTime.now(), null, 1); //sin terminar
		t2 = new Tarea(LocalDateTime.parse("2020-01-01T00:00:00.000"), LocalDateTime.parse("2020-01-05T00:00:00.000"), 20); //estimadas 20 hs, terminada en 16
		t3 = new Tarea(LocalDateTime.parse("2020-01-01T00:00:00.000"), LocalDateTime.parse("2020-01-10T00:00:00.000"), 100);
		t4 = new Tarea(LocalDateTime.parse("2020-01-01T00:00:00.000"), LocalDateTime.parse("2020-01-02T00:00:00.000"), 100);
		t5 = new Tarea(LocalDateTime.parse("2020-01-01T00:00:00.000"), LocalDateTime.parse("2020-01-05T00:00:00.000"), 20);
		t6 = new Tarea(LocalDateTime.parse("2020-01-01T00:00:00.000"), LocalDateTime.parse("2020-01-05T00:00:00.000"), 20);
		e1 = new Empleado(frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo.CONTRATADO, 50.0);
		tareas = new ArrayList<Tarea>();
		
		tareas.add(t1);
		e2 = new Empleado(frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo.EFECTIVO, tareas, 50.0);
		tareas.add(t2);
		tareas.add(t3);
		tareas.add(t4);
		tareas.add(t5);
		e3 = new Empleado(frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo.CONTRATADO, tareas, 50.0);
	}
	
	@Test
	public void testSalario() {
		assertSame(e3.salario(), 440.0);
		
		assertSame(e2.salario(), 50.0);
	}

	@Test
	public void testCostoTareaConDuracionEstimada() {
		Double cost1 = e1.costoTarea(t1);
		assertSame(cost1, 50.0);
		
		Double cost2 = e2.costoTarea(t1);
		assertSame(cost2, 50.0);
	}
	
	@Test
	public void testCostoTareaTerminadaAntesDeEstimado() {
		Double cost1 = e1.costoTarea(t2);
		assertSame(cost1, 60.0);
		
		Double cost2 = e2.costoTarea(t3);
		assertSame(cost2, 37.5);
		
		Double cost3 = e2.costoTarea(t4);
		assertSame(cost3, 65.0);
	}
	
//	Falta CALCULOPAGOPORTAREA

	@Test
	public void testAsignarTarea() {
		assertTrue(e1.asignarTarea(t1));
		
		assertFalse(e3.asignarTarea(t6));
	}

	@Test
	public void testComenzarInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalizarInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testComenzarIntegerString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalizarIntegerString() {
		fail("Not yet implemented");
	}

}

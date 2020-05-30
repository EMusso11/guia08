package frsf.isi.died.guia08.problema01.modelo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import frsf.isi.died.guia08.problema01.exceptions.AsignarTareaEmpAsignadoException;
import frsf.isi.died.guia08.problema01.exceptions.AsignarTareaFechaFinException;

public class TareaTest {
	Tarea t1, t2, t3;
	Empleado e1, e2;
	
	@Before
	public void setUp() {
		t1 = new Tarea();
		t2 = new Tarea();
		t2.setFechaFin(LocalDateTime.now());
		
		e1 = new Empleado();
		t1.setEmpleadoAsignado(e1);
		
		t3 = new Tarea();
	}
	
	@Test(expected = AsignarTareaEmpAsignadoException.class)
	public void asignarEmpleadoConEmpAsignadoTest() throws AsignarTareaEmpAsignadoException, AsignarTareaFechaFinException {
		t1.asignarEmpleado(e1);
	}
	
	@Test(expected = AsignarTareaFechaFinException.class)
	public void asignarEmpleadoConFechaAsignadaTest() throws AsignarTareaEmpAsignadoException, AsignarTareaFechaFinException {
		t2.asignarEmpleado(e1);
	}
	
	@Test
	public void asignarEmpleadoTest() throws AsignarTareaEmpAsignadoException, AsignarTareaFechaFinException {
		t3.asignarEmpleado(e1);
		assertSame(e1, t3.getEmpleadoAsignado());
	}

}

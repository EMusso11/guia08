package frsf.isi.died.guia08.problema01;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import frsf.isi.died.guia08.problema01.exceptions.AsignarTareaEmpAsignadoException;
import frsf.isi.died.guia08.problema01.exceptions.TareaNoEncontradaException;
import frsf.isi.died.guia08.problema01.modelo.AppRRHH;
import frsf.isi.died.guia08.problema01.modelo.Empleado;
import frsf.isi.died.guia08.problema01.modelo.Tarea;

public class AppRRHHTest {
	AppRRHH app;
	
	@Before
	public void setUp() { // Armo app con un empleado para probar cuando existe y cuando no
		app = new AppRRHH();
		
	}

	@Test
	public void testAgregarEmpleadoContratado() {
		app.agregarEmpleadoContratado(1234, "Perez", 100.0);
		assertTrue(app.getEmpleados()
						.contains(
								app.buscarEmpleado( emp -> emp.getCuil()==1234 &&
								emp.getTipo()==frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo.CONTRATADO).get())
						);
	}
	
	@Test
	public void testAgregarEmpleadoEfectivo() {
		app.agregarEmpleadoContratado(1234, "Perez", 100.0);
		assertTrue(app.getEmpleados()
						.contains(
								app.buscarEmpleado( emp -> emp.getCuil()==1234 &&
								emp.getTipo()==frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo.EFECTIVO).get())
						);
	}

	@Test(expected = NoSuchElementException.class)
	public void testAsignarTareaEmpleadoNoEncontrado() {
		app.setEmpleados(null);	//no va a existir ningun empleado
		app.asignarTarea(1234, 01, null, null);
	}
	
	@Test
	public void testAsignarTareaExitosa() {
		app.agregarEmpleadoContratado(1234,	null, null);
		app.asignarTarea(1234, 01, null, null);
		Empleado e1 = app.buscarEmpleado(e -> e.getCuil().equals(1234)).get();
		assertTrue(e1.getTareasAsignadas().size() == 1);
	}
	
	@Test(expected = TareaNoEncontradaException.class)
	public void testEmpezarTareaNoEncontrada() {
		app.agregarEmpleadoContratado(1234,	null, null);
		app.asignarTarea(1234, 01, null, null);
		app.empezarTarea(1234, 01); // la tarea 01 no existe
	}
	
	@Test
	public void testEmpezarTareaExitosa() {
		app.agregarEmpleadoContratado(1234,	null, null);
		app.asignarTarea(1234, 01, null, null);
		Empleado e1 = app.buscarEmpleado(e -> e.getCuil().equals(1234)).get();
		
		List<Tarea> list = new ArrayList<Tarea>();
		Tarea t1 = new Tarea(01, null, null);
		list.add(t1);
		e1.setTareasAsignadas(list);
		app.empezarTarea(1234, 01);
		assertTrue(e1.getTareasAsignadas().contains(t1));
	}
	
	@Test
	public void testTerminarTareaEmpleadoNoEncontrado() {
		app.terminarTarea(1234, 01);
	}
	
	@Test
	public void testTerminarTareaTareaNoEncontrada() {
		app.agregarEmpleadoContratado(1234,	null, null);
		app.asignarTarea(1234, 01, null, null);
		app.terminarTarea(1234, 01); // la tarea 01 no existe
	}
	
	@Test
	public void testTerminarTareaExitoso() {
		app.agregarEmpleadoContratado(1234,	null, null);
		app.asignarTarea(1234, 01, null, null);
		Empleado e1 = app.buscarEmpleado(e -> e.getCuil().equals(1234)).get();
		
		List<Tarea> list = new ArrayList<Tarea>();
		Tarea t1 = new Tarea(01, null, null);
		list.add(t1);
		e1.setTareasAsignadas(list);
		app.empezarTarea(1234, 01);
		assertTrue(t1.getFechaFin().equals(LocalDateTime.now()));
	}
	
	@Test
	public void testCargarEmpleadosContratadosCSV() throws IOException {
		Empleado e1 = new Empleado(1234, "Perez", 100.0);
		try(Writer fw = new FileWriter("csvTest.csv")){
			try(BufferedWriter wr = new BufferedWriter(fw)){
				wr.write(e1.asCsv());
			}
		}
		catch (IOException e) {
			e.getMessage();
		}
		
		app.cargarEmpleadosContratadosCSV("csvTest");
		assertTrue(app.getEmpleados().contains(e1));
	}
	
	@Test
	public void testCargarEmpleadosEfectivosCSV() throws IOException {
		Empleado e1 = new Empleado(1234, "Perez", 100.0);
		try(Writer fw = new FileWriter("csvTest.csv")){
			try(BufferedWriter wr = new BufferedWriter(fw)){
				wr.write(e1.asCsv()+System.getProperty("line.separator"));
			}
		}
		catch (IOException e) {
			e.getMessage();
		}
		
		app.cargarEmpleadosEfectivosCSV("csvTest");
		assertTrue(app.getEmpleados().contains(e1));
	}
}

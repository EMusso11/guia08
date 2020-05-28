package frsf.isi.died.guia08.problema01.modelo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import frsf.isi.died.guia08.problema01.exceptions.tareaNoEncontradaException;

public class AppRRHH {

	private List<Empleado> empleados;
	
	public void agregarEmpleadoContratado(Integer cuil,String nombre,Double costoHora) {
		// crear un empleado
		// agregarlo a la lista
		Empleado e1 = new Empleado(cuil, nombre, costoHora, frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo.CONTRATADO);
		empleados.add(e1);
	}
	
	public void agregarEmpleadoEfectivo(Integer cuil,String nombre,Double costoHora) {
		// crear un empleado
		// agregarlo a la lista
		Empleado e1 = new Empleado(cuil, nombre, costoHora, frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo.EFECTIVO);
		empleados.add(e1);
	}
	
	public void asignarTarea(Integer cuil,Integer idTarea,String descripcion,Integer duracionEstimada) {
		// crear un empleado
		// con el método buscarEmpleado() de esta clase
		// agregarlo a la lista	
		Empleado e1;
		Tarea t1;
		try {
			e1 = buscarEmpleado( e -> e.getCuil().equals(cuil) ).get();
			t1 = new Tarea(idTarea, descripcion, duracionEstimada);
			e1.asignarTarea(t1);
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
		
		
	}
	
	public void empezarTarea(Integer cuil,Integer idTarea) {
		// busca el empleado por cuil en la lista de empleados
		// con el método buscarEmpleado() actual de esta clase
		// e invoca al método comenzar tarea
		Empleado e1;
		Tarea t1;
		try {
			e1 = buscarEmpleado( e -> e.getCuil().equals(cuil) ).get();
			e1.comenzar(idTarea);
		} catch (NoSuchElementException e) {
			e.getMessage();
		} catch (tareaNoEncontradaException e) {
			e.getMessage();
		}
	}
	
	public void terminarTarea(Integer cuil,Integer idTarea) {
		// crear un empleado
		// agregarlo a la lista	
		Empleado e1;
		Tarea t1;
		try {
			e1 = buscarEmpleado( e -> e.getCuil().equals(cuil) ).get();
			e1.finalizar(idTarea);
		} catch (NoSuchElementException e) {
			e.getMessage();
		} catch (tareaNoEncontradaException e) {
			e.getMessage();
		}
	}

	public void cargarEmpleadosContratadosCSV(String nombreArchivo) {
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado
	}

	public void cargarEmpleadosEfectivosCSV(String nombreArchivo) {
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado		
	}

	public void cargarTareasCSV(String nombreArchivo) {
		// leer datos del archivo
		// cada fila del archivo tendrá:
		// cuil del empleado asignado, numero de la taera, descripcion y duración estimada en horas.
	}
	
	private void guardarTareasTerminadasCSV() {
		// guarda una lista con los datos de la tarea que fueron terminadas
		// y todavía no fueron facturadas
		// y el nombre y cuil del empleado que la finalizó en formato CSV 
	}
	
	private Optional<Empleado> buscarEmpleado(Predicate<Empleado> p){
		return this.empleados.stream().filter(p).findFirst();
	}

	public Double facturar() {
		this.guardarTareasTerminadasCSV();
		return this.empleados.stream()				
				.mapToDouble(e -> e.salario())
				.sum();
	}
}

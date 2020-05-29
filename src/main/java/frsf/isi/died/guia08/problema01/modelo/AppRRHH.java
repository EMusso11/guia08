package frsf.isi.died.guia08.problema01.modelo;

import java.io.*;
import java.util.*;
import java.util.function.*;

import frsf.isi.died.guia08.problema01.exceptions.TareaNoEncontradaException;

public class AppRRHH {

	private List<Empleado> empleados;
	
	public void agregarEmpleadoContratado(Integer cuil,String nombre,Double costoHora) {
		Empleado e1 = new Empleado(cuil, nombre, costoHora, frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo.CONTRATADO);
		empleados.add(e1);
	}
	
	public void agregarEmpleadoEfectivo(Integer cuil,String nombre,Double costoHora) {
		Empleado e1 = new Empleado(cuil, nombre, costoHora, frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo.EFECTIVO);
		empleados.add(e1);
	}
	
	public void asignarTarea(Integer cuil,Integer idTarea,String descripcion,Integer duracionEstimada) {
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
		Empleado e1;
		Tarea t1;
		try {
			e1 = buscarEmpleado( e -> e.getCuil().equals(cuil) ).get();
			e1.comenzar(idTarea);
		} catch (NoSuchElementException e) {
			e.getMessage();
		} catch (TareaNoEncontradaException e) {
			e.getMessage();
		}
	}
	
	public void terminarTarea(Integer cuil,Integer idTarea) {	
		Empleado e1;
		Tarea t1;
		try {
			e1 = buscarEmpleado( e -> e.getCuil().equals(cuil) ).get();
			e1.finalizar(idTarea);
		} catch (NoSuchElementException e) {
			e.getMessage();
		} catch (TareaNoEncontradaException e) {
			e.getMessage();
		}
	}

	public void cargarEmpleadosContratadosCSV(String nombreArchivo) {
		try(Reader fr = new FileReader(nombreArchivo+".csv")){
			try(BufferedReader rd = new BufferedReader(fr)){
				String linea = null;
				while((linea=rd.readLine())!=null) {
					String[] fila = linea.split(";");
					Empleado emp = new Empleado();
					Integer cuil = Integer.valueOf(fila[0]);
					String nombre = fila[1];
					Double costoHora = Double.valueOf(fila[2]);
					agregarEmpleadoContratado(cuil, nombre, costoHora);
				}
			}
		} catch(IOException e) {
			e.getMessage();
		}
	}

	public void cargarEmpleadosEfectivosCSV(String nombreArchivo) {
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado
		try(Reader fr = new FileReader(nombreArchivo+".csv")){
			//cuil;/nombre;/costoHora
			try(BufferedReader rd = new BufferedReader(fr)){
				String linea = null;
				while((linea=rd.readLine())!=null) {
					String[] fila = linea.split(";");
					Empleado emp = new Empleado();
					Integer cuil = Integer.valueOf(fila[0]);
					String nombre = fila[1];
					Double costoHora = Double.valueOf(fila[2]);
					agregarEmpleadoEfectivo(cuil, nombre, costoHora);
				}
			}
		} catch(IOException e) {
			e.getMessage();
		}
	}

	public void cargarTareasCSV(String nombreArchivo) {
		// leer datos del archivo
		// cada fila del archivo tendrá:
		// cuil del empleado asignado, numero de la taera, descripcion y duración estimada en horas.
		try(Reader fr = new FileReader(nombreArchivo+".csv")){
			try(BufferedReader rd = new BufferedReader(fr)){
				String linea = null;
				while((linea=rd.readLine())!=null) {
					String[] fila = linea.split(";");
					Integer idTarea = Integer.valueOf(fila[0]);
					String descripcion = fila[1];
					Integer duracionEstimada = Integer.valueOf(fila[2]);
					Integer cuil = Integer.valueOf(fila[3]);
					Tarea t1 = new Tarea(idTarea, descripcion, duracionEstimada);s
					this.empezarTarea(cuil, idTarea);
				}
			}
		} catch(IOException e) {
			e.getMessage();
		}
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

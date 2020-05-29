package frsf.isi.died.guia08.problema01.modelo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;

import frsf.isi.died.guia08.problema01.exceptions.*;

public class Empleado {

	public enum Tipo {CONTRATADO, EFECTIVO}; 
	
	private Integer cuil;
	private String nombre;
	private Tipo tipo;
	private Double costoHora;
	private List<Tarea> tareasAsignadas;
	
	private Function<Tarea, Double> calculoPagoPorTarea;
	private Predicate<Tarea> puedeAsignarTarea;
	
	public Empleado() {}
	
	public Empleado(Tipo tipo, Double costoHora) {
		this.tipo = tipo;
		this.costoHora = costoHora;
	}
	
	public Empleado(Tipo tipo, List<Tarea> tareasAsignadas, Double costoHora) {
		this.tipo = tipo;
		this.tareasAsignadas = tareasAsignadas;
		this.costoHora = costoHora;
	}
	
	public Empleado(Integer cuil, String nombre, Double costoHora, Tipo tipo) {
		this.cuil = cuil;
		this.nombre = nombre;
		this.costoHora = costoHora;
		this.tipo = tipo;
	}
	
	public Empleado(Integer cuil) {
		this.cuil = cuil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getCostoHora() {
		return costoHora;
	}

	public void setCostoHora(Double costoHora) {
		this.costoHora = costoHora;
	}

	public Integer getCuil() {
		return cuil;
	}

	public void setCuil(Integer cuil) {
		this.cuil = cuil;
	}

	public Double salario() {
		Double cost;
		cost = tareasAsignadas.stream()
							.filter( t -> !t.getFacturada())
							.map( t -> this.costoTarea(t) )
							.reduce(0.0, (c1, c2) -> c1+c2);
		tareasAsignadas.stream()
						.filter( t -> !t.getFacturada())
						.forEach( t -> t.setFacturada(true));
		
		return cost;
	}
	
	/**
	 * Si la tarea ya fue terminada nos indica cuaal es el monto según el algoritmo de calculoPagoPorTarea
	 * Si la tarea no fue terminada simplemente calcula el costo en base a lo estimado.
	 * @param t
	 * @return
	 */
	public Double costoTarea(Tarea t) {
		Double cost=0.0;
		Duration duracion = Duration.between(t.getFechaInicio(), t.getFechaFin());
		calculoPagoPorTarea = (t1) -> 0.0;
		if(t.getFechaFin()!=null) {	//terminada
			
			if((duracion.toDays()*4) < t.getDuracionEstimada()) {
				switch(this.tipo) {
				case EFECTIVO:
					this.costoHora*=1.2;
					break;
				case CONTRATADO:
					if(duracion.toDays() >= 2) {
						this.costoHora*=0.75;
					} else {
						this.costoHora*=1.3;
					}
					break;
				}
			}
			cost += calculoPagoPorTarea.apply(t);
			
		}else {						//no terminada
			cost += this.costoHora*t.getDuracionEstimada();
		}
		
		return cost;
	}
		
	public Boolean asignarTarea(Tarea t) {
		switch(this.tipo) {
		case CONTRATADO:
			long cant = this.tareasAsignadas.stream()
										.filter(tar -> tar.getFechaFin()!=null)
										.count();
			if(cant>=5) return false;
		case EFECTIVO:
			Integer cant_pendientes = this.tareasAsignadas.stream()
												.filter(tar -> tar.getFechaFin()!=null)
												.mapToInt(Tarea::getDuracionEstimada)
												.reduce(0, (dur1, dur2) -> dur1 + dur2);
			if(cant_pendientes+t.getDuracionEstimada()<15)return false;
		}
		
		try {
			tareasAsignadas.add(t);
			t.asignarEmpleado(this);
		} catch(AsignarTareaEmpAsignadoException e) {
			System.out.println(e.getMessage());
		} catch (AsignarTareaFechaFinException e) {
			System.out.println(e.getMessage());
		}

		return true;

	}
	
	public void comenzar(Integer idTarea) throws TareaNoEncontradaException {
		Optional<Tarea> tareaOpt = 	this.tareasAsignadas.stream()
														.filter( tar -> idTarea.equals(tar.getId()) )
														.findFirst();
		if(tareaOpt.isPresent()) {
			tareaOpt.get().setFechaInicio(LocalDateTime.now());
		} else {
			throw new TareaNoEncontradaException("La tarea no existe en la lista.");
		}
	}
	
	public void finalizar(Integer idTarea) throws TareaNoEncontradaException {
		Optional<Tarea> tareaOpt = 	this.tareasAsignadas.stream()
				.filter( tar -> idTarea.equals(tar.getId()) )
				.findFirst();
		if(tareaOpt.isPresent()) {
			tareaOpt.get().setFechaFin(LocalDateTime.now());
		} else {
			throw new TareaNoEncontradaException("La tarea no existe en la lista.");
		}
	}

	public void comenzar(Integer idTarea,String fecha) throws TareaNoEncontradaException {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		Optional<Tarea> tareaOpt = 	this.tareasAsignadas.stream()
				.filter( tar -> idTarea.equals(tar.getId()) )
				.findFirst();
		if(tareaOpt.isPresent()) {
			tareaOpt.get().setFechaInicio(LocalDateTime.parse(fecha, format));
		} else {
			throw new TareaNoEncontradaException("La tarea no existe en la lista.");
		}
	}
	
	public void finalizar(Integer idTarea,String fecha) throws TareaNoEncontradaException {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		Optional<Tarea> tareaOpt = 	this.tareasAsignadas.stream()
				.filter( tar -> idTarea.equals(tar.getId()) )
				.findFirst();
		if(tareaOpt.isPresent()) {
			tareaOpt.get().setFechaFin(LocalDateTime.parse(fecha, format));
		} else {
			throw new TareaNoEncontradaException("La tarea no existe en la lista.");
		}
	}
}

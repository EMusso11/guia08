package frsf.isi.died.guia08.problema01.modelo;

import java.time.LocalDateTime;
import frsf.isi.died.guia08.problema01.exceptions.*;

public class Tarea {

	private Integer id;
	private String descripcion;
	private Integer duracionEstimada;
	private Empleado empleadoAsignado;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private Boolean facturada;
	
	public Tarea() {
		this.facturada = false;
	}
	
	public Tarea(LocalDateTime fi, LocalDateTime ff, Integer de) {
		this.facturada = false;
		this.fechaInicio = fi;
		this.fechaFin = ff;
		this.duracionEstimada = de;
	}
	
	public Tarea(Integer idTarea, String descripcion, Integer duracionEstimada) {
		this.id = idTarea;
		this.descripcion = descripcion;
		this.duracionEstimada = duracionEstimada;
	}

	public void asignarEmpleado(Empleado e) throws AsignarTareaEmpAsignadoException, AsignarTareaFechaFinException {
		if(empleadoAsignado!=null) throw new AsignarTareaEmpAsignadoException("La tarea que se quiere asignar es incorrecta. Seleccione otra tarea.");
		if(fechaFin!=null) throw new AsignarTareaFechaFinException("La tarea que se quiere asignar ha finalizado.");
		this.empleadoAsignado = e;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDuracionEstimada() {
		return duracionEstimada;
	}

	public void setDuracionEstimada(Integer duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setEmpleadoAsignado(Empleado empleadoAsignado) {
		this.empleadoAsignado = empleadoAsignado;
	}

	public Boolean getFacturada() {
		return facturada;
	}

	public void setFacturada(Boolean facturada) {
		this.facturada = facturada;
	}

	public Empleado getEmpleadoAsignado() {
		return empleadoAsignado;
	}

	public String asCsv(Integer cuil, String nombre) {
		return this.id+";\""+this.descripcion+";\""+cuil+";\""+nombre;
	}
	
}
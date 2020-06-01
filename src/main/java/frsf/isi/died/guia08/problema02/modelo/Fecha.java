package frsf.isi.died.guia08.problema02.modelo;

import java.util.List;

public class Fecha {

	private Integer numero;
	private List<Partido> partidos;
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public List<Partido> getPartidos() {
		return partidos;
	}
	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
}

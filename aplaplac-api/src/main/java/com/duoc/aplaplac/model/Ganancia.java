package com.baufest.tennis.springtennis.model;

public class Ganancia {
	private Long id;
	private int ganancia;
	
	public Ganancia(Long id, int ganancia) {
		super();
		this.id = id;
		this.ganancia = ganancia;
	}
	
	public Ganancia() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getGanancia() {
		return ganancia;
	}
	public void setGanancia(int ganancia) {
		this.ganancia = ganancia;
	}
	
	
}

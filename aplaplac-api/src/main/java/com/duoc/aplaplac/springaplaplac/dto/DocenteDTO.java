package com.duoc.aplaplac.springaplaplac.dto;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DocenteDTO {
	private Long id;

	private String nombre;

	private int rut;
	
	private CarreraDTO carrera;
	
	public DocenteDTO(String nombre, int rut) {
		this.nombre = nombre;
		this.rut = rut;
	}

	public DocenteDTO(String nombre, int rut, CarreraDTO carrera) {
		this.nombre = nombre;
		this.rut = rut;
		this.carrera = carrera;
	}

	public DocenteDTO(Long id, String nombre, int rut, CarreraDTO carrera) {
		this.id = id;
		this.nombre = nombre;
		this.rut = rut;
		this.carrera = carrera;
	}

	public DocenteDTO(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRut() {
		return rut;
	}

	public void setRut(int puntos) {
		this.rut = puntos;
	}
	
	public CarreraDTO getCarrera() {
		return carrera;
	}

	public void setCarrera(CarreraDTO carrera) {
		this.carrera = carrera;
	}

	public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		jo.put("id",getId());
		jo.put("nombre",getNombre());
		jo.put("puntos",getRut());
		jo.put("carrera",getCarrera());
		return jo;
	}
}

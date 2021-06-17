package com.duoc.aplaplac.springaplaplac.dto;


import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CarreraDTO {
	
	private Long id;

	private String nombre;

	public CarreraDTO(String nombre) {
		this.nombre = nombre;
	}

	public CarreraDTO() {
	}

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

	@Override
	public String toString() {
		return "Cancha [id=" + id + ", nombre=" + nombre + "]";
	}
	public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		jo.put("id",getId());
		jo.put("nombre",getNombre());
		return jo;
	}
}

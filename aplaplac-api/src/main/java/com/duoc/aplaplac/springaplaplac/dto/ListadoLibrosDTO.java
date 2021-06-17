package com.duoc.aplaplac.springaplaplac.dto;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ListadoLibrosDTO {
	
	private Long id;

	private String nombre;
	
	private int cantidad;

	public ListadoLibrosDTO(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	public ListadoLibrosDTO() {
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
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Cancha [id=" + id + ", nombre=" + nombre + ", cantidad="  + cantidad + "]";
	}
	public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		jo.put("id",getId());
		jo.put("nombre",getNombre());
		jo.put("cantidad",getCantidad());
		return jo;
	}
}

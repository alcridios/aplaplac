package com.duoc.aplaplac.springaplaplac.model;

import org.json.JSONObject;
import javax.persistence.*;

@Entity
@Table(name = "ListadoLibros")
public class ListadoLibros {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private int cantidad;

	public ListadoLibros(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	public ListadoLibros() {
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

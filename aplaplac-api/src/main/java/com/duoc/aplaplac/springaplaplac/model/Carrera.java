package com.duoc.aplaplac.springaplaplac.model;

import org.json.JSONObject;
import javax.persistence.*;

@Entity
@Table(name = "Carrera")
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	public Carrera(String nombre) {
		this.nombre = nombre;
	}

	public Carrera() {
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

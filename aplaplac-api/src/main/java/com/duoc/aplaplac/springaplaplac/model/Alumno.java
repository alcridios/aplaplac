package com.duoc.aplaplac.springaplaplac.model;

import org.json.JSONObject;
import javax.persistence.*;

@Entity
@Table(name = "Alumno")
public class Alumno{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private int rut;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idLocal", nullable = true)
	private Carrera carrera;
	
	public Alumno(String nombre, int rut) {
		this.nombre = nombre;
		this.rut = rut;
	}

	public Alumno(String nombre, int rut, Carrera carrera) {
		this.nombre = nombre;
		this.rut = rut;
		this.carrera = carrera;
	}

	public Alumno(Long id, String nombre, int rut, Carrera carrera) {
		this.id = id;
		this.nombre = nombre;
		this.rut = rut;
		this.carrera = carrera;
	}

	public Alumno(){}

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
	
	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
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

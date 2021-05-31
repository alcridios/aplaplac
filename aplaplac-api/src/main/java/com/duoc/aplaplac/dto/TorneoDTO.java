package com.baufest.tennis.springtennis.dto;

import org.json.JSONObject;
import java.util.Date;

public class TorneoDTO {

	private Long id;

	private Date fechaComienzo;

	private JugadorDTO jugador_1;

	private JugadorDTO jugador_2;
	
	private JugadorDTO jugador_3;

	private JugadorDTO jugador_4;

	public TorneoDTO(Date fechaComienzo, JugadorDTO jugador_1, JugadorDTO jugador_2, 
			JugadorDTO jugador_3, JugadorDTO jugador_4) {
		this.fechaComienzo = fechaComienzo;
		this.jugador_1 = jugador_1;
		this.jugador_2 = jugador_2;
		this.jugador_3 = jugador_3;
		this.jugador_4 = jugador_4;
	}

	public TorneoDTO(Long id, Date fechaComienzo, JugadorDTO jugador_1, JugadorDTO jugador_2, 
			JugadorDTO jugador_3, JugadorDTO jugador_4) {
		this.id = id;
		this.fechaComienzo = fechaComienzo;
		this.jugador_1 = jugador_1;
		this.jugador_2 = jugador_2;
		this.jugador_3 = jugador_3;
		this.jugador_4 = jugador_4;
	}
	

	public TorneoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaComienzo() {
		return fechaComienzo;
	}

	public void setFechaComienzo(Date fechaComienzo) {
		this.fechaComienzo = fechaComienzo;
	}

	public JugadorDTO getJugador_1() {
		return jugador_1;
	}

	public void setJugador_1(JugadorDTO jugador_1) {
		this.jugador_1 = jugador_1;
	}

	public JugadorDTO getJugador_2() {
		return jugador_2;
	}

	public void setJugador_2(JugadorDTO jugador_2) {
		this.jugador_2 = jugador_2;
	}

	public JugadorDTO getJugador_3() {
		return jugador_3;
	}

	public void setJugador_3(JugadorDTO jugador_3) {
		this.jugador_3 = jugador_3;
	}

	public JugadorDTO getJugador_4() {
		return jugador_4;
	}

	public void setJugador_4(JugadorDTO jugador_4) {
		this.jugador_4 = jugador_4;
	}

	public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		jo.put("id", getId());
		jo.put("fechaComienzo", getFechaComienzo());
		jo.put("jugador_1",getJugador_1());
		jo.put("jugador_2",getJugador_2());
		jo.put("jugador_3",getJugador_3());
		jo.put("jugador_4",getJugador_4());
		return jo;
	}
}
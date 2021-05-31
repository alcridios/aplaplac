package com.baufest.tennis.springtennis.model;

import org.json.JSONObject;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Torneo")
public class Torneo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date fechaComienzo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idJugador_1", nullable = false)
	private Jugador jugador_1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idJugador_2", nullable = false)
	private Jugador jugador_2;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idJugador_3", nullable = false)
	private Jugador jugador_3;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idJugador_4", nullable = false)
	private Jugador jugador_4;

	public Torneo(Date fechaComienzo, Jugador jugador_1, Jugador jugador_2, Jugador jugador_3, Jugador jugador_4) {
		this.fechaComienzo = fechaComienzo;
		this.jugador_1 = jugador_1;
		this.jugador_2 = jugador_2;
		this.jugador_3 = jugador_3;
		this.jugador_4 = jugador_4;
	}

	public Torneo(Long id, Date fechaComienzo, Jugador jugador_1, Jugador jugador_2, Jugador jugador_3, Jugador jugador_4) {
		this.id = id;
		this.fechaComienzo = fechaComienzo;
		this.jugador_1 = jugador_1;
		this.jugador_2 = jugador_2;
		this.jugador_3 = jugador_3;
		this.jugador_4 = jugador_4;
	}
	

	public Torneo() {
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

	public Jugador getJugador_1() {
		return jugador_1;
	}

	public void setJugador_1(Jugador jugador_1) {
		this.jugador_1 = jugador_1;
	}

	public Jugador getJugador_2() {
		return jugador_2;
	}

	public void setJugador_2(Jugador jugador_2) {
		this.jugador_2 = jugador_2;
	}

	public Jugador getJugador_3() {
		return jugador_3;
	}

	public void setJugador_3(Jugador jugador_3) {
		this.jugador_3 = jugador_3;
	}

	public Jugador getJugador_4() {
		return jugador_4;
	}

	public void setJugador_4(Jugador jugador_4) {
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
package org.company.demo.chess.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private int id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "white")
	private Player white;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "black")
	private Player black;

	@JsonProperty
	@Enumerated(EnumType.STRING)
	private Result result;
	
	@JsonProperty
	@Column(length = 2500)
	private String pgn;

	public Match(Player white, Player black, Result result, String pgn) {
		super();
		this.white = white;
		this.black = black;
		this.result = result;
		this.pgn = pgn;
	}

	public Match() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	public enum Result {
		WHITE_WIN, DRAW, BLACK_WIN
	}

	public void setWhite(Player white) {
		this.white = white;
	}

	public void setBlack(Player black) {
		this.black = black;
	}

//	@JsonProperty
//	public Integer getWhiteId() {
//		if(this.white != null) {
//			return this.white.getId();
//		}
//		return null;
//	}
//	
//	@JsonProperty
//	public Integer getBlackId() {
//		if(this.black != null) {
//			return this.black.getId();
//		}
//		return null;
//	}

	@JsonProperty
	public Player getWhite() {
		if (this.white != null) {
			return this.white;
		}
		return null;
	}

	@JsonProperty
	public Player getBlack() {
		if (this.black != null) {
			return this.black;
		}
		return null;
	}

	public void setWhiteId(Integer whiteId) {
		if (whiteId == null) {
			this.white = null;
			return;
		}
		this.white = new Player();
		this.white.setId(whiteId.intValue());
	}

	public void setBlackId(Integer blackId) {
		if (blackId == null) {
			this.black = null;
			return;
		}
		this.black = new Player();
		this.black.setId(blackId.intValue());
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}

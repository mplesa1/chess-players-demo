package org.company.demo.chess.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private int id;
	@JsonProperty
	private String firstName;
	@JsonProperty
	private String lastName;
	@JsonProperty
	private double score;

	@OneToMany(mappedBy = "white")
	private Set<Match> whiteMatches;
	
	@OneToMany(mappedBy = "black")
	private Set<Match> blackMatches;

	public Player(String firstName, String lastName, double score) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = score;
	}

	public Player() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setWhiteMatches(Set<Match> whiteMatches) {
		this.whiteMatches = whiteMatches;
	}

	public void setBlackMatches(Set<Match> blackMatches) {
		this.blackMatches = blackMatches;
	}

}

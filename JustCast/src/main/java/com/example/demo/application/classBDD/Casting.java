package com.example.demo.application.classBDD;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Casting {

	private Long id;
	private String nomFilm;
	private String role;
	private Integer ageMin;
	private Integer ageMax;
	private String sexe;
	private String createur;
	// cloture 0 quand ouvert et 1 qaund ferme
	private int cloture;
	
	public Casting(String nomFilm, String role, Integer ageMin, Integer ageMax, String sexe,String createur,Integer cloture) {
		
		super();
		this.nomFilm = nomFilm;
		this.role = role;
		this.ageMin = ageMin;
		this.ageMax = ageMax;
		this.sexe = sexe;
		this.createur=createur;
		this.cloture = cloture;
	}

	public Casting() {}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomFilm() {
		return nomFilm;
	}

	public void setNomFilm(String nomFilm) {
		this.nomFilm = nomFilm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}

	public Integer getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(Integer ageMax) {
		this.ageMax = ageMax;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	public String getCreateur() {
		return createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}
	
	public Integer getCloture() {
		return cloture;
	}

	public void setCloture(Integer cloture) {
		this.cloture = cloture;
	}
	
}


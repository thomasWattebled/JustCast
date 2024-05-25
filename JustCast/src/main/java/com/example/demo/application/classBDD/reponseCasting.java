package com.example.demo.application.classBDD;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class reponseCasting {
	
	private Long id;
	private Long idCasting;
	private Long idActeur;
	private String nomActeur;
	private String prenomActeur;
	private Long ageActeur;
	private String telActeur;
	private String imgActeur;
	private String film;
	private String role;
	
	public reponseCasting(Long idCasting,Long idActeur, String nomActeur, String prenomActeur, Long ageActeur, String telActeur,
			String imgActeur,String film,String role) {
		super();
		this.idCasting = idCasting;
		this.idActeur = idActeur;
		this.nomActeur = nomActeur;
		this.prenomActeur = prenomActeur;
		this.ageActeur = ageActeur;
		this.telActeur = telActeur;
		this.imgActeur = imgActeur;
		this.film = film;
		this.role = role;
	}
	
	public reponseCasting() {}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCasting() {
		return idCasting;
	}

	public void setIdCasting(Long idCasting) {
		this.idCasting = idCasting;
	}

	public String getNomActeur() {
		return nomActeur;
	}

	public void setNomActeur(String nomActeur) {
		this.nomActeur = nomActeur;
	}

	public String getPrenomActeur() {
		return prenomActeur;
	}

	public void setPrenomActeur(String prenomActeur) {
		this.prenomActeur = prenomActeur;
	}

	public Long getAgeActeur() {
		return ageActeur;
	}

	public void setAgeActeur(Long ageActeur) {
		this.ageActeur = ageActeur;
	}

	public String getTelActeur() {
		return telActeur;
	}

	public void setTelActeur(String telActeur) {
		this.telActeur = telActeur;
	}

	public String getImgActeur() {
		return imgActeur;
	}

	public void setImgActeur(String imgActeur) {
		this.imgActeur = imgActeur;
	}

	public Long getIdActeur() {
		return idActeur;
	}

	public void setIdActeur(Long idActeur) {
		this.idActeur = idActeur;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}

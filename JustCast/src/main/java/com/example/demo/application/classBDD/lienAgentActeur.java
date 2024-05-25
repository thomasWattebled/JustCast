package com.example.demo.application.classBDD;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class lienAgentActeur {
	
	private Long id;
	private Long idAgent;
	private Long idActeur;
	
	public lienAgentActeur(Long idAgent, Long idActeur) {
		super();
		this.idAgent = idAgent;
		this.idActeur = idActeur;
	} 
	
	public lienAgentActeur() {}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(Long idAgent) {
		this.idAgent = idAgent;
	}

	public Long getIdActeur() {
		return idActeur;
	}

	public void setIdActeur(Long idActeur) {
		this.idActeur = idActeur;
	}
	
	

}
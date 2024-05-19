package com.example.demo.application.service;

public interface directeurCastingService {

	void ajouterDirecteurCasting(String nom, String prenom, String mail, String mdp, String tel);
	
	boolean existant(String mail, String mdp);
	
}

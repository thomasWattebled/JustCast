package com.example.demo.application.service;

import com.example.demo.application.classBDD.directeurCasting;

public interface directeurCastingService {

	void ajouterDirecteurCasting(String nom, String prenom, String mail, String mdp, String tel);
	
	directeurCasting existant(String mail, String mdp);
	
}

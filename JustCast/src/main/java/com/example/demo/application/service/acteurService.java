package com.example.demo.application.service;

import com.example.demo.application.classBDD.acteur;

public interface acteurService {
	
	void ajouterActeur(String nom, String prenom, String mail, String mdp, String tel, Long age);
	
	acteur existant(String mail, String mdp);

}

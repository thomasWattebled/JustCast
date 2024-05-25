package com.example.demo.application.service;

import com.example.demo.application.classBDD.Agent;

public interface agentService {

	void ajouterAgent(String nom, String prenom, String mail, String mdp, String tel);
	
	Agent existant(String mail, String mdp);
	
}

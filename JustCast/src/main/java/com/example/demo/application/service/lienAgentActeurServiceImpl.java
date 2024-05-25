package com.example.demo.application.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.application.classBDD.lienAgentActeur;
import com.example.demo.application.repository.lienAgentActeurRepository;

public class lienAgentActeurServiceImpl implements lienAgentActeurService {

	@Autowired
	private lienAgentActeurRepository repoLien;
	
	@Override
	public void ajouterLien(Long idAgent, Long idActeur) {
		repoLien.save(new lienAgentActeur(idAgent,idActeur));
	}

	
}	

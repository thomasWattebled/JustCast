package com.example.demo.application.service;

import java.util.List;

public interface lienAgentActeurService{
	
	void ajouterLien(Long idAgent,Long idActeur);
	
	List<Long> getIdActeurByAgent(Long idAgent);

}
package com.example.demo.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.classBDD.lienAgentActeur;
import com.example.demo.application.repository.lienAgentActeurRepository;

@Service
public class lienAgentActeurServiceImpl implements lienAgentActeurService {

	@Autowired
	private lienAgentActeurRepository repoLien;
	
	@Override
	public void ajouterLien(Long idAgent, Long idActeur) {
		repoLien.save(new lienAgentActeur(idAgent,idActeur));
	}

	@Override
	public List<Long> getIdActeurByAgent(Long idAgent) {
		return repoLien.findActeurByAgent(idAgent);
	}

	
}	

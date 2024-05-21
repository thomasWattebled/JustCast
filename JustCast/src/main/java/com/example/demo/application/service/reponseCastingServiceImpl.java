package com.example.demo.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.classBDD.reponseCasting;
import com.example.demo.application.repository.reponseCastingRepository;

@Service
public class reponseCastingServiceImpl implements reponseCastingService {
	
	@Autowired
	private reponseCastingRepository repoReponseCasting;

	@Override
	public void ajouterReponse(Long idCasting, String nomActeur, String prenomActeur, Long ageActeur, String telActeur,
			String imgActeur) {
		
		repoReponseCasting.save(new reponseCasting(idCasting,nomActeur,prenomActeur,ageActeur,telActeur,imgActeur));
		
	}

}

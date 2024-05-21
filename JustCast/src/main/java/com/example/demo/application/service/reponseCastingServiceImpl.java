package com.example.demo.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.classBDD.Casting;
import com.example.demo.application.classBDD.reponseCasting;
import com.example.demo.application.repository.reponseCastingRepository;

@Service
public class reponseCastingServiceImpl implements reponseCastingService {
	
	@Autowired
	private reponseCastingRepository repoReponseCasting;

	@Override
	public void ajouterReponse(Long idCasting,Long idActeur, String nomActeur, String prenomActeur, Long ageActeur, String telActeur,
			String imgActeur) {
		
		repoReponseCasting.save(new reponseCasting(idCasting,idActeur,nomActeur,prenomActeur,ageActeur,telActeur,imgActeur));
		
	}

	@Override
	public List<reponseCasting> getCastingByIdActeur(Long idActeur) {
		return repoReponseCasting.findByIdActeur(idActeur);
	}

}

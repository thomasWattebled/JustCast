package com.example.demo.application.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.application.classBDD.Casting;
import com.example.demo.application.classBDD.reponseCasting;

public interface reponseCastingService {

	void ajouterReponse(Long idCasting,Long idActeur,String nomActeur,String prenomActeur,Long ageActeur,String telActeur,String imgActeur,String film,String role);

	List<reponseCasting> getCastingByIdActeur(Long idActeur);
	
	public List<reponseCasting> getCastingByIdCasting(Long idCasting);
	
}

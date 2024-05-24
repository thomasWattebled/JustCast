package com.example.demo.application.service;

import java.util.List;

import com.example.demo.application.classBDD.Casting;

public interface CastingService {
	
	public List<Casting> getCastingByCreateur(String email);

	void ajouterCasting(String nomFilm, String role, Integer ageMin, Integer ageMax, String sexe,String createur,Integer cloture);
	
	
	
	Casting existant(String nomFilm, String role);

	public List<Casting> getAllCasting();
	
	public List<Casting> getCastingByCloture(int cloture);

	public List<Casting> getCastingById(Long idCasting);

	public Casting updateCasting(Long idCasting, String nomFilm, String role, int ageMin, int ageMax, String sexe, String string,Integer cloture);

}


/*
public static List<Casting> getCastingByCreateur(String email) {
	// TODO Auto-generated method stub
	return null;
}*/
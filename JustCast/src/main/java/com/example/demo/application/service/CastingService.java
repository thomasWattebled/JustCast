package com.example.demo.application.service;

import java.util.List;

import com.example.demo.application.classBDD.Casting;

public interface CastingService {
	
	public List<Casting> getCastingByCreateur(String email);

	void ajouterCasting(String nomFilm, String role, Integer ageMin, Integer ageMax, String sexe,String createur);
	
	
	
	Casting existant(String nomFilm, String role);

	public List<Casting> getAllCasting();
}


/*
public static List<Casting> getCastingByCreateur(String email) {
	// TODO Auto-generated method stub
	return null;
}*/
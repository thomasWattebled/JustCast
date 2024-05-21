package com.example.demo.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.application.classBDD.Casting;
import com.example.demo.application.repository.CastingRepository;


@Service
public class CastingServiceImpl implements CastingService{

	
	@Autowired
	private CastingRepository repoCasting;

	@Override
	public void ajouterCasting(String nomFilm, String role, Integer ageMin, Integer ageMax, String sexe, String createur) {
		repoCasting.save(new Casting(nomFilm,role,ageMin,ageMax,sexe,createur));
	}

	@Override
	public Casting existant(String nomFilm, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Casting> getCastingByCreateur(String email) {
		return repoCasting.findByCreateur(email); 
	}

	@Override
	public List<Casting> getAllCasting() {
		return (List<Casting>) repoCasting.findAll();
	}
}

	
	
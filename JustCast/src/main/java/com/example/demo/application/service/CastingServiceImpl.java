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

	@Override
	public List<Casting> getCastingById(Long idCasting) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Casting updateCasting(Long idCasting, String nomFilm, String role, int ageMin, int ageMax, String sexe, String string) {
		
		Optional<Casting> casting = repoCasting.findById(idCasting);
		
		if(casting.isPresent()) {
			Casting getCasting = casting.get();
			getCasting.setNomFilm(nomFilm);
			getCasting.setAgeMax(ageMax);
			getCasting.setAgeMin(ageMin);
			getCasting.setRole(role);
			getCasting.setSexe(sexe);
			
			return repoCasting.save(getCasting);
		}
		
		else {
            // Gérer le cas où le casting n'existe pas
            throw new RuntimeException("Casting not found with id " + idCasting);
        }
		
	}
}

	
	
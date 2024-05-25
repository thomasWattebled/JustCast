package com.example.demo.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.application.classBDD.Evenement;
import com.example.demo.application.repository.EvenementRepository;

public class EvenementServiceImpl implements EvenementService {
    
    @Autowired
	private EvenementRepository evenementRepository;

    @Override
    public List<Evenement> getEvenementByUserEvenementIdAndYearAndMonthAndDay(long idUserEvenement, long year, long month, long day) {
        List<Evenement> listeEvenements = new ArrayList<Evenement>();
        Iterable<Evenement> evenementIterable = evenementRepository.findByIdUserEvenementAndYearAndMonthAndDay(idUserEvenement, year, month, day);
        for (Evenement event : evenementIterable) {
            listeEvenements.add(event);
        }
        return listeEvenements;
    }
	
    @Override
	public boolean evenementExiste(long idUserEvenement, long year, long month, long day, long hour) {
        List<Evenement> listeEvenements = new ArrayList<Evenement>();
        Iterable<Evenement> evenementIterable = evenementRepository.findByIdUserEvenementAndYearAndMonthAndDay(idUserEvenement, year, month, day);
        for (Evenement event : evenementIterable) {
            listeEvenements.add(event);
        }
        return listeEvenements.size() != 0;
    }

    @Override
    public void ajouterEvenement(long idUserEvenement, long year, long month, long day, long hour, String libelle, String description) {
        if (!this.evenementExiste(idUserEvenement, year, month, day, hour)) {
            evenementRepository.save(new Evenement(idUserEvenement, year, month, day, hour, libelle, description));
        }
    }
}

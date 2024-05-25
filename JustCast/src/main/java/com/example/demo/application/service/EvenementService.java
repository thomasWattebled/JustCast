package com.example.demo.application.service;

import java.util.List;

import com.example.demo.application.classBDD.Evenement;

public interface EvenementService {

	List<Evenement> getEvenementByUserEvenementIdAndYearAndMonthAndDay(long idUserEvenement, long year, long month, long day);

    void ajouterEvenement(long idUserEvenement, long year, long month, long day, long hour, String libelle, String description);
	
	boolean evenementExiste(long idUserEvenement, long year, long month, long day, long hour);
	
}
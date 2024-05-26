package com.example.demo.application.service;

import java.util.List;

import com.example.demo.application.classBDD.Evenement;

public interface EvenementService {

	List<Evenement> getEvenementByUserIdAndUserTypeIdAndYearAndMonthAndDay(long userId, int userType, long year, long month, long day);

    void ajouterEvenement(long userId, int userType, long year, long month, long day, long hour, String libelle, String description);
	
	boolean evenementExiste(long userId, int userType, long year, long month, long day, long hour);
	
}
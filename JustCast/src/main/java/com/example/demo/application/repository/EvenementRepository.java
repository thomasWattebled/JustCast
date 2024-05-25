package com.example.demo.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.application.classBDD.Evenement;

public interface EvenementRepository extends CrudRepository <Evenement,Long>{

	Iterable<Evenement> findByIdUserEvenementAndYearAndMonthAndDay(long idUserEvenement, long year, long month, long day);
	Iterable<Evenement> findByIdUserEvenementAndYearAndMonthAndDayAndHour(long idUserEvenement, long year, long month, long day, long hour);

}
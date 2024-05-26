package com.example.demo.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.application.classBDD.Evenement;

public interface EvenementRepository extends CrudRepository <Evenement,Long>{

	Iterable<Evenement> findByUserIdAndUserTypeAndYearAttAndMonthAttAndDayAtt(long userId, int userType, long yearAtt, long monthAtt, long dayAtt);
	Iterable<Evenement> findByUserIdAndUserTypeAndYearAttAndMonthAttAndDayAttAndHourAtt(long userId, int userType, long yearAtt, long monthAtt, long dayAtt, long hourAtt);

}
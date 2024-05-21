package com.example.demo.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.application.classBDD.Casting;
import com.example.demo.application.classBDD.reponseCasting;

public interface reponseCastingRepository extends CrudRepository <reponseCasting,Long> {

	List<reponseCasting> findByIdActeur(Long idActeur);

	
	
}

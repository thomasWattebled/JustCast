package com.example.demo.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.application.classBDD.Casting;


public interface CastingRepository extends CrudRepository <Casting,Long>{

	Iterable<Casting> findByNomFilmAndRole(String nomFilm, String role);
	
	List<Casting> findByCreateur(String email);

}

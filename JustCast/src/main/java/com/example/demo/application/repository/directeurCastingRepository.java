package com.example.demo.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.application.classBDD.directeurCasting;

public interface directeurCastingRepository extends CrudRepository <directeurCasting,Long>{

	Iterable<directeurCasting> findByMail(String mail);

}

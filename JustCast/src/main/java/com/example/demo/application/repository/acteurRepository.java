package com.example.demo.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.application.classBDD.acteur;

public interface acteurRepository extends CrudRepository <acteur,Long>{

	Iterable<acteur> findByMail(String mail);

}
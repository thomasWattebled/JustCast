package com.example.demo.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.application.classBDD.acteur;

public interface acteurRepository extends CrudRepository <acteur,Long>{

	Iterable<acteur> findByMail(String mail);
	
	@Query("SELECT a FROM acteur a WHERE a.id NOT IN :ids")
    List<acteur> findActeursNotInIds(@Param("ids") List<Long> ids);
    
    @Query("SELECT a FROM acteur a WHERE a.id IN :ids")
    List<acteur> findActeursById(@Param("ids") List<Long> ids);

}
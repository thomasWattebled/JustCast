package com.example.demo.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.application.classBDD.lienAgentActeur;

public interface lienAgentActeurRepository extends CrudRepository <lienAgentActeur,Long> {
	
	@Query("SELECT a.idActeur FROM lienAgentActeur a WHERE a.idAgent = :ids")
    List<Long> findActeurByAgent(@Param("ids") Long ids);
	
}
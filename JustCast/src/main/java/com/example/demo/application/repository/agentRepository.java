package com.example.demo.application.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.application.classBDD.Agent;
import com.example.demo.application.classBDD.directeurCasting;

public interface agentRepository extends CrudRepository <Agent,Long>{

	Iterable<Agent> findByMail(String mail);

}

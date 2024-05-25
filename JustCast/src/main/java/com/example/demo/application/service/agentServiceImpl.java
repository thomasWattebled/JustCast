package com.example.demo.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.classBDD.Agent;
import com.example.demo.application.classBDD.directeurCasting;
import com.example.demo.application.repository.agentRepository;


@Service
public class agentServiceImpl implements agentService {

	@Autowired
	private agentRepository repoAgent;
	
	@Override
	public void ajouterAgent(String nom, String prenom, String mail, String mdp, String tel) {
		repoAgent.save(new Agent(nom,prenom,mail,mdp,tel));
		
	}
	
	@Override
	public Agent existant(String mail, String mdp) {
		Iterable<Agent> verificationMail = repoAgent.findByMail(mail);
		Agent a = null;
		for (Agent d : verificationMail) {
			if( d.getMdp().equals(mdp)) {
				a = d;
				return a;
			}
		}
		return null;
	}

	
	
}

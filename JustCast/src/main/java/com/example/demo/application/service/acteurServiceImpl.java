package com.example.demo.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.classBDD.acteur;
import com.example.demo.application.repository.acteurRepository;

@Service
public class acteurServiceImpl implements acteurService{
	
	@Autowired
	private acteurRepository repoActeur;

	@Override
	public void ajouterActeur(String nom, String prenom, String mail, String mdp, String tel, Long age) {
		repoActeur.save(new acteur(nom,prenom,mail,mdp,tel,age));
		
	}

	@Override
	public acteur existant(String mail, String mdp) {
		Iterable<acteur> verificationMail = repoActeur.findByMail(mail);
		acteur a = null;
		for (acteur d : verificationMail) {
			if( d.getMdp().equals(mdp)) {
				a = d;
				return a;
			}
		}
		return null;
	}

	@Override
	public List<acteur> getAllAuteurs() {
		return (List<acteur>) repoActeur.findAll();
	}
	
	@Override
	public List<acteur> getActeursNotInIds(List<Long> ids) {
        return repoActeur.findActeursNotInIds(ids);
    }

	@Override
	public List<acteur> getActeursById(List<Long> ids) {
		return repoActeur.findActeursById(ids);
	}

}

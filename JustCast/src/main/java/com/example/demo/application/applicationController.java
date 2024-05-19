package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.application.service.directeurCastingService;
import com.example.demo.application.service.agentService;


@Controller
@RequestMapping("/application")
public class applicationController {
	
	@Autowired
	private directeurCastingService DCService;

	@Autowired
	private agentService agentService;
	
	@GetMapping("/home")
	public String home() {
		return "/application/home";
	}
	
	@GetMapping("/pageInscription")
	public String pageInscription() {
		return "/application/pageInscription";
	}
	
	@PostMapping("/pageInscription")
	public String pageInscription(@RequestParam String role) {
		System.out.println(role.equals("dc"))	;
		if(role.equals("dc")) {
			return "/application/pageInscriptionDC";
		}
		if(role.equals("agent")) {
			return "/application/pageInscriptionAgent";
		}
		if(role.equals("acteur")) {
			return "/application/pageInscriptionActeur";
		}	
		return "/application/pageInscription";
	}
	
	@PostMapping("/inscriptionDC")
	public String inscriptionDC(@RequestParam String nom,@RequestParam String prenom,@RequestParam String mail,@RequestParam String tel,@RequestParam String mdp) {
		DCService.ajouterDirecteurCasting(nom, prenom, mail, mdp, tel);
		return "/application/home";
	}
	
	@PostMapping("/inscriptionAgent")
	public String inscriptionAgent(@RequestParam String nom,@RequestParam String prenom,@RequestParam String mail,@RequestParam String tel,@RequestParam String mdp) {
		agentService.ajouterAgent(nom, prenom, mail, mdp, tel);
		return "/application/home";
	}
	
	@PostMapping("/connexion")
	public String connexion(@RequestParam String role,@RequestParam String mail,@RequestParam String mdp) {
		if(role.equals("dc")) {
			boolean verification = DCService.existant(mail, mdp);
			if(verification==true) {
				return "application/connexionReussie";
			}
		}
		
		if(role.equals("agent")) {
			boolean verification = agentService.existant(mail, mdp);
			if(verification==true) {
				return "application/connexionReussie";
			}
		}
		
		return "/application/home";
	}
	
	
}

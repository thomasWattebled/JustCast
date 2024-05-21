package com.example.demo.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.application.service.directeurCastingService;


import jakarta.servlet.http.HttpSession;


import com.example.demo.application.classBDD.Agent;
import com.example.demo.application.classBDD.Casting;
import com.example.demo.application.classBDD.acteur;
import com.example.demo.application.classBDD.directeurCasting;
import com.example.demo.application.service.CastingService;
import com.example.demo.application.service.acteurService;
import com.example.demo.application.service.agentService;
import com.example.demo.application.service.reponseCastingService;


@Controller
@RequestMapping("/application")
public class applicationController {
	
	@Autowired
	private directeurCastingService DCService;

	@Autowired
	private agentService agentService;
	
	@Autowired
	private acteurService acteurService;
	
	@Autowired
	private CastingService castingService;
	
	@Autowired
	private reponseCastingService reponseCastingService;
	
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
	
	@PostMapping("/inscriptionActeur")
	public String inscriptionActeur(@RequestParam String nom,@RequestParam String prenom,@RequestParam String mail,@RequestParam String tel,@RequestParam String mdp,@RequestParam Long age) {
		acteurService.ajouterActeur(nom, prenom, mail, mdp, tel,age);
		return "/application/home";
	}
	
	@PostMapping("/connexion")
	public String connexion(@RequestParam String role,@RequestParam String mail,@RequestParam String mdp,HttpSession session) {
		if(role.equals("dc")) {
			directeurCasting verification = DCService.existant(mail, mdp);
			if(verification!=null) {
				session.setAttribute("nom",verification.getNom());
				session.setAttribute("prenom",verification.getPrenom());
				session.setAttribute("mail",verification.getMail());
				return "application/accueilDC";
			}
			
		}
		
		if(role.equals("agent")) {
			Agent verification = agentService.existant(mail, mdp);
			if(verification!=null) {
				session.setAttribute("nom",verification.getNom());
				session.setAttribute("prenom",verification.getPrenom());
				session.setAttribute("mail",verification.getMail());
				return "application/accueilAgent";
			}
		}
		
		if(role.equals("acteur")) {
			acteur verification = acteurService.existant(mail, mdp);
			if(verification!=null) {
				session.setAttribute("nom",verification.getNom());
				session.setAttribute("prenom",verification.getPrenom());
				session.setAttribute("mail",verification.getMail());
				session.setAttribute("age", verification.getAge());
				session.setAttribute("tel", verification.getTel());
				return "application/accueilActeur";
			}
		}
		
		return "/application/home";
	}
	
	@GetMapping("/deconnexion")
	public String deconnexionAgent(HttpSession session) {
		session.removeAttribute("email");
		session.removeAttribute("nom");
		session.removeAttribute("prenom");
		return "/application/home";
	}
	
	@GetMapping("/evenementDC")
	public String evenementDC(HttpSession session) {
		System.out.println(session.getAttribute("mail"));
		return "/application/evenementDC";
	}
	
	@GetMapping("/creationEvenementDC")
	public String creationEvenementDC(HttpSession session) {
		return "/application/creationEvenementDC";
	}
	
	@PostMapping("/creationRoleCasting")
	public String creationRoleCasting(@RequestParam String nomFilm,@RequestParam String role,@RequestParam String ageMin,@RequestParam String ageMax,@RequestParam String sexe, HttpSession session) {
		castingService.ajouterCasting(nomFilm, role, Integer.parseInt(ageMin), Integer.parseInt(ageMax), sexe,session.getAttribute("mail").toString());
		return "/application/evenementDC";
	} 
	
	@GetMapping("/myEvenementDC")
	public String myEvenementDC(HttpSession session, Model model) {
		
		List<Casting> castings = new ArrayList<>();
		castings = castingService.getCastingByCreateur( (String) session.getAttribute("mail"));
		model.addAttribute("castings",castings);
		return "/application/myEvenementDC";
	}

	@GetMapping("/informations")
	public String informations(HttpSession session) {
		return "/application/informations";
	}

	@GetMapping("/emploiDuTemps")
	public String emploiDuTemps(HttpSession session) {
		return "/application/emploiDuTemps";
	}
	
	@GetMapping("/annonces")
	public String annonces(HttpSession session, Model model) {
		
		List<Casting> castings = new ArrayList<>();
		castings = castingService.getAllCasting();
		model.addAttribute("castings",castings);
		
		return "/application/annonces";
	}
	
	@PostMapping("/repondreAnnonce")
	public String repondreAnnonce(HttpSession session,@RequestParam String film,@RequestParam String role,@RequestParam Long ageMin,@RequestParam Long ageMax,@RequestParam String sexe,@RequestParam Long idCasting) {
		session.setAttribute("film", film);
		session.setAttribute("role", role);
		session.setAttribute("idCasting", idCasting);
		return "/application/formulaireCasting";
	}
	
	@PostMapping("/validerInscriptionCasting")
	public String validerInscriptionCasting(@RequestParam String nom,@RequestParam String prenom,@RequestParam Long age,@RequestParam String tel,@RequestParam String mail,@RequestParam String photo,@RequestParam Long idCasting) {
		reponseCastingService.ajouterReponse(idCasting,nom, prenom,age,tel,photo);
		return "/application/annonces";
	}
	
	
	
}

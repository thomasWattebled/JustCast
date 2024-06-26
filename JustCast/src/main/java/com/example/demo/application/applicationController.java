package com.example.demo.application;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.application.service.directeurCastingService;


import jakarta.servlet.http.HttpSession;


import com.example.demo.application.classBDD.Agent;
import com.example.demo.application.classBDD.Casting;
import com.example.demo.application.classBDD.Evenement;
import com.example.demo.application.classBDD.acteur;
import com.example.demo.application.classBDD.directeurCasting;
import com.example.demo.application.classBDD.reponseCasting;
import com.example.demo.application.service.CastingService;
import com.example.demo.application.service.EvenementService;
import com.example.demo.application.service.acteurService;
import com.example.demo.application.service.agentService;
import com.example.demo.application.service.reponseCastingService;
import com.example.demo.application.service.lienAgentActeurService;


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
	
	@Autowired
	private lienAgentActeurService lienAgentActeurService;

	@Autowired
	private EvenementService evenementService;
	
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
				session.setAttribute("mdp",verification.getMdp());
				session.setAttribute("role","dc");
				session.setAttribute("prenom",verification.getPrenom());
				session.setAttribute("mail",verification.getMail());
				session.setAttribute("tel",verification.getTel());
				session.setAttribute("idDc",verification.getId());
				return "application/accueilDC";
			}
			
		}
		
		if(role.equals("agent")) {
			Agent verification = agentService.existant(mail, mdp);
			if(verification!=null) {
				session.setAttribute("nom",verification.getNom());
				session.setAttribute("role","agent");
				session.setAttribute("mdp",verification.getMdp());
				session.setAttribute("prenom",verification.getPrenom());
				session.setAttribute("mail",verification.getMail());
				session.setAttribute("tel",verification.getTel());
				session.setAttribute("idAgent", verification.getId());
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
				session.setAttribute("idActeur", verification.getId());
				session.setAttribute("mdp",verification.getMdp());
				session.setAttribute("role","acteur");
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
		session.removeAttribute("age");
		session.removeAttribute("tel");
		session.removeAttribute("idActeur");
		session.removeAttribute("idAgent");
		session.removeAttribute("idDc");
		return "/application/home";
	}
	
	@GetMapping("/evenementDC")
	public String evenementDC(HttpSession session) {
		System.out.println(session.getAttribute("mail"));
		return "/application/evenementDC";
	}
	
	@PostMapping("/evenementDC")
	public String evenementDCPost(HttpSession session) {
		System.out.println(session.getAttribute("mail"));
		return "/application/evenementDC";
	}
	
	@GetMapping("/creationEvenementDC")
	public String creationEvenementDC(HttpSession session) {
		return "/application/creationEvenementDC";
	}
	
	@PostMapping("/creationRoleCasting")
	public String creationRoleCasting(@RequestParam String nomFilm,@RequestParam String role,@RequestParam String ageMin,@RequestParam String ageMax,@RequestParam String sexe, HttpSession session) {
		//0 car casting ouvert
		castingService.ajouterCasting(nomFilm, role, Integer.parseInt(ageMin), Integer.parseInt(ageMax), sexe,session.getAttribute("mail").toString(),0);
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

	@GetMapping("/emploiDuTemps/{day}/{month}/{year}")
	public String emploiDuTempsSemaine(HttpSession session, Model model, @PathVariable("day") int day, @PathVariable("month") int month, @PathVariable("year") int year) {
		LocalDate date = LocalDate.of(year, month, day);

		int dayWeek = date.getDayOfWeek().getValue();
		int maxDayOfMonth = (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0) ? date.getMonth().maxLength() : date.getMonth().minLength();

		// Pour le bouton de la semaine suivante
		int dayNextWeek = day + 7;
		int monthNextWeek;
		int yearNextWeek;
		if (dayNextWeek > maxDayOfMonth) {
			dayNextWeek = dayNextWeek - maxDayOfMonth;
			if (month == 12) {
				monthNextWeek = 1;
				yearNextWeek = year + 1;
			} else {
				monthNextWeek = month + 1;
				yearNextWeek = year;
			}
			
		} else {
			monthNextWeek = month;
			yearNextWeek = year;
		}

		String semaineSuivante = new String("\"/application/emploiDuTemps/" + dayNextWeek + "/" + monthNextWeek + "/" + yearNextWeek + "\"");
		model.addAttribute("semaineSuivante", semaineSuivante);

		// Pour le bouton de la semaine précédente

		int dayPreviousWeek = day - 7;
		int monthPreviousWeek;
		int yearPreviousWeek;

		if (dayPreviousWeek < 1) {
			int maxDayPreviousMonth;
			if (month == 1) {
				monthPreviousWeek = 12;
				yearPreviousWeek = year - 1;
				maxDayPreviousMonth = (yearPreviousWeek % 400 == 0) || (yearPreviousWeek % 4 == 0 && yearPreviousWeek % 100 != 0) ? Month.of(monthPreviousWeek).maxLength() : Month.of(monthPreviousWeek).minLength();
				dayPreviousWeek = maxDayPreviousMonth + dayPreviousWeek;
			} else {
				monthPreviousWeek = month - 1;
				yearPreviousWeek = year;
				maxDayPreviousMonth = (yearPreviousWeek % 400 == 0) || (yearPreviousWeek % 4 == 0 && yearPreviousWeek % 100 != 0) ? Month.of(monthPreviousWeek).maxLength() : Month.of(monthPreviousWeek).minLength();
				dayPreviousWeek = maxDayPreviousMonth + dayPreviousWeek;
			}
		} else {
			monthPreviousWeek = month;
			yearPreviousWeek = year;
		}

		String semainePrecedente = new String("\"/application/emploiDuTemps/" + dayPreviousWeek + "/" + monthPreviousWeek + "/" + yearPreviousWeek + "\"");
		model.addAttribute("semainePrecedente", semainePrecedente);

		// On met dans le modèle les numéros du mois des jours de la semaine, si jour hors du mois actuel alors -1


		for (int i = 1; i <= 7; i++) {
			int dayValue = day - dayWeek + i;
			int otherMonthValue = month;
			int otherYearValue = year;
			int maxDayOtherMonth;
			String dayName = "";
			int userType = -1;
			long userId= -1;

			if (dayValue < 1) {
				if (month == 1) {
					otherMonthValue = 12;
					otherYearValue = year;
				} else {
					otherMonthValue = month - 1;
				}
				maxDayOtherMonth = (otherYearValue % 400 == 0) || (otherYearValue % 4 == 0 && otherYearValue % 100 != 0) ? Month.of(otherMonthValue).maxLength() : Month.of(otherMonthValue).minLength();
				dayValue = maxDayOtherMonth + dayValue;
			} else if (dayValue > maxDayOfMonth) {
				if (month == 12) {
					otherMonthValue = 1;
					otherYearValue = year + 1;
				} else {
					otherMonthValue = month + 1;
				}
				maxDayOtherMonth = (otherYearValue % 400 == 0) || (otherYearValue % 4 == 0 && otherYearValue % 100 != 0) ? Month.of(otherMonthValue).maxLength() : Month.of(otherMonthValue).minLength();
				dayValue = maxDayOtherMonth - dayValue;
			}

			switch (i) {
				case 1 :
					dayName = "mondayValue";
					break;
				case 2 :
					dayName = "tuesdayValue";
					break;
				case 3 :
					dayName = "wednesdayValue";
					break;
				case 4 :
					dayName = "thursdayValue";
					break;
				case 5 :
					dayName = "fridayValue";
					break;
				case 6 :
					dayName = "saturdayValue";
					break;
				case 7 :
					dayName = "sundayValue";
					break;
			}

			switch ((String)session.getAttribute("role")) {
				case "agent" :
					userType = 2;
					userId = (long) session.getAttribute("idAgent");
					break;
				case "acteur" :
					userType = 1;
					userId = (long) session.getAttribute("idActeur");
					break;
				case "dc" :
					userType = 0;
					userId = (long) session.getAttribute("idDc");
					break;
			}

			List<Evenement> dayEventsList = evenementService.getEvenementByUserIdAndUserTypeIdAndYearAndMonthAndDay(userId, userType, otherYearValue, otherMonthValue, dayValue);

			for (Evenement tmpEvent : dayEventsList) {
				model.addAttribute(dayName + String.valueOf(tmpEvent.getHourAtt()), tmpEvent.getLibelle());
			}

			model.addAttribute(dayName, otherMonthValue != month ? '(' + Integer.toString(dayValue) + ')' : dayValue);
		}

		model.addAttribute("month", month);
		model.addAttribute("year", year);

		return "/application/emploiDuTemps";
	}

	@GetMapping("/emploiDuTemps")
	public String emploiDuTemps(HttpSession session, Model model) {
		LocalDate date = LocalDate.now();

		int day = date.getDayOfMonth();
		int month = date.getMonthValue();
		int year = date.getYear();
		int dayWeek = date.getDayOfWeek().getValue();
		int maxDayOfMonth = (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0) ? date.getMonth().maxLength() : date.getMonth().minLength();

		// Pour le bouton de la semaine suivante
		int dayNextWeek = day + 7;
		int monthNextWeek;
		int yearNextWeek;
		if (dayNextWeek > maxDayOfMonth) {
			System.out.println("passe");
			dayNextWeek = dayNextWeek - maxDayOfMonth;
			if (month == 12) {
				monthNextWeek = 1;
				yearNextWeek = year + 1;
			} else {
				monthNextWeek = month + 1;
				yearNextWeek = year;
			}
			
		} else {
			monthNextWeek = month;
			yearNextWeek = year;
		}

		String semaineSuivante = new String("\"/application/emploiDuTemps/" + dayNextWeek + "/" + monthNextWeek + "/" + yearNextWeek + "\"");
		model.addAttribute("semaineSuivante", semaineSuivante);

		// Pour le bouton de la semaine précédente

		int dayPreviousWeek = day - 7;
		int monthPreviousWeek;
		int yearPreviousWeek;

		if (dayPreviousWeek < 1) {
			int maxDayPreviousMonth;
			if (month == 1) {
				monthPreviousWeek = 12;
				yearPreviousWeek = year - 1;
				maxDayPreviousMonth = (yearPreviousWeek % 400 == 0) || (yearPreviousWeek % 4 == 0 && yearPreviousWeek % 100 != 0) ? Month.of(monthPreviousWeek).maxLength() : Month.of(monthPreviousWeek).minLength();
				dayPreviousWeek = maxDayPreviousMonth + dayPreviousWeek;
			} else {
				monthPreviousWeek = month - 1;
				yearPreviousWeek = year;
				maxDayPreviousMonth = (yearPreviousWeek % 400 == 0) || (yearPreviousWeek % 4 == 0 && yearPreviousWeek % 100 != 0) ? Month.of(monthPreviousWeek).maxLength() : Month.of(monthPreviousWeek).minLength();
				dayPreviousWeek = maxDayPreviousMonth + dayPreviousWeek;
			}
		} else {
			monthPreviousWeek = month;
			yearPreviousWeek = year;
		}

		String semainePrecedente = new String("\"/application/emploiDuTemps/" + dayPreviousWeek + "/" + monthPreviousWeek + "/" + yearPreviousWeek + "\"");
		model.addAttribute("semainePrecedente", semainePrecedente);

		// On met dans le modèle les numéros du mois des jours de la semaine, si jour hors du mois actuel alors -1

		for (int i = 1; i <= 7; i++) {
			int dayValue = day - dayWeek + i;
			int otherMonthValue = month;
			int otherYearValue = year;
			int maxDayOtherMonth;
			String dayName = "";
			int userType = -1;
			long userId= -1;

			if (dayValue < 1) {
				if (month == 1) {
					otherMonthValue = 12;
					otherYearValue = year;
				} else {
					otherMonthValue = month - 1;
				}
				maxDayOtherMonth = (otherYearValue % 400 == 0) || (otherYearValue % 4 == 0 && otherYearValue % 100 != 0) ? Month.of(otherMonthValue).maxLength() : Month.of(otherMonthValue).minLength();
				dayValue = maxDayOtherMonth + dayValue;
			} else if (dayValue > maxDayOfMonth) {
				if (month == 12) {
					otherMonthValue = 1;
					otherYearValue = year + 1;
				} else {
					otherMonthValue = month + 1;
				}
				maxDayOtherMonth = (otherYearValue % 400 == 0) || (otherYearValue % 4 == 0 && otherYearValue % 100 != 0) ? Month.of(otherMonthValue).maxLength() : Month.of(otherMonthValue).minLength();
				dayValue = maxDayOtherMonth - dayValue;
			}

			switch (i) {
				case 1 :
					dayName = "mondayValue";
					break;
				case 2 :
					dayName = "tuesdayValue";
					break;
				case 3 :
					dayName = "wednesdayValue";
					break;
				case 4 :
					dayName = "thursdayValue";
					break;
				case 5 :
					dayName = "fridayValue";
					break;
				case 6 :
					dayName = "saturdayValue";
					break;
				case 7 :
					dayName = "sundayValue";
					break;
			}

			switch ((String)session.getAttribute("role")) {
				case "agent" :
					userType = 2;
					userId = (long) session.getAttribute("idAgent");
					break;
				case "acteur" :
					userType = 1;
					userId = (long) session.getAttribute("idActeur");
					break;
				case "dc" :
					userType = 0;
					userId = (long) session.getAttribute("idDc");
					break;
			}

			List<Evenement> dayEventsList = evenementService.getEvenementByUserIdAndUserTypeIdAndYearAndMonthAndDay(userId, userType, otherYearValue, otherMonthValue, dayValue);

			for (Evenement tmpEvent : dayEventsList) {
				model.addAttribute(dayName + String.valueOf(tmpEvent.getHourAtt()), tmpEvent.getLibelle());
			}

			model.addAttribute(dayName, otherMonthValue != month ? '(' + Integer.toString(dayValue) + ')' : dayValue);
		}

		model.addAttribute("month", month);
		model.addAttribute("year", year);

		return "/application/emploiDuTemps";
	}
	
	@GetMapping("/annonces")
	public String annonces(HttpSession session, Model model) {
		
		List<Casting> castings = new ArrayList<>();
		castings = castingService.getCastingByCloture(0);
		model.addAttribute("castings",castings);
		
		return "/application/annonces";
	}
	
	@PostMapping("/repondreAnnonce")
	public String repondreAnnonce(HttpSession session,@RequestParam String film,@RequestParam String role,@RequestParam Long ageMin,@RequestParam Long ageMax,@RequestParam String sexe,@RequestParam Long idCasting) {
		System.out.println(film);
		session.setAttribute("film", film);
		session.setAttribute("role", role);
		session.setAttribute("idCasting", idCasting);
		return "/application/formulaireCasting";
	}
	
	@PostMapping("/validerInscriptionCasting")
	public String validerInscriptionCasting(HttpSession session,@RequestParam String nom,@RequestParam String prenom,@RequestParam Long age,@RequestParam String tel,@RequestParam String mail,@RequestParam String photo,@RequestParam Long idCasting,@RequestParam Long idActeur) {
		String film = (String) session.getAttribute("film");
		String role = (String) session.getAttribute("role");
		reponseCastingService.ajouterReponse(idCasting,idActeur,nom, prenom,age,tel,photo,film,role);
		session.removeAttribute("film");
		session.removeAttribute("role");
		session.removeAttribute("idCasting");
		return "redirect:/application/annonces";
	}
	
	@PostMapping("/modifierCasting")
	public String modifierCasting(@RequestParam Long idCasting,@RequestParam String nomFilm,@RequestParam String role,@RequestParam String ageMin,@RequestParam String ageMax,@RequestParam String sexe,@RequestParam Integer cloture, HttpSession session,Model model) {
			
		model.addAttribute("modNomFilm", nomFilm);
		model.addAttribute("modRole", role);
		model.addAttribute("modIdCasting", idCasting);
		model.addAttribute("modAgeMin", ageMin);
		model.addAttribute("modAgeMax", ageMax);
		model.addAttribute("modSexe", sexe);
		model.addAttribute("modCloture", cloture);
		return "/application/modifierEvenementDC";
	} 
	
	@PostMapping("/modifierRoleCasting")
	public String modifierRoleCasting(@RequestParam Long idCasting,@RequestParam String nomFilm,@RequestParam String role,@RequestParam String ageMin,@RequestParam String ageMax,@RequestParam String sexe,@RequestParam String cloture, HttpSession session) {
			System.out.println(cloture);
			castingService.updateCasting(idCasting,nomFilm, role, Integer.parseInt(ageMin), Integer.parseInt(ageMax), sexe,session.getAttribute("mail").toString(),Integer.parseInt(cloture));
		
			
		
		
		//session.removeAttribute("modNomFilm");
		//session.removeAttribute("modRole");
		//session.removeAttribute("modIdCasting");
		//session.removeAttribute("modAgeMin");
		//session.removeAttribute("modAgeMax");
		//session.removeAttribute("modSexe");
		//session.removeAttribute("modCloture");
		return "/application/evenementDC";
	} 
	
	@GetMapping("/postulationsActeur")
	public String postulationsActeur(HttpSession session, Model model) {
		
		List<reponseCasting> castings = new ArrayList<>();
		castings = reponseCastingService.getCastingByIdActeur((Long) session.getAttribute("idActeur"));
		model.addAttribute("castings",castings);
		
		return "/application/postulationsActeur";
	}
	
	@GetMapping("/detailsCasting/{id}")
    public String getDetailsCasting(@PathVariable String id, Model model) {
		Long longId= Long.parseLong(id);
		System.out.println("je suis ici "+ id);
		List<reponseCasting> reponsecastings = reponseCastingService.getCastingByIdCasting(longId);
		
		model.addAttribute("reponsecastings",reponsecastings);
            return "/application/detailsCasting";
        }
	
	@GetMapping("/gererActeur")
	public String gererActeur(HttpSession session) {
		return "/application/gererActeur";
	}

	@GetMapping("/trouverActeur")
	public String trouverActeur(Model model,HttpSession session) {
		/*List<acteur> acteurs = acteurService.getAllAuteurs();*/
		List<Long> ids = lienAgentActeurService.getIdActeurByAgent((Long) session.getAttribute("idAgent"));
		List<acteur> acteurs = acteurService.getActeursNotInIds(ids);
		model.addAttribute("acteurs",acteurs);
		return "/application/trouverActeur";
	}
 
	@PostMapping("/addLienAgentActeur")
	public String addLienAgentActeur(@RequestParam Long idActeur,HttpSession session) {
		lienAgentActeurService.ajouterLien((Long) session.getAttribute("idAgent"), idActeur);
		return "/application/gererActeur";
	}
	
	@GetMapping("/creerEvenement")
	public String creerEvenement(HttpSession session) {
		//lienAgentActeurService.ajouterLien((Long) session.getAttribute("idAgent"), idActeur);
		return "/application/creerEvenement";
	}
	
	@PostMapping("/creationEvenement")
	public String creationEvenement(@RequestParam String libelle,@RequestParam String detail,@RequestParam int heure,@RequestParam String date, HttpSession session) {
		System.out.println(libelle);
		System.out.println(detail);
		System.out.println(date);
		System.out.println(date.substring(0, 4));
		System.out.println(date.substring(5, 7));
		System.out.println(date.substring(8));
		
		long annee = Long.parseLong(date.substring(0, 4));
		long mois = Long.parseLong(date.substring(5, 7));
		long jour = Long.parseLong(date.substring(8));
		int userType = -1;
		long userId = -1;
		switch ((String)session.getAttribute("role")) {
		case "agent" :
			userType = 2;
			userId = (long) session.getAttribute("idAgent");
			
			break;
		case "acteur" :
			userType = 1;
			userId = (long) session.getAttribute("idActeur");
			break;
		case "dc" :
			userType = 0;
			userId = (long) session.getAttribute("idDc");
			break;
	}
		evenementService.ajouterEvenement(userId, userType, annee, mois, jour, heure, libelle, detail);
		if(userType==(long) 2) {
			return "/application/accueilAgent";
		}
		else if (userType==(long) 1) {
			return "/application/accueilActeur";
		}
		return "/application/accueilDC";
	}
	
	
	@GetMapping("/voirActeur")
	public String voirActeur(Model model,HttpSession session) {
		/*List<acteur> acteurs = acteurService.getAllAuteurs();*/
		List<Long> ids = lienAgentActeurService.getIdActeurByAgent((Long) session.getAttribute("idAgent"));
		System.out.println(ids);
		List<acteur> acteurs = acteurService.getActeursById(ids);
		model.addAttribute("acteurs",acteurs);
		return "/application/voirActeur";
	}
	
}




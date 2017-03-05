package com.banque2.controleur;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.banque2.services.ServiceAuthentification;

@Controller
public class GestionnaireRacine {

	final String regex = "[a-zA-Z0-9^.*\\W]{8,}";
	@Autowired
	private ServiceAuthentification serviceSecurite;


		@RequestMapping(value = {"/portail","/"}, method = RequestMethod.GET)
		public String getloginPage() {
			return "portail";
		}

		@RequestMapping(value="/deconnexion", method = RequestMethod.GET)
		public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        if (auth != null){
		            new SecurityContextLogoutHandler().logout(request, response, auth);
		        }
		        return "redirect:/portail?logout";
		}
		
		@RequestMapping(value = {"/mdpOublie"}, method = RequestMethod.GET)
		public String getmdpOublie() {
			
			return "/mdpOublie";
		}
		
		@RequestMapping(value = {"/reinitialiserMdp"}, method = RequestMethod.GET)
		public String getReinitialiserMdp() {
			
			return "/reinitialiserMdp";
		}
		
		@RequestMapping(value = {"/mdpOublie"}, method = RequestMethod.POST)
		public ModelAndView postmdpOublie(
				@RequestParam("idt") String identifiant,
				@RequestParam("courriel") String courriel) {
			ModelAndView vueModele = new ModelAndView();
			
			if(this.serviceSecurite.checkResetMDP(identifiant, courriel)){
				
				serviceSecurite.setRecoveryPin(identifiant);
				vueModele.setViewName("/reinitialiserMdp");
				vueModele.addObject("succes", true);
				vueModele.addObject("idUser", identifiant);
				vueModele.addObject("description", "Un PIN de securité vous a été envoyé par courriel à l'adresse : "+courriel);
			}else{
				vueModele.setViewName("/mdpOublie");
				vueModele.addObject("succes", false);
				vueModele.addObject("description", "Echec de recovery de mot de passe.");
			}
			
			return vueModele;
		}
		
		@RequestMapping(value = {"/reinitialiserMdp"}, method = RequestMethod.POST)
		public ModelAndView postReinitialiserMdp(
				@RequestParam("idt") String identifiant,
				@RequestParam("pin") int pin,
				@RequestParam("pass1") String pass1,
				@RequestParam("pass2") String pass2) {
			ModelAndView vueModele = new ModelAndView();
			
			if(pass1.equals(pass2) && pass1.matches(regex) && pin >0){
				if(serviceSecurite.resetMDP(identifiant, pin, pass1)){
					vueModele.setViewName("/portail");
				}else{
					vueModele.setViewName("/reinitialiserMdp");
					vueModele.addObject("succes", false);
					vueModele.addObject("description", "Le PIN de reinitialisation n'est pas valide.");
					vueModele.addObject("pass1", pass1);
					vueModele.addObject("pass2", pass2);
					vueModele.addObject("idUser",identifiant);
				}
			}
			else{
				vueModele.setViewName("/reinitialiserMdp");
				vueModele.addObject("succes", false);
				vueModele.addObject("pin", pin);
				vueModele.addObject("description", "Les mots de passe ne sont pas identiques ou ne respectent pas le niveau de securite.");
				vueModele.addObject("idUser",identifiant);
			}			
			return vueModele;
		}		
		

		@RequestMapping(value = {"/denied"}, method = RequestMethod.GET)
		public String getLogAdmin() {
			return "/denied";
		}
}

package com.banque2.controleur;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.banque2.modele.PojoAdministrateur;
import com.banque2.services.ServiceDaoAdministrateur;
import com.banque2.services.ServiceAuthentification;

@Controller
public class GestionnaireGet {
		@Autowired
		private ServiceDaoAdministrateur serviceDaoAdministrateur;
	
		
		private static final Logger log = Logger.getLogger(GestionnaireGet.class);
		
		@RequestMapping(value = {"/portail","/"}, method = RequestMethod.GET)
		public String getloginPage() {
			PojoAdministrateur administrateur = serviceDaoAdministrateur.getAdministrateur(1);
			//System.out.println("Administrateur recu : "+administrateur.toString());
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
		
		@RequestMapping(value = {"/denied"}, method = RequestMethod.GET)
		public String getLogAdmin() {
			return "/denied";
		}
}

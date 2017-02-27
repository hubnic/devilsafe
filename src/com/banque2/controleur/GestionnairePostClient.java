package com.banque2.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.banque2.services.ServiceAuthentification;
import com.banque2.services.ServiceDaoClient;

@Controller
public class GestionnairePostClient {

	@Autowired
	private ServiceDaoClient serviceDaoClient;
	
	@Autowired
	private ServiceAuthentification serviceSecurite;
	
	private final String regex = "[a-zA-Z0-9^.*\\W]{8,}";
	
	//ADMINISTRATEUR
	@RequestMapping(value = {"/changePwdClient"}, method = RequestMethod.POST)
	public ModelAndView postChangePassAdmin(	
			@RequestParam("oldPass") String oldPass,
			@RequestParam("newPass") String newPass1,
			@RequestParam("newPass2") String newPass2){
		
		
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		ModelAndView vueModele = new ModelAndView();
		vueModele.setViewName("/client/client_profil");
		
		if(newPass1.equals(newPass2)){
			if(serviceSecurite.authentificationClient("c"+id, oldPass)){
				if(newPass1.matches(regex)){
					serviceSecurite.updateClientPass(id, newPass1);
					vueModele.addObject("succes", true);
					vueModele.addObject("description", "Votre mot de passe a ete modifie avec succes.");
					System.out.println("Mise a jour du mdp OK");
				}
				else{
					vueModele.addObject("succes", false);
					vueModele.addObject("description", "Le nouveau mot de passe ne respect pas le REGEX.");
				}
			
			}
			else{
				vueModele.addObject("succes", false);
				vueModele.addObject("description", "L'ancien mot de passe est errone.");
			}
		}else{
			vueModele.addObject("succes", false);
			vueModele.addObject("description", "Les mots de passe ne concordent pas.");
		}
		
		vueModele.addObject("client", serviceDaoClient.getProfilClient(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));
		return vueModele;
	}
	
}

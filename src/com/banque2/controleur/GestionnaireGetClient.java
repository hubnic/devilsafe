package com.banque2.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.banque2.services.ServiceDaoClient;

@Controller
public class GestionnaireGetClient {
	
	@Autowired
	private ServiceDaoClient serviceDaoClient;
	
	

	@RequestMapping(value = {"/apercu"}, method = RequestMethod.GET)
	public String getApercu(ModelMap listeElement) {	
			listeElement.addAttribute("comptes",serviceDaoClient.getAllComptesClient(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));
		return "/client/client_apercu";
	}
	
	
	@RequestMapping(value = {"/transfertIn"}, method = RequestMethod.GET)
	public String getTransfertIn(ModelMap listeElement) {
		listeElement.addAttribute("comptes", serviceDaoClient.getAllComptesClientForTransfert(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));	
		return "/client/client_transfertIn";
	}
	
	@RequestMapping(value = {"/transfertOut"}, method = RequestMethod.GET)
	public String getTransfertOut(ModelMap listeElement) {
		listeElement.addAttribute("comptes", serviceDaoClient.getAllComptesClientForTransfert(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));		
		return "/client/client_transfertOut";
	}
	@RequestMapping(value = {"/profil"}, method = RequestMethod.GET)
	public String getProfil(ModelMap listeElement) {
		listeElement.addAttribute("client",serviceDaoClient.getProfilClient(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));
		return "/client/client_profil";
	}
	
	@RequestMapping(value = {"/credit"}, method = RequestMethod.GET)
	public String getCredit(ModelMap listeElement) {
		listeElement.addAttribute("compteCredit", serviceDaoClient.getCompteCredit(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));
		listeElement.addAttribute("comptes", serviceDaoClient.getAllComptesClientForTransfert(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));	
		return "/client/client_credit";
	}
	
}

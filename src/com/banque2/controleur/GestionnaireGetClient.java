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
		listeElement.addAttribute("username", "client");
		listeElement.addAttribute("account", "Debit");
		listeElement.addAttribute("accountID", "066-123456");
		listeElement.addAttribute("accountBalance", "2599");
		listeElement.addAttribute("creditAccount", "Credit");
		listeElement.addAttribute("creditAccountId", "066-3223423423");
		listeElement.addAttribute("creditBalance", "256");
		return "/client/client_transfertIn";
	}
	
	@RequestMapping(value = {"/transfertOut"}, method = RequestMethod.GET)
	public String getTransfertOut(ModelMap listeElement) {
		listeElement.addAttribute("username", "client");
		listeElement.addAttribute("account", "Debit");
		listeElement.addAttribute("accountId", "066-123456");
		listeElement.addAttribute("accountBalance", "2599");
		listeElement.addAttribute("creditAccount", "Credit");
		listeElement.addAttribute("creditAccountID", "066-3223423423");
		listeElement.addAttribute("creditBalance", "256");
		return "/client/client_transfertOut";
	}
	@RequestMapping(value = {"/profil"}, method = RequestMethod.GET)
	public String getProfil(ModelMap listeElement) {
		listeElement.addAttribute("userName", "Dupont");
		listeElement.addAttribute("userFirstName", "Jean");
		listeElement.addAttribute("courriel", "jean.dupont@jdupe.com");
		listeElement.addAttribute("tel", "514-555-7777");
		return "/client/client_profil";
	}
	
	@RequestMapping(value = {"/credit"}, method = RequestMethod.GET)
	public String getCredit(ModelMap listeElement) {
		listeElement.addAttribute("accountName", "Debit");
		listeElement.addAttribute("accountId", "066-123456");
		listeElement.addAttribute("montant", "256");
		return "/client/client_credit";
	}
}

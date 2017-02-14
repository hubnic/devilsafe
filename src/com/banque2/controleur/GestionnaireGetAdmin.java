package com.banque2.controleur;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GestionnaireGetAdmin {

	//ADMINISTRATEUR
	@RequestMapping(value = {"/secureAdmin"}, method = RequestMethod.GET)
	public String getLogAdmin(ModelMap listeElement) {
		listeElement.addAttribute("username", "admin");
		return "/admin/admin_auth";
	}
	
	
	@RequestMapping(value = {"/newAdmin"}, method = RequestMethod.GET)
	public String getAdminCreerCompte() {
		//listeElement.addAttribute("username", "admin");
		return "/admin/admin_addAdmin";
	}
	
	
	@RequestMapping(value = {"/showAccount"}, method = RequestMethod.GET)
	public String getAdminshowAccount(ModelMap listeElement) {
		listeElement.addAttribute("username", "admin");
		return "/admin/admin_showAccount";
	}
	
	@RequestMapping(value = {"/homeAdmin"}, method = RequestMethod.GET)
	public String getAdminHome(ModelMap listeElement) {
		listeElement.addAttribute("username", "admin");
		return "/admin/admin_home";
	}
			
	@RequestMapping(value = {"/showLog"}, method = RequestMethod.GET)
	public String getAdminLog(ModelMap listeElement) {
		listeElement.addAttribute("username", "admin");
		return "/admin/admin_log";
	}
	
	@RequestMapping(value = {"/newClient"}, method = RequestMethod.GET)
	public String getAdminAddPers(ModelMap listeElement) {
		listeElement.addAttribute("username", "admin");
		return "/admin/admin_newClient";
	}
	
	@RequestMapping(value = {"/showAllClient"}, method = RequestMethod.GET)
	public String getAdminAllClient(ModelMap listeElement) {
		listeElement.addAttribute("username", "admin");
		return "/admin/admin_showAllClient";
	}
	
	
	@RequestMapping(value = {"/adminProfil"}, method = RequestMethod.GET)
	public String getAdminAddProfil(ModelMap listeElement) {
		listeElement.addAttribute("username", "admin");
		return "/admin/admin_adminProfil";
	}
	
}

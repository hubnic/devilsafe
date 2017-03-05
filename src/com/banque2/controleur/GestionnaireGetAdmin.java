package com.banque2.controleur;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.banque2.modele.PojoClient;
import com.banque2.services.ServiceDaoAdministrateur;

@Controller
public class GestionnaireGetAdmin {

	@Autowired
	private ServiceDaoAdministrateur serviceDaoAdministrateur;
	
	@RequestMapping(value = {"/secureAdmin"}, method = RequestMethod.GET)
	public String getLogAdmin(ModelMap listeElement) {
		listeElement.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
		return "/admin/admin_auth";
	}
	
	@RequestMapping(value = {"/homeAdmin"}, method = RequestMethod.GET)
	public String getAdminHome(ModelMap listeElement) {
	
		listeElement.addAttribute("username", "admin");
		return "/admin/admin_home";
	}
	
	
	@RequestMapping(value = {"/newAdmin"}, method = RequestMethod.GET)
	public String getAdminCreerCompte() {
		return "/admin/admin_addAdmin";
	}
	
	@RequestMapping(value = {"/showAllClient"}, method = RequestMethod.GET)
	public String getAdminAllClient( 
			ModelMap listeElement) {
		listeElement.addAttribute("clients", (ArrayList<PojoClient>) serviceDaoAdministrateur.getAllClient());
		return "/admin/admin_showAllClient";
	}
	
	@RequestMapping(value = {"/showAccount"}, method = RequestMethod.GET)
	public String getAdminshowAccount(
			HttpServletRequest request,
			ModelMap listeElement) {
		String id = request.getParameter("id");
		System.out.print(id);
		listeElement.addAttribute("username", "admin");
		return "/admin/admin_showAccount";
	}
	
	
	@RequestMapping(value = {"/newClient"}, method = RequestMethod.GET)
	public String getAdminAddPers(ModelMap listeElement) {
		listeElement.addAttribute("username", "admin");
		return "/admin/admin_newClient";
	}
	
	@RequestMapping(value = {"/adminProfil"}, method = RequestMethod.GET)
	public String getAdminAddProfil(ModelMap listeElement) {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		listeElement.addAttribute("admin", serviceDaoAdministrateur.getProfil(SecurityContextHolder.getContext().getAuthentication().getName()));
		return "/admin/admin_adminProfil";
	}
	
	
	
	@RequestMapping(value = {"/addAccountClient"}, method = RequestMethod.GET)
	public String getAdminAddAccountClient(ModelMap listeElement) {
		listeElement.addAttribute("clients", (ArrayList<PojoClient>) serviceDaoAdministrateur.getAllClient());
		return "/admin/admin_showAllClient";
	}
}

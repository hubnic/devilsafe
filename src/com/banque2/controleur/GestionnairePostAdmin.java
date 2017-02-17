package com.banque2.controleur;

import java.sql.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.banque2.modele.PojoAdministrateur;
import com.banque2.modele.PojoClient;
import com.banque2.services.ServiceDaoAdministrateur;
import com.banque2.services.ServiceAuthentification;

@Controller
public class GestionnairePostAdmin {

	@Autowired
	private ServiceDaoAdministrateur serviceDaoAdministrateur;
	
	@Autowired
	private ServiceAuthentification serviceSecurite;
	
	private String explication;
	
	//ADMINISTRATEUR
		@RequestMapping(value = {"/secureAdmin"}, method = RequestMethod.POST)
		public ModelAndView postSecureLogin(	
				@RequestParam("idt") String id, 
				@RequestParam("secureKey") int secureKey){

			System.out.println("idt : " +id + " SecureKey : " + secureKey );
			ModelAndView vueModele;
			
			// Proceder a la validation du pin de securite
			boolean validation = true;
			
			if(validation){
				vueModele = new ModelAndView();
				vueModele.setViewName("/admin/admin_home");
				vueModele.addObject("succes", "TRUE");
				
			}else {
				vueModele = new ModelAndView();
				vueModele.setViewName("/secureAdmin");
				vueModele.addObject("echec", "FALSE");
			}
			
			return vueModele;
		}
	
	
	//ADMINISTRATEUR
	@RequestMapping(value = {"/addAdmin"}, method = RequestMethod.POST)
	public ModelAndView postAddAdmin(	
			@RequestParam("nom") String nom, 
			@RequestParam("prenom") String prenom,
			@RequestParam("role") String role,
			@RequestParam("pass1") String pass1,
			@RequestParam("pass2") String pass2){

		System.out.println(nom +" "+ prenom +" "+role +" "+pass1 +" "+pass2);
		ModelAndView vueModele;
		
		vueModele = new ModelAndView();
		vueModele.setViewName("/admin/admin_addAdmin");
		
		if(valideNewAmin(nom,prenom,role,pass1,pass2)){
			PojoAdministrateur newAdmin = new PojoAdministrateur();
			newAdmin.setNom(nom);
			newAdmin.setPrenom(prenom);
			newAdmin.setMdp(serviceSecurite.hashMDP(pass1));
			newAdmin.setKey(new Random().nextInt(9999));
			
			if(serviceDaoAdministrateur.createAdmin(newAdmin)){
				vueModele.addObject("succes", true);
				vueModele.addObject("description", "Le compte : " +newAdmin.getNom() +" a ete crée avec succes");
			}else {
				vueModele.addObject("succes", false);
				vueModele.addObject("description", "Echec lors de l'exécution de la requête");
			}
			
		}else {
			vueModele.addObject("succes", false);
			vueModele.addObject("description", "Echec lors de la création du compte administrateur, "
					+ "les mots de passe ne concordent pas");
		}
		
		return vueModele;
	}
	
	
	
	//ADMINISTRATEUR
		@RequestMapping(value = {"/newClient"}, method = RequestMethod.POST)
		public ModelAndView posNewClient(	
				@RequestParam("nom") String nom, 
				@RequestParam("prenom") String prenom,
				@RequestParam("date") String date,
				@RequestParam("telephone") String telephone,
				@RequestParam("adresse") String adresse,
				@RequestParam("courriel") String courriel,
				@RequestParam("pass1") String pass1,
				@RequestParam("pass2") String pass2){

			System.out.println(nom +" "+ prenom +" "+date +" "+pass1 +" "+pass2);
			ModelAndView vueModele;
			
			vueModele = new ModelAndView();
			
			
			if(valideNewUser()){
				
				PojoClient newClient = new PojoClient();
				newClient.setNom(nom);
				newClient.setPrenom(prenom);
				newClient.setDateNaissance(date.toString());
				newClient.setTelephone(telephone);
				newClient.setAdresse(adresse);
				newClient.setCourriel(courriel);
				newClient.setMdp(serviceSecurite.hashMDP(pass1));
				
				if(serviceDaoAdministrateur.createClient(newClient)){
					vueModele.setViewName("/admin/admin_showAccount");
					vueModele.addObject("succes", true);
					vueModele.addObject("description", "Le Client : " + newClient.getNom() +" a ete crée avec succes");
				}else {
					vueModele.setViewName("/admin/admin_newClient");
					vueModele.addObject("succes", false);
					vueModele.addObject("client",newClient);
					vueModele.addObject("description", "Echec lors de l'exécution de la requête");
				}
				
			}else {
				vueModele.setViewName("/admin/admin_newClient");
				vueModele.addObject("succes", false);
				vueModele.addObject("description", "Echec lors de la création du compte administrateur, "
						+ "les mots de passe ne concordent pas");
			}
			
			return vueModele;
		}
		
		private boolean valideNewAmin(String nom, String prenom, String role, String pass1, String pass2){
			
			if(nom!=null && prenom != null && role != null && pass1 != null && pass2 != null && pass1.equals(pass2)){
				return true;
			}else{
				return false;
			}
			
		}
		private boolean valideNewUser(){
			
			return true;
		}
}

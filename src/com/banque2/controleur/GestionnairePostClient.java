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
	
	//ADMINISTRATEUR
	@RequestMapping(value = {"/transfertIn"}, method = RequestMethod.POST)
	public ModelAndView postTransfertIn(	
			@RequestParam("compteOut") String compteEmetteur,
			@RequestParam("compteIn") String compteReceveur,
			@RequestParam("montant") String montant){

		
		String[] cEmetteur = compteEmetteur.split(" ");
		String[] cReveveur = compteReceveur.split(" ");
		
		
		ModelAndView vueModele = new ModelAndView();
		vueModele.setViewName("/client/client_transfertIn");
		
		if(valideCompteifCredit(cEmetteur)){
			if(valideMMCompte(cEmetteur,cReveveur) ){
				if(valideSoustraction(cEmetteur,montant)){
					System.out.println("Compte emetteur : "+cEmetteur[0]  + " " +cEmetteur[1] + " " +cEmetteur[2]+ " " +cEmetteur[3] + " " +cEmetteur[4]);
					System.out.println("Compte receveur : "+ " " +cReveveur[0] + " " +cReveveur[1]+ " " +cReveveur[2] + " " +cReveveur[3] + " " +cReveveur[4]);
					int transaction = serviceDaoClient.createTransfertCompteIn(Integer.parseInt(cEmetteur[2]), Integer.parseInt(cReveveur[2]), Double.parseDouble(montant));
					vueModele.addObject("succes", true);
					vueModele.addObject("description", "Le virement a ete effecture avec succes est le numéro de transation est : ["+transaction+"]");
				}
				else{
					vueModele.addObject("succes", false);
					vueModele.addObject("description", "Virement impossible, le montant du virement supérieur au Solde disponible du compte : ["+cEmetteur[1] +cEmetteur[2]+"]");
				}
				
				
			}else{
				vueModele.addObject("succes", false);
				vueModele.addObject("description", "Vous ne pouvez pas faire ce virement, les comptes sont identiques.");
			}
			
		}else{
			vueModele.addObject("succes", false);
			vueModele.addObject("description", "Vous ne pouvez pas faire un virement depuis un compte crédit.");
		}
		
		vueModele.addObject("comptes", serviceDaoClient.getAllComptesClientForTransfert(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));	
		
		return vueModele;
	}
	
	private boolean valideCompteifCredit(String[] cE){
		if(cE[0].equals("CREDIT")){
			return false;
		}else{
			return true;
		}
	}
	
	private boolean valideMMCompte(String[] cE, String[] cR){
		if(cE[2].equals(cR[2])){
			return false;
		}else{
			return true;
		}
	}
	
	private boolean valideSoustraction(String[] cE, String montant){
		double montantCompteOut = Double.parseDouble(cE[3]);
		double montantT = Double.parseDouble(montant);
		if(montantCompteOut-montantT > 0){
			System.out.println("OK LA SOUSTRATION EST VALIDE");
			return true;
		}else{
			return false;
		}
	}
}


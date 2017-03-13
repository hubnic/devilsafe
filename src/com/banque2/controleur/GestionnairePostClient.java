package com.banque2.controleur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
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
	
	final String url = "http://gti525banque2.herokuapp.com/api/virement";
	final String banque1 = "https://gti525-h17-banque1-backend.herokuapp.com/api/1.0.0/virement";
	
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
	@RequestMapping(value = {"/relevePdf"}, method = RequestMethod.POST)
	public ModelAndView postRelevePdf(
			@RequestParam("idCompte") int idCompte){

		return new ModelAndView("/client/client_relevepdf","compte",serviceDaoClient.getInfosComptePDF(idCompte));
			
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
					int transaction;
					if(cReveveur[0].equals("CREDIT")){
						transaction = serviceDaoClient.rembourserCC(Integer.parseInt(cEmetteur[2]), Integer.parseInt(cReveveur[2]), Float.parseFloat(montant));
					}
					else{
						transaction = serviceDaoClient.createTransfertCompteIn(Integer.parseInt(cEmetteur[2]), Integer.parseInt(cReveveur[2]), Float.parseFloat(montant));
					}
					
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

	
	private boolean valideSoustraction(String[] cE, String montant){
		float montantCompteOut = Float.parseFloat(cE[3]);
		float montantT = Float.parseFloat(montant);
		if(montantCompteOut-montantT > 0){
			System.out.println("OK LA SOUSTRATION EST VALIDE");
			return true;
		}else{
			return false;
		}
	}
	
	
	
		//ADMINISTRATEUR
		@RequestMapping(value = {"/remboursementCC"}, method = RequestMethod.POST)
		public ModelAndView postRemboursementCC(	
				@RequestParam("compteOut") String compteEmetteur,
				@RequestParam("montantRemboursement") float montantRemboursement,
				@RequestParam("idCC") int idCC,
				@RequestParam("montantCredit") float montantCredit){
			
			System.out.println("Controleur RemboursementCC : "+compteEmetteur + " " +montantRemboursement + " "+montantCredit);

			String[] cEmetteur = compteEmetteur.split(" ");
			
			ModelAndView vueModele = new ModelAndView();
			vueModele.setViewName("/client/client_credit");
			
			if(valideCompteifCredit(cEmetteur)){
				if(valideRemboursementCredit(cEmetteur, montantRemboursement)){
					System.out.println("Compte emetteur : "+cEmetteur[0]  + " " +cEmetteur[1] + " " +cEmetteur[2]+ " " +cEmetteur[3] + " " +cEmetteur[4]);
					int transaction = serviceDaoClient.rembourserCC(Integer.parseInt(cEmetteur[2]), idCC, montantRemboursement);
					if(transaction>0){
						vueModele.addObject("succes", true);
						vueModele.addObject("description", "Le virement a ete effecture avec succes est le numéro de transation est : ["+transaction+"]");
					}
					else{
						vueModele.addObject("succes", false);
						vueModele.addObject("description", "Erreur lors de l'execution du transfert....");
				
					}
					

				}
				else{
					vueModele.addObject("succes", false);
					vueModele.addObject("description", "Vous ne disposez pas suffisament de fond dans votre comptre pour effectuer ce remboursement.");
			
				}
			
			}else{
				vueModele.addObject("succes", false);
				vueModele.addObject("description", "Vous ne pouvez pas faire un virement depuis un compte crédit.");
			}
			
			vueModele.addObject("compteCredit", serviceDaoClient.getCompteCredit(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));
			vueModele.addObject("comptes", serviceDaoClient.getAllComptesClientForTransfert(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));	
			
			return vueModele;
		}
		
		
		//ADMINISTRATEUR
				@RequestMapping(value = {"/transfertOut"}, method = RequestMethod.POST)
				public @ResponseBody ModelAndView postTransfertOut(	
						@RequestParam("compteOut") String compteEmetteur,
						@RequestParam("idBanque") int idBanque,
						@RequestParam("idCompteExterne") int idCompteExterne,
						@RequestParam("commentaire") String commentaire,
						@RequestParam("montant") float montant){
					
					String[] cEmetteur = compteEmetteur.split(" ");
					System.out.println("Controleur du transfert EXTERNE : ");
					System.out.println("Compte emetteur : "+cEmetteur[0]  + " " +cEmetteur[1] + " " +cEmetteur[2]+ " " +cEmetteur[3] + " " +cEmetteur[4]);
					System.out.println("DETAILS DU VIREMENT : " + idBanque +" "+ idCompteExterne +" "+ commentaire +" "+ montant);
					ModelAndView vueModele = new ModelAndView();
					vueModele.setViewName("/client/client_transfertOut");
					try {
						
						
						     
						    RestTemplate restTemplate = new RestTemplate();
						     
						    HttpHeaders header = new HttpHeaders();
						    header.setContentType(MediaType.APPLICATION_JSON);
						    header.set("key", "1234");
						    header.set("host", "gti525banque2.herokuapp.com");
						    
						    JSONObject virementJson = new JSONObject();
						    virementJson.put("compte_dst_ID", "6003");
						    virementJson.put("src_ID", "sada");
						    virementJson.put("montant", "-50"); 
						    
							//TEST BANQUE 1
							//virementJson.add("montant", Float.toHexString(montant)));
							//virementJson.add("source", cEmetteur[1] +cEmetteur[2]));
							//virementJson.add("destination", Integer.toString(idBanque)+"-"+Integer.toString(idCompteExterne)));
							//virementJson.add("date", "date"));
							//virementJson.add("commentaire", commentaire));
						 
						    HttpEntity requeteVirementBanque1= new HttpEntity( virementJson.toString(), header );
						    
						    String st = restTemplate.postForObject(url, requeteVirementBanque1, String.class);
						    System.out.println(st);
						   
					
						int idTransaction = 1000;
						vueModele.addObject("succes", true);
						vueModele.addObject("description", "Le virement a ete effectué, ID de transaction : "+idTransaction);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						vueModele.addObject("succes", false);
						vueModele.addObject("description", "Le virement n'a  pu etre effectué en raison d'une erreur de communication avec la banque 2 ");
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
		
		private boolean valideRemboursementCredit(String[] cE, float montantRemboursement){
			if(Float.parseFloat(cE[3])-montantRemboursement>=0){
				return true;
			}else{
				return false;
			}
		}
}


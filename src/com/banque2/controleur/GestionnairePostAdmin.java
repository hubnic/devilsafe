package com.banque2.controleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GestionnairePostAdmin {

	//ADMINISTRATEUR
	@RequestMapping(value = {"/secureAdmin"}, method = RequestMethod.POST)
	public ModelAndView getLogAdmin(	
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
}

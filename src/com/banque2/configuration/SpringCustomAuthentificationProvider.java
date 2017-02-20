package com.banque2.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.banque2.services.ServiceAuthentification;

@Component
public class SpringCustomAuthentificationProvider implements AuthenticationProvider {
	
	@Autowired
	private ServiceAuthentification serviceAuthentification;
	
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	    	
	    	
	        String login = authentication.getName();
	        String mdp = authentication.getCredentials().toString();
	        
	        System.out.println("Voici les informations de l'utilisateur : "+ login +" MDP : "+mdp );
	        
	        if(login!=null && mdp!=null){
	        	String[] auth = serviceAuthentification.authentification(login, mdp);
		        System.out.println("Voici Le resultat du serVice D'authentification "+auth[0]);
		        //valider les informations
		        if (auth[0].equals("true")) {
		        	
		            List<GrantedAuthority> droit = new ArrayList<>();
		            droit.add(new SimpleGrantedAuthority(auth[1]));
		            
		            Authentication authSession = new UsernamePasswordAuthenticationToken(auth[2], auth[3], droit);
		            return authSession;
		        } else {
		            return null;
		        }
	        }else{
	        	return null;
	        }
	        
	    }
	 
	    public boolean supports(Class<?> authentication) {
	        return authentication.equals(UsernamePasswordAuthenticationToken.class);
	    }

}

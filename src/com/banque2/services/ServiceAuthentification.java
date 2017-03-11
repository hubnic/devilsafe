package com.banque2.services;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Utilisation de la librairie jasypt
 *
 */
public class ServiceAuthentification {
	
	private JdbcTemplate jdbcTemplate;
	StrongPasswordEncryptor mdp = new StrongPasswordEncryptor();

	public ServiceAuthentification(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	// utilise un algo SHA-256, 16 bytes et iteration = 100000
	public String hashMDP(String texteEnClair){
	return mdp.encryptPassword(texteEnClair);
	}
	
	private boolean samePassHash(String texteEnClair, String hash){
		return mdp.checkPassword(texteEnClair, hash);
	}
	
	
	public String[] authentification(String user, String textEnClair){
		
		String[] auth = new String[4];
		String type = Character.toString(user.charAt(0));
		
		if(type.equals("c")){
			//SUREMENT UN CLIENT
			System.out.println("CECI EST UN CLIENT");
			if(authentificationClient(user, textEnClair)){
				auth[0]="true";
				auth[1]="ROLE_CLIENT";
				auth[2]= user.substring(1, user.length());
				auth[3]= hashMDP(textEnClair);
			}else{
				auth[0]="false";
			}
			
		}else if(type.equals("a")){
			//SUREMENT UN ADMIN
			if(authentificationAdmin(user, textEnClair)){
				auth[0]="true";
				auth[1]="ROLE_ADMIN";
				auth[2]= user.substring(1, user.length());
				auth[3]= hashMDP(textEnClair);
				
			}else{
				auth[0]="false";
			}
		}
		else{
			auth[0]="false";
		}
		
		return auth;
	}
	
	public boolean authentificationClient(String user, String textEnClair){
		String authentificationClient = 
				"SELECT mdp,CONCAT(prefixe,identifiant) AS login " 
				+"FROM clients "
				+"HAVING login = ? ";
		
		try{
			List<Map<String, Object>> auth = jdbcTemplate.queryForList(authentificationClient,user);
			System.out.println(auth.get(0).get("mdp"));
			if(auth!=null && samePassHash(textEnClair,auth.get(0).get("mdp").toString())){
				System.out.println("Le mot de passe est correct");
				return true;
			}else{
				System.out.println("Le compte utilisateur n'existe pas ou le mot de passe est errone");
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean authentificationAdmin(String user, String textEnClair){
		String authentificationAdmin = 
				"SELECT mdp,CONCAT(prefixe,identifiant) AS login " 
				+"FROM administrateurs "
				+"HAVING login = ? ";
		try{
			List<Map<String, Object>> auth = jdbcTemplate.queryForList(authentificationAdmin,user);
			if(!auth.isEmpty()){
				if(samePassHash(textEnClair,auth.get(0).get("mdp").toString())){
					System.out.println("Le mot de passe est correct");
					return true;
				}else{
					System.out.println("Mauvais mot de passe");
					return false;
				}
			}
			else{
				System.out.println("Le compte utilisateur n'existe pas.");
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void updateAdminPass(String id, String mdp){
		String updatePass = "UPDATE administrateurs SET mdp=? WHERE identifiant=?";
		jdbcTemplate.update(updatePass, hashMDP(mdp) , id);
	}
	
	public void updateClientPass(String id, String mdp){
		String updatePass = "UPDATE clients SET mdp=? WHERE identifiant=?";
		jdbcTemplate.update(updatePass, hashMDP(mdp) , id);
	}
	
	public boolean checkAdminPIN(String id, String secureKey){
		String checkAdminPIN = "SELECT * FROM administrateurs WHERE identifiant = ? AND secureKey = ?";
		try{
			List<Map<String, Object>> validePin = jdbcTemplate.queryForList(checkAdminPIN,id,secureKey);
			
			if(!validePin.isEmpty()){
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
public boolean checkResetMDP(String user, String courriel){
		
		String type = Character.toString(user.charAt(0));

		boolean valide = false;
		
		if(type.equals("c")){
			String authentificationClient = 
					"SELECT CONCAT(prefixe,identifiant) AS login " 
					+"FROM clients "
					+"WHERE courriel = ? "
					+"HAVING login = ? ";
			
			try{
				List<Map<String, Object>> auth = jdbcTemplate.queryForList(authentificationClient,courriel,user);

				if(!auth.isEmpty()){
					valide = true;
				}
			}catch(Exception e){
				e.printStackTrace();
				valide = false;
			}
			
		}else if(type.equals("a")){
			
			String authentificationAdmin = 
					"SELECT CONCAT(prefixe,identifiant) AS login " 
					+"FROM administrateurs "
					+"WHERE courriel = ? "
					+"HAVING login = ? ";
			try{
				List<Map<String, Object>> auth = jdbcTemplate.queryForList(authentificationAdmin,courriel,user);
				
				if(!auth.isEmpty()){
					valide = true;
				}
			}catch(Exception e){
				e.printStackTrace();
				valide = false;
			}
		}
		return valide;

	}

public boolean setRecoveryPin(String user){
	
	String type = Character.toString(user.charAt(0));

	boolean valide = false;
	
	if(type.equals("c")){
		String recoveryClient = 
				"UPDATE clients "
				+"SET recovery = ? "
				+"WHERE CONCAT(prefixe,identifiant) = ?";
		
		try{
			if(jdbcTemplate.update(recoveryClient,new Random().nextInt(99999),user)==1){
				return true;
			}else{
				return false;
			}

		
		}catch(Exception e){
			e.printStackTrace();
			valide = false;
		}
		
	}else if(type.equals("a")){
		
		String recoveryAdmin = 
				"UPDATE administrateurs "
				+"SET recovery = ? "
				+"WHERE CONCAT(prefixe,identifiant) = ?";
		try{
			if(jdbcTemplate.update(recoveryAdmin,new Random().nextInt(99999),user)==1){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			valide = false;
		}
	}
	return valide;

}


public boolean resetMDP(String user, int pin, String pass){
	
	String type = Character.toString(user.charAt(0));

	boolean valide = false;
	
	if(type.equals("c")){
		String resetMDPClient = 
				"UPDATE clients "
				+"SET mdp = ? "
				+"WHERE CONCAT(prefixe,identifiant) = ? AND recovery = ? ";
		
		try{
			if(jdbcTemplate.update(resetMDPClient,hashMDP(pass),user,pin)==1){
				resetPIN(user);
				return true;
			}else{
				return false;
			}

		
		}catch(Exception e){
			e.printStackTrace();
			valide = false;
		}
		
	}else if(type.equals("a")){
		
		String resetMDPAdmin = 
				"UPDATE administrateurs "
				+"SET mdp = ? "
				+"WHERE CONCAT(prefixe,identifiant) = ? AND recovery = ? ";
		try{
			if(jdbcTemplate.update(resetMDPAdmin,hashMDP(pass),	user,pin)==1){
				resetPIN(user);
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			valide = false;
		}
	}
	return valide;

}

public boolean resetPIN(String user){
	
	String type = Character.toString(user.charAt(0));

	boolean valide = false;
	
	if(type.equals("c")){
		String resetPINClient = 
				"UPDATE clients "
				+"SET recovery = 0 "
				+"WHERE CONCAT(prefixe,identifiant) = ?";
		
		try{
			if(jdbcTemplate.update(resetPINClient,user)==1){
				
				return true;
			}else{
				return false;
			}

		
		}catch(Exception e){
			e.printStackTrace();
			valide = false;
		}
		
	}else if(type.equals("a")){
		
		String resetMDPAdmin = 
				"UPDATE administrateurs "
				+"SET recovery = 0 "
				+"WHERE CONCAT(prefixe,identifiant) = ? ";
		try{
			if(jdbcTemplate.update(resetMDPAdmin,user)==1){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			valide = false;
		}
	}
	return valide;

}


}

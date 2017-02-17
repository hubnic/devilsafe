package com.banque2.services;

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
	
	public boolean authentification(String user, String textEnClair){
		
		
		return true;
	}
}

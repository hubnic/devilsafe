package com.banque2.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.banque2.mappingModele.MappingAdminitrateur;
import com.banque2.modele.PojoAdministrateur;
import com.banque2.modele.PojoClient;



public class ServiceDaoAdministrateur {
	
	
private JdbcTemplate jdbcTemplate;
	
	public ServiceDaoAdministrateur(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean createAdmin(PojoAdministrateur administrateur) {
		String addAdministrateur = "INSERT INTO administrateurs (nom, prenom, mdp,secureKey) VALUES (?, ?, ?, ?)";

		try{
				jdbcTemplate.update(addAdministrateur, 
						administrateur.getNom(), 
						administrateur.getPrenom(),
						administrateur.getMdp(), 
						administrateur.getKey());
				return true;	
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		
	}
	
	public boolean createClient(PojoClient client) {
		String addClient = "INSERT INTO clients (nom, prenom,courriel,dateNaissance,telephone,adresse,mdp) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try{
				jdbcTemplate.update(addClient, 
						client.getNom(), 
						client.getPrenom(),
						client.getCourriel(),
						client.getDateNaissance(), 
						client.getTelephone(),
						client.getAdresse(),
						client.getMdp());
				return true;	
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		
	}
	
	public void update(PojoAdministrateur administrateur) {
		
		String sql = "";
		jdbcTemplate.execute(sql);
	}
	
	public void delete(PojoAdministrateur administrateur) {
		
		String sql = "";
		jdbcTemplate.execute(sql);
	}
	
	public PojoAdministrateur getAdministrateur(int adminId) {
		String sql = "SELECT * FROM administrateur WHERE id=" + adminId;
		
		try{
			List<PojoAdministrateur> result = jdbcTemplate.query(sql,new MappingAdminitrateur());
			
			if(result.isEmpty()){
				return null;
			}
			else{
				return result.get(0);
			}	
		}
		catch(Exception e){
			return null;
		}
		

	}
	

}

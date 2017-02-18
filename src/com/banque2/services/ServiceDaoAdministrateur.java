package com.banque2.services;


import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.banque2.mappingModele.MappingAdminitrateur;
import com.banque2.mappingModele.MappingClient;
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
	
	public PojoClient getClient(int id) {
		String getClient = "SELECT * FROM clients WHERE identifiant=" + id;
		
		try{
			List<PojoClient> result = jdbcTemplate.query(getClient,new MappingClient());
			
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
	
	public List<PojoClient> getAllClient() {
		String sql = "SELECT * FROM clients";
		
		try{
			List<PojoClient> result = jdbcTemplate.query(sql,new MappingClient());
			
			if(result.isEmpty()){
				return null;
			}
			else{
				return result;
			}	
		}
		catch(Exception e){
			return null;
		}
	

	}
}
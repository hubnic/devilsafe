package com.banque2.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.banque2.mappingModele.MappingAdministrateur;
import com.banque2.mappingModele.MappingClient;
import com.banque2.mappingModele.MappingCompte;
import com.banque2.mappingModele.MappingTransaction;
import com.banque2.modele.PojoAdministrateur;
import com.banque2.modele.PojoClient;
import com.banque2.modele.PojoCompte;
import com.banque2.modele.PojoTransaction;



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
	public boolean createCompteClient(PojoCompte compte) {
		
		String addClient = "INSERT INTO compte (type, numCarte, solde, idClient) "
				+ "VALUES (?, ?, ?, ?)";

		try{
				jdbcTemplate.update(addClient, 
						compte.getType(),
						1234567,
						compte.getSolde(),
						compte.getIdClient());
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
	
	public boolean deleteAccount(int idCompte) {
		String deleteAccount  = "DELETE FROM compte WHERE idCompte = ?";
		if(deleteAllTransaction(idCompte)){
			try{
				jdbcTemplate.update(deleteAccount,idCompte);
				return true;	
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean deleteAllTransaction(int idCompte) {
		String delTransaction  = "DELETE FROM transaction WHERE idCompteClient = ?";
		try{
			jdbcTemplate.update(delTransaction,idCompte);
			return true;	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	

	public PojoAdministrateur getProfil(String idAdmin) {
		String getProfil =
		"SELECT * "
		+"FROM administrateurs " 
		+"WHERE CONCAT(prefixe,identifiant) = ?";
		System.out.println("Execution de la requete.......");
		try{
			List<Map<String, Object>> profil = jdbcTemplate.queryForList(getProfil,"a"+idAdmin);
			System.out.println(profil.toString());
			if(profil.isEmpty()){
				return null;
			}
			else{
				PojoAdministrateur admin = new PojoAdministrateur();
				admin.setIdentifiant(Integer.parseInt(profil.get(0).get("identifiant").toString()));
				admin.setNom(profil.get(0).get("nom").toString());
				admin.setPrenom(profil.get(0).get("prenom").toString());
				
				return admin;
			}	
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void delete(PojoAdministrateur administrateur) {
		
		String sql = "";
		jdbcTemplate.execute(sql);
	}
	
	public PojoAdministrateur getAdministrateur(int adminId) {
		String sql = "SELECT * FROM administrateur WHERE id=" + adminId;
		
		try{
			List<PojoAdministrateur> result = jdbcTemplate.query(sql,new MappingAdministrateur());
			
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
	
	public List<PojoCompte> getAllComptesClient(int id){
		String getAllComptes = "SELECT * FROM compte where idClient ="+id;		
		try{
			ArrayList<PojoCompte> result = (ArrayList<PojoCompte>) jdbcTemplate.query(getAllComptes,new MappingCompte());
			result = this.getAllTransactionByCompte(result);
			
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
	
	
	private ArrayList<PojoCompte> getAllTransactionByCompte(ArrayList<PojoCompte> l){
    	System.out.println("Je suis dans getAllTransactionsByCompte ................");
    	
		String getAllTransactions = "SELECT * FROM transaction WHERE idCompteClient = ";
		
		System.out.println("Size de la liste : "+l.size());
	   	
	    for(int i=0; i<l.size();i++){
	    	
	    	List<PojoTransaction> result = jdbcTemplate.query(
	    			getAllTransactions+l.get(i).getIdCompte()
	    			,new MappingTransaction());
	    	
	    	System.out.println("Size de la liste de transaction : "+result.size());
	    	l.get(i).setTransactions(result);
	    	System.out.println("La transaction contenues dans la liste : "+l.get(i).getTransactions().toString());
		}

		return l;
		
	}
}
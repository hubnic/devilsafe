package com.banque2.services;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.banque2.mappingModele.MappingAdministrateur;
import com.banque2.mappingModele.MappingCarte;
import com.banque2.mappingModele.MappingClient;
import com.banque2.mappingModele.MappingCompte;
import com.banque2.mappingModele.MappingTransaction;
import com.banque2.modele.PojoAdministrateur;
import com.banque2.modele.PojoCarte;
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
	
	public boolean deleteClient(int idClient) {
		
		String delCarte = "DELETE FROM carte WHERE idCompte IN (SELECT idCompte FROM compte WHERE idClient = ?)";
		String delTransactions = "DELETE FROM transaction WHERE idCompteClient IN (SELECT idCompte FROM compte WHERE idClient = ?)";
		String delCompte = "DELETE FROM compte WHERE idClient = ?";
		String delClient = "DELETE FROM clients WHERE identifiant = ?";
		try{
				jdbcTemplate.update(delCarte,idClient);
				jdbcTemplate.update(delTransactions,idClient);
				jdbcTemplate.update(delCompte,idClient);
				jdbcTemplate.update(delClient,idClient);
				
				return true;	
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		
	}

	
	
	public boolean createCompteClient(PojoCompte compte) {
		
		String addClient = "INSERT INTO compte (type, solde, idClient) "
				+ "VALUES (?, ?, ?)";

		try{
				jdbcTemplate.update(addClient, 
						compte.getType(),
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
		if(deleteAllTransaction(idCompte) && deleteCC(idCompte)){
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
	
	public boolean deleteCC(int idCompte) {
		String delCC  = "DELETE FROM carte WHERE idCompte = ?";
		try{
			jdbcTemplate.update(delCC,idCompte);
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
	
	
	public List<PojoClient> getAllClientByID(String idClient) {
		String recherche = 
				"SELECT * "
				+"FROM clients "
				+"WHERE "
				+"identifiant = "+idClient;
		try{
			List<PojoClient> result = jdbcTemplate.query(recherche, new MappingClient());
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
	
	public List<PojoClient> getAllClientByNom(String nom) {
		String recherche = 
				"SELECT * "
				+"FROM clients "
				+"WHERE "
				+"nom LIKE '%"+nom+"%'";
		try{
			List<PojoClient> result = jdbcTemplate.query(recherche, new MappingClient());
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
	
	public List<PojoClient> getAllClientByPrenom(String prenom) {
		String recherche = 
				"SELECT * "
				+"FROM clients "
				+"WHERE "
				+"prenom LIKE '%"+prenom+"%'";
		try{
			List<PojoClient> result = jdbcTemplate.query(recherche, new MappingClient());
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
	
	public List<PojoClient> getAllClientByCourriel(String courriel) {
		String recherche = 
				"SELECT * "
				+"FROM clients "
				+"WHERE "
				+"courriel LIKE '%"+courriel+"%'";
		try{
			List<PojoClient> result = jdbcTemplate.query(recherche, new MappingClient());
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
	
	public boolean checkIfCardIsAlreadyPresent(int idCompte){
		String checkCC = "SELECT * FROM carte WHERE idCompte = "+idCompte;
		
		try{
			List<PojoCarte> result = jdbcTemplate.query(checkCC,new MappingCarte());
			
			if(result.isEmpty()){
				return true;
			}
			else{
				return false;
			}	
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean createCreditCard(int idClient,int idCompte){
		PojoClient client = getClient(idClient);
		Date date = new Date();
		System.out.println("Voici la date :"+date.getYear()+"/"+ date.getMonth() +"/"+ date.getDay());
		String insertCC = 
				"INSERT INTO carte (numCarte, dateExp, crypto, nom, prenom, idCompte) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		try{
			jdbcTemplate.update(insertCC, 
					creerCarteLuhn(), 
					"2018/02/01",
					new Random().nextInt(999), 
					client.getNom(),
					client.getPrenom(), 
					idCompte);
			return true;	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private String creerCarteLuhn(){

		Random random = new Random();
		
		int zero = 6666;	
		int un = random.nextInt(9999);	
		int deux = random.nextInt(9999);	
		int trois = random.nextInt(9999);
		
		String carte = 
				Integer.toString(zero) +
				Integer.toString(un) +
				Integer.toString(deux) +
				Integer.toString(trois);
		//System.out.println(carte);
		int calc=0;
		int i = carte.length()-1;
		boolean carteValide = false;
		int valeur;
		
		while(!carteValide){
			
			if(i % 2 == 0){
				//System.out.println("modulo 2 ok");
				valeur = Character.getNumericValue(carte.charAt(i)) * 2;
				if(valeur > 10){
					
					valeur = valeur -9;
				}
				calc = calc + valeur;
			}
			else{
				calc = calc + Character.getNumericValue(carte.charAt(i)); 
			}
			if(i==0){
				//System.out.println("Voici le resultat du calc : " +calc);
				
				if(calc % 10 == 0){
					carteValide = true;
					//System.out.println("voici la carte valide : "+carte);
				}else{	
					calc = 0;
					un = random.nextInt(9999);	
					deux = random.nextInt(9999);	
					trois = random.nextInt(9999);
					
					carte = 
							zero +
							Integer.toString(un) +
							Integer.toString(deux) +
							Integer.toString(trois);
					
					i = carte.length();
				}
				
			}
			i--;
		}
		return carte;
	}
}
package com.banque2.services;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.banque2.mappingModele.MappingPreautorisation;
import com.banque2.mappingModele.MappingTransaction;
import com.banque2.modele.PojoAdministrateur;
import com.banque2.modele.PojoCarte;
import com.banque2.modele.PojoClient;
import com.banque2.modele.PojoCompte;
import com.banque2.modele.PojoPreautorisation;
import com.banque2.modele.PojoTransaction;


public class ServiceDaoAdministrateur {
	
	
private JdbcTemplate jdbcTemplate;
private PojoCompte templateCredit = new PojoCompte();
private PojoCompte templateDebit = new PojoCompte();
	
	public ServiceDaoAdministrateur(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean createAdmin(PojoAdministrateur administrateur) {
		String addAdministrateur = "INSERT INTO administrateurs (nom, prenom,courriel, mdp,secureKey) VALUES (?, ?, ?, ?, ?)";

		try{
				jdbcTemplate.update(addAdministrateur, 
						administrateur.getNom(), 
						administrateur.getPrenom(),
						administrateur.getCourriel(),
						administrateur.getMdp(), 
						administrateur.getKey());
				return true;	
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}
	
		
	public boolean createClient(PojoClient client, float solde) {
		String addClient = "INSERT INTO clients (nom, prenom,courriel,dateNaissance,telephone,adresse,mdp) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		templateCredit.setType("CREDIT");
		templateCredit.setSolde(0);
		
		templateDebit.setType("DEBIT");
		templateDebit.setSolde(solde);
		
		try {
			
			Connection connec = jdbcTemplate.getDataSource().getConnection();
			
			PreparedStatement st = connec.prepareStatement(addClient,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, client.getNom());
			st.setString(2, client.getPrenom());
			st.setString(3, client.getCourriel());
			st.setString(4, client.getDateNaissance());
			st.setString(5, client.getTelephone());
			st.setString(6, client.getAdresse());
			st.setString(7, client.getMdp());
			st.executeUpdate();
			ResultSet result = st.getGeneratedKeys();
			
			if (result.next()) {
			    System.out.println("Valeur : "+result.getInt(1));
			    int idClient = result.getInt(1);
			    System.out.println("Id du Client crée : "+result.getInt(1));
			    templateCredit.setIdClient(idClient);
			    templateDebit.setIdClient(idClient);
			    createCompteClient(templateCredit);
			    createCompteClient(templateDebit);
			    connec.close();
			    return true;
			}else{
				connec.close();
				return false;
			}
			
		} catch (SQLException e) {
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
		
		String addCompteClient = "INSERT INTO compte (type, solde, idClient) VALUES (?, ?, ?)";
		try {
			Connection connec = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement st = connec.prepareStatement(addCompteClient,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, compte.getType());
			st.setDouble(2, compte.getSolde());
			st.setInt(3, compte.getIdClient());
			st.executeUpdate();
			ResultSet result = st.getGeneratedKeys();
			
			if (result.next()) {
			    System.out.println("Id du compte crée : "+result.getInt(1));
			    int idCompte = result.getInt(1);
				createCreditCard(compte.getIdClient(), idCompte);
				connec.close();
				return true;
			}else{
				connec.close();
				return false;
			}
			
		} catch (SQLException e) {
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
		String sql = "SELECT * FROM clients ORDER BY identifiant";
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
			result = this.getAllPreautorisation(result);
			
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
	
	private ArrayList<PojoCompte> getAllPreautorisation(ArrayList<PojoCompte> l){
		String getIdCarte = "SELECT numCarte FROM carte WHERE idCompte = ?" ;
		String getPreauth = "SELECT * FROM preautorisation WHERE credit_id = " ;
		String carte;
	    for(int i=0; i<l.size();i++){
	    	if(l.get(i).getType().equals("CREDIT")){
	    		System.out.println("CECI EST UN COMPTE CREDIT ");
	    		System.out.println(l.get(i).getIdCompte());
	    		    		
	    		try{
	    		
	    			Connection connec = jdbcTemplate.getDataSource().getConnection();
	        		PreparedStatement st = connec.prepareStatement(getIdCarte);
	        		st.setInt(1, l.get(i).getIdCompte());      		
	        		ResultSet result = st.executeQuery();
	        		
	        		if(result.next()){
	        			carte = result.getString(1);
	          		    System.out.println(carte);
	          			List<PojoPreautorisation> preauth = jdbcTemplate.query(getPreauth+carte,new MappingPreautorisation());
	          			l.get(i).setPreautorisation(preauth);
	          			System.out.println(preauth.size());
	        		}
	        		connec.close();
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}
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
				
		String insertCC = 
				"INSERT INTO carte (numCarte, dateExp, crypto, nom, prenom, idCompte) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		try{
			jdbcTemplate.update(insertCC, 
					creerCarteLuhn2(), 
					"2021/04",
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
	
	public boolean checkIfCCpossible(int idClient){
			String checkifCCexiste = "SELECT * FROM compte WHERE type = 'CREDIT' AND idClient = " + idClient;
		
		try{
			List<PojoCompte> result = jdbcTemplate.query(checkifCCexiste,new MappingCompte());
			if(result.isEmpty()){
				return true;
			}
			else{
				return false;
			}	
		}
		catch(Exception e){
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
		
		System.out.println(carte);
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
				System.out.println("Voici le resultat du calc : " +calc);
				
				if(calc % 10 == 0){
					if(carte.length()==16){
						carteValide = true;
						System.out.println("voici la carte valide : "+carte);
					}	
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
					
					i = carte.length()-1;
				}
				
			}
			i--;
		}
		return carte;
	}
	
	// ALGO BASÉ SUR : http://www.dcode.fr/algorithme-luhn
	private String creerCarteLuhn2(){

		Random random = new Random();
		
		boolean carteValide = false;
		String carte = null;
		while(!carteValide){
			
		
		boolean sizeOK = false;
		while(!sizeOK){
			int zero = 6666;
			int un = random.nextInt(9999);	
			int deux = random.nextInt(9999);	
			int trois = random.nextInt(9999);
			carte = Integer.toString(zero) + Integer.toString(un) +	Integer.toString(deux) + Integer.toString(trois);
			if(carte.length()==16){
				sizeOK=true;
			}
		}
	    int somme = 0;

	    boolean inversion = false;

	    for (int i = 16 - 1; i >= 0; i--){

	    int n = Integer.parseInt(carte.substring(i, i + 1));
        if (inversion){
             n = n * 2;
             if (n > 9){
                n = (n % 10) + 1;
             }
       }
        	 somme += n;
        	 inversion = !inversion;
     }

		if(somme % 10 == 0){
			carteValide = true;
		}
	}
		return carte;
}
}
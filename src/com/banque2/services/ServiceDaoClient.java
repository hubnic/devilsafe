package com.banque2.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.banque2.mappingModele.MappingAdministrateur;
import com.banque2.mappingModele.MappingClient;
import com.banque2.mappingModele.MappingCompte;
import com.banque2.mappingModele.MappingPreautorisation;
import com.banque2.mappingModele.MappingTransaction;
import com.banque2.modele.PojoAdministrateur;
import com.banque2.modele.PojoClient;
import com.banque2.modele.PojoCompte;
import com.banque2.modele.PojoPreautorisation;
import com.banque2.modele.PojoTransaction;

public class ServiceDaoClient {
	
	private JdbcTemplate jdbcTemplate;
	
	public ServiceDaoClient(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

public PojoClient getProfilClient(int id) {
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

public List<PojoCompte> getAllComptesClient(int idClient){
	
	System.out.println("Execution de la requete pour client "+idClient);
	String getAllComptes = "SELECT * FROM compte where idClient ="+idClient;		
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

public List<PojoCompte> getAllComptesClientForTransfert(int idClient){
	String getAllComptes = "SELECT * FROM compte where idClient ="+idClient;		
	try{
		ArrayList<PojoCompte> result = (ArrayList<PojoCompte>) jdbcTemplate.query(getAllComptes,new MappingCompte());

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
	String getAllTransactions = "SELECT * FROM transaction WHERE idCompteClient = ";
	
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
	String getAllPreautorisation = "SELECT * FROM preautorisation WHERE idCompteClient = ";
	
    for(int i=0; i<l.size();i++){
    	
    	List<PojoPreautorisation> result = jdbcTemplate.query(
    			getAllPreautorisation+l.get(i).getIdCompte(),
    			new MappingPreautorisation());
    	
    	System.out.println("Size de la liste de transaction : "+result.size());
    	l.get(i).setPreautorisation(result);
    	System.out.println("La transaction contenues dans la liste : "+l.get(i).getTransactions().toString());
	}

	return l;
	
}


public int createTransfertCompteIn(int idCompteEmetteur, int idCompteReceveur, double montant) {

		
		// retrait fond en 1
		// ajout fond en 2
		// Creation de la transaction
	
		retraitFondCompte(idCompteEmetteur, montant);
		ajouterFondCompte(idCompteReceveur, montant);
		int idTransaction = createTransactionTransfertIn(idCompteEmetteur, idCompteReceveur, -montant,"Virement vers compte "+ idCompteReceveur);
		createTransactionTransfertIn(idCompteReceveur, idCompteEmetteur, montant,"Virement en provenace du compte "+ idCompteEmetteur);
		if(idTransaction != -1){
			return idTransaction;
		}
		else{
			return -1;
		}		
}

private int createTransactionTransfertIn(int idCompteOut, int idCompteIn, double montant, String description) {
	
	String transaction =
	"INSERT INTO transaction (idCompteClient, idCompteDestinataire, montant, description)"
	+" VALUES ( ?, ?, ?, ?)";
	
	// compteOut CompteIn Montant DEsc
	try {
		Connection connec = jdbcTemplate.getDataSource().getConnection();
		PreparedStatement st = connec.prepareStatement(transaction,Statement.RETURN_GENERATED_KEYS);
		st.setInt(1, idCompteOut);
		st.setInt(2, idCompteIn);
		st.setDouble(3, montant);
		st.setString(4, description);
		st.executeUpdate();
		
		ResultSet result = st.getGeneratedKeys();
		if (result.next()) {
		    System.out.println("L'id de la transaction a été crée : "+ result.getInt(1));
		    int idtransaction = result.getInt(1);
			return idtransaction;
		}else{
			return -1;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		return -1;
	}
}

private boolean ajouterFondCompte(int idCompte, double montant) {
	String ajouterFondCompte =	
	"UPDATE compte"
	+" SET Solde = (Solde + ?)"
	+" WHERE idCompte = ?";
	
	try{
		Connection connec = jdbcTemplate.getDataSource().getConnection();
		PreparedStatement st = connec.prepareStatement(ajouterFondCompte);
		st.setDouble(1, montant);
		st.setInt(2, idCompte);
		st.executeUpdate();
		return true;
	}catch(Exception e){
		
		return false;
	}

}

private boolean retraitFondCompte(int idCompte, double montant) {
	String retaitFondCompte =	
			"UPDATE compte"
			+" SET Solde = (Solde - ?)"
			+" WHERE idCompte = ?";
			
			try{
				Connection connec = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement st = connec.prepareStatement(retaitFondCompte);
				st.setDouble(1, montant);
				st.setInt(2, idCompte);
				st.executeUpdate();
				return true;
			}catch(Exception e){
				
				return false;
			}
}

public PojoCompte getCompteCredit(int idClient) {
	PojoCompte tmpCC = new PojoCompte();
	String getCompteCC =	
			"SELECT *"
			+" FROM compte"
			+" WHERE type = 'CREDIT'"
			+" AND idClient = ?";
			
			try{
				Connection connec = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement st = connec.prepareStatement(getCompteCC);
				st.setInt(1, idClient);
				ResultSet result = st.executeQuery();
				if (result.next()) {
					tmpCC.setIdCompte(result.getInt(1));
					tmpCC.setIdBanque(result.getString(2));
					tmpCC.setType(result.getString(3));
					tmpCC.setSolde(result.getFloat(4));
					System.out.println(result.getFloat(4));
					return tmpCC;
				}else{
					return null;
				}
			}catch(Exception e){
				return null;
			}
}


}

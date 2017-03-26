package com.banque2.services;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class ServiceDaoBanque1 {


	private final String API_KEY_BANQUE = "1l1l1l1l1l1l1l1l1l1l1l1l";
	private final String URL_BANQUE1_API = "https://gti525-h17-banque1-backend.herokuapp.com/api/1.0.0/";
	private JdbcTemplate jdbcTemplate;

	
	public ServiceDaoBanque1(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	/*
	{
	EXEMPLE SELON BANQUE !
		  "commentaire": "Paiement pour billet",
		  "date": "2017-03-26T19:02:11.061Z",
		  "destination": "133-2132",
		  "montant": 0,
		  "numeroConfirmation": "71829-123321",
		  "source": "133-1123"
		}
	*/

public int doVirement(String compteSrc, String compteDst, double montant , String commentaire) {
	try {		
		System.out.println("VALIDATION DU COMPTE");
		if(clientExists(compteDst)){
			// CREATION THEADER
			RestTemplate restTemplate = new RestTemplate();
			//HEADER
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			header.add("apikey", API_KEY_BANQUE);
			header.set("host", "https://gti525-h17-banque1-backend.herokuapp.com/api/1.0.0/");

			//OBJECT VIREMENT
			JSONObject virementJson = new JSONObject();
			virementJson.put("commentaire", commentaire);
			//virementJson.put("date", "2017-03-26T19:02:11.061Z");
			virementJson.put("destination",compteDst);
			virementJson.put("montant", montant);
			//virementJson.put("numeroConfirmation", "71829-123321");
			virementJson.put("source", compteSrc);
			String virement = "https://gti525-h17-banque1-backend.herokuapp.com/api/1.0.0/"+"virement";
		
			//
			HttpEntity requeteVirementBanque1= new HttpEntity( virementJson.toString(), header );
			System.out.println(requeteVirementBanque1.toString());
			ResponseEntity st = restTemplate.postForEntity(virement, requeteVirementBanque1, String.class);
			
			//AFFICHE LE STATUT DE LA REQUETE
			System.out.println(st.getStatusCode());
			System.out.println(st);
			if(st.getStatusCode().is2xxSuccessful()){
				//RETIRER L'ARGENT DU COMPTE SOURCE
				System.out.println("VIREMENT EFFECTUE");
				int idCompteEmetteur = Integer.parseInt(compteSrc.split("-")[1]);
				int idCompteReceveur = Integer.parseInt(compteDst.split("-")[1]);
				int idTransaction = createTransfertCompteIn(idCompteEmetteur, idCompteReceveur, " Virement vers : (Banque1) "+commentaire+" "+compteDst , montant);
				return idTransaction;
			}else{
				// ERREUR (-1) = Statut != 200 OK
				return -1;
			}
		}else{
			//LE COMPTE N'EXISTE PAS
			return -2;
		}
		
	}
	catch(Exception e){
		// ERREUR (-3) = EXCEPTION
		e.printStackTrace();
		return -3;
	}

}


public boolean clientExists(String idClient) {
	
	try{
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("apikey", API_KEY_BANQUE);
		headers.set("host", "https://gti525-h17-banque1-backend.herokuapp.com/api/1.0.0/");

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity st = restTemplate.exchange("https://gti525-h17-banque1-backend.herokuapp.com/api/1.0.0/client/"+idClient, HttpMethod.GET, entity, String.class);
		if(st.getStatusCode().is2xxSuccessful()){
			return true;
		}else{
			return false;
		}
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
		
		
}



public int createTransfertCompteIn(int idCompteEmetteur, int idCompteReceveur, String commentaire, double montant) {

	// retrait fond en 1

	retraitFondCompte(idCompteEmetteur, montant);
	
	int idTransaction = createTransaction(idCompteEmetteur, idCompteReceveur, -montant,commentaire);
	if(idTransaction != -1){
		return idTransaction;
	}
	else{
		return -1;
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
				 connec.close();
				return true;
			}catch(Exception e){
				
				return false;
			}
}

public int createTransaction(int idCompteOut, int idCompteIn, double montant, String description) {

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
		    System.out.println("L'id de la transaction a ete cree : "+ result.getInt(1));
		    int idtransaction = result.getInt(1);
		    connec.close();
			return idtransaction;
		}else{
			 connec.close();
			return -1;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return -1;
	}
}
}

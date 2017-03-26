package com.banque2.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

public class ServiceDaoBanque1 {



	public ServiceDaoBanque1(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	private final String API_KEY_BANQUE = "1l1l1l1l1l1l1l1l1l1l1l1l";
	private final String URL_BANQUE1_API = "https://gti525-h17-banque1-backend.herokuapp.com/api/1.0.0/";
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ServiceDaoCompte serviceDaoCompte;
	

public String doVirement(int compteSrc, String compteDst, double montant) {
	// CREATION THEADER
	RestTemplate restTemplate = new RestTemplate();

	HttpHeaders header = new HttpHeaders();
	header.add("Content-Type","application/json");
	header.add("apikey", "1l1l1l1l1l1l1l1l1l1l1l1l");
//	header.set("host", "https://gti525-h17-banque1-backend.herokuapp.com/api/1.0.0/");

	JSONObject virementJson = new JSONObject();
	virementJson.put("commentaire", "Virement de "+montant+" du compte 166-" +compteSrc+ " au compte "+ compteDst);
	virementJson.put("destination", compteDst);
	virementJson.put("montant", montant);
	virementJson.put("source", compteSrc);
	String virement = URL_BANQUE1_API+"virement";
	System.out.println(virement);
	try{
		HttpEntity requeteVirementBanque1= new HttpEntity( virementJson.toString(), header );
		System.out.println(requeteVirementBanque1.toString());
		ResponseEntity st = restTemplate.postForEntity(virement, requeteVirementBanque1, String.class);
		System.out.println(st.getStatusCode());
		return st.getStatusCode().toString();
	}
	catch(Exception e){
		System.out.println("Erreur Virement:");
		System.out.println(e);
		return "marche pas";
	}
}

public boolean clientExists(String idClient) {

	RestTemplate restTemplate = new RestTemplate();

	HttpHeaders header = new HttpHeaders();

	header.add("Content-Type","application/json");
	header.add("apikey", "0o0o0o0o0o0o0o0o0o0o0o0o");
//	header.set("apikey", "1l1l1l1l1l1l1l1l1l1l1l1l");
//	header.set("host", "https://gti525-h17-banque1-backend.herokuapp.com/api/1.0.0/client");

	String client = URL_BANQUE1_API+"client/"+idClient;
	System.out.println(client);
	try {
		HttpEntity requeteGetUserBanque1 = new HttpEntity(header);
		System.out.println(requeteGetUserBanque1.toString());
		ResponseEntity st = restTemplate.getForEntity(client, String.class);
		System.out.println(st.getStatusCode());
		if(st.getStatusCode().is2xxSuccessful()){
			return true;
		}
		return false;
	} catch (Exception e) {
		System.out.println("Erreur Exist:");
		System.out.println(e);
		return false;
	}

}
}

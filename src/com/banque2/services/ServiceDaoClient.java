package com.banque2.services;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.banque2.mappingModele.MappingAdministrateur;
import com.banque2.mappingModele.MappingCompte;
import com.banque2.mappingModele.MappingTransaction;
import com.banque2.modele.PojoAdministrateur;
import com.banque2.modele.PojoClient;
import com.banque2.modele.PojoCompte;
import com.banque2.modele.PojoTransaction;

public class ServiceDaoClient {
	
	private JdbcTemplate jdbcTemplate;
	
	public ServiceDaoClient(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void create(PojoClient client) {
		
		String sql = "";
		jdbcTemplate.execute(sql);
}

public void update(PojoAdministrateur administrateur) {
	
	String sql = "";
	jdbcTemplate.execute(sql);
}

public void delete(PojoAdministrateur administrateur) {
	
	String sql = "";
	jdbcTemplate.execute(sql);
}

public PojoAdministrateur getClient(int clientId) {
	
	String sql = "SELECT * FROM client WHERE id=" + clientId;
	
	List<PojoAdministrateur> result = jdbcTemplate.query(sql,new MappingAdministrateur());
	
	if(result.isEmpty()){
		return null;
	}
	else{
		return result.get(0);
	}	

}

public List<PojoCompte> getAllComptesClient(int idClient){
	
	System.out.println("Execution de la requete pour client "+idClient);
	String getAllComptes = "SELECT * FROM compte where idClient ="+idClient;		
	try{
		ArrayList<PojoCompte> result = (ArrayList<PojoCompte>) jdbcTemplate.query(getAllComptes,new MappingCompte());
		result = this.getAllTransactionByCompte(result);
		System.out.println(result.get(0));
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


}

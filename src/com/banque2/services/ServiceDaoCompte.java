package com.banque2.services;

import com.banque2.mappingModele.MappingCompte;
import com.banque2.modele.PojoClient;
import com.banque2.modele.PojoCompte;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ServiceDaoCompte {

	private JdbcTemplate jdbcTemplate;

	public ServiceDaoCompte(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean createAccount(PojoClient client, String type){
		String sql ="";
		jdbcTemplate.execute(sql);
		return true;
	}
	public PojoCompte getAccount(int pid){
		String getAccount = "SELECT * FROM compte WHERE idCompte =" + pid;

		try{
			List<PojoCompte> result = jdbcTemplate.query(getAccount ,new MappingCompte());
			
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

	public Boolean ajoutMontant(int pid, double montant){
		String ajoutMontant = "UPDATE compte SET Solde = Solde + "+montant+" WHERE idCompte = " + pid;

		try{

			jdbcTemplate.execute(ajoutMontant);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public boolean deleteAccount(PojoClient client, String type){
		String sql ="";
		jdbcTemplate.execute(sql);
		return true;
	}
	
}

package com.banque2.services;

import com.banque2.mappingModele.MappingPreautorisation;
import com.banque2.modele.PojoPreautorisation;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ServiceDaoApi {

private JdbcTemplate jdbcTemplate;
	
	public ServiceDaoApi(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public PojoPreautorisation getPreautorisation(int pid) {
		String getPreautorisation = "SELECT * FROM preautorisation WHERE preauth_id =" + pid;
		
		try{
			List<PojoPreautorisation> result = jdbcTemplate.query(getPreautorisation,new MappingPreautorisation());
			
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
	
	
	public Boolean createPreautorisation(PojoPreautorisation preauth) {
		String addpreauth = "INSERT INTO preautorisation (preauth_id, credit_id, credit_expiration, credit_nom, credit_prenom, credit_cvs, source_id, montant) VALUES (?,?, ?, ?, ?, ?, ?, ?)";

		try{
				jdbcTemplate.update(addpreauth,
						preauth.getPreauth_id(),
						preauth.getCredit_id(), 
						preauth.getCredit_expiration(),
						preauth.getCredit_nom(), 
						preauth.getCredit_prenom(), 
						preauth.getCredit_cvs(),
						preauth.getSource_id(), 
						preauth.getMontant());
				return true;	
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}
	
	
	
}

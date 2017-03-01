package com.banque2.services;

import com.banque2.mappingModele.MappingCarte;
import com.banque2.mappingModele.MappingPreautorisation;
import com.banque2.modele.PojoCarte;
import com.banque2.modele.PojoPreautorisation;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		String addpreauth = "INSERT INTO preautorisation (preauth_id, credit_id, credit_expiration, credit_nom, credit_prenom, credit_cvs, source_id, montant, preauth_status) VALUES (?,?,?, ?, ?, ?, ?, ?, ?)";

		try{
				jdbcTemplate.update(addpreauth,
						preauth.getPreauth_id(),
						preauth.getCredit_id(), 
						preauth.getCredit_expiration(),
						preauth.getCredit_nom(), 
						preauth.getCredit_prenom(), 
						preauth.getCredit_cvs(),
						preauth.getSource_id(), 
						preauth.getMontant(),
						preauth.getPreauthStatus());
				return true;	
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}

	public Boolean modifierPreautorizationStatus(PojoPreautorisation preauth){

		String status = preauth.getPreauthStatus();
		int id = preauth.getPreauth_id();
		String modifStatus = "UPDATE preautorisation SET preauth_status = ? WHERE preauth_id = ?";

		try{

			jdbcTemplate.update(modifStatus,
									preauth.getPreauthStatus(),
									preauth.getPreauth_id());
			return true;
/*
			if(result.isEmpty()){
				return false;
			}
			else{
				return true;
			}*/
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean checkIfCCexit(PojoCarte carte) {
		String checkCC = 
				"SELECT *"
				+" FROM carte"
				+" WHERE  numCarte = ?"
				+" AND UPPER(nom) = UPPER(?)"
				+" AND UPPER(prenom) = UPPER(?)"
				+" AND dateExp = ?"
				+" AND crypto = ?";
		try {
			Connection connec = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement st = connec.prepareStatement(checkCC);
			st.setString(1, Integer.toString(carte.getNumCarte()));
			st.setString(2, carte.getNom());
			st.setString(3, carte.getPrenom());
			st.setString(4, carte.getDateExp());
			st.setInt(5, carte.getCrypto());
			
			
			ResultSet result = st.executeQuery();
			if (result.next()) {
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
}
}

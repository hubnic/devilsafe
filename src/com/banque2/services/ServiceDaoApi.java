package com.banque2.services;

import com.banque2.mappingModele.MappingPreautorisation;
import com.banque2.modele.PojoCarte;
import com.banque2.modele.PojoPreautorisation;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
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

			System.out.print("result: "+result.get(0).toString());
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

	public int createPreautorisation(PojoPreautorisation preauth) {
		String addpreauth = "INSERT INTO preautorisation (credit_id, credit_expiration, credit_nom, credit_prenom, credit_cvs, source_id, montant, preauth_status) VALUES (?,?, ?, ?, ?, ?, ?, ?)";

		try{
			Connection connec = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement st = connec.prepareStatement(addpreauth, Statement.RETURN_GENERATED_KEYS);

			st.setString(1,preauth.getCredit_id());
			st.setString(2,preauth.getCredit_expiration());
			st.setString(3,preauth.getCredit_nom());
			st.setString(4,preauth.getCredit_prenom());
			st.setInt(5,preauth.getCredit_cvs());
			st.setString(6,preauth.getSource_id());
			st.setDouble(7,preauth.getMontant());
			st.setString(8,preauth.getPreauthStatus());
			st.executeUpdate();
			ResultSet result = st.getGeneratedKeys();
			 
			if (result.next()) {
				System.out.println("L'id de la preautorisation a ete cree : "+ result.getInt(1));
				int idPreauth = result.getInt(1);
				connec.close();
				return idPreauth;
			}else{
				connec.close();
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
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

			System.out.print(carte.getNumCarte()+"\n");
			System.out.print(carte.getNom()+"\n");
			System.out.print(carte.getPrenom()+"\n");
			System.out.print(carte.getDateExp()+"\n");
			System.out.print(carte.getCrypto()+"\n");
			st.setString(1, carte.getNumCarte());
			st.setString(2, carte.getNom());
			st.setString(3, carte.getPrenom());
			st.setString(4, carte.getDateExp());
			st.setInt(5, carte.getCrypto());
			
			
			ResultSet result = st.executeQuery();
			
			if (result.next()) {
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

}

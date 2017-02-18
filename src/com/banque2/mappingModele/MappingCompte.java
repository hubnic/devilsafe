package com.banque2.mappingModele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.banque2.modele.PojoClient;
import com.banque2.modele.PojoCompte;
import com.banque2.modele.PojoTransaction;



public final class MappingCompte implements RowMapper<PojoCompte> {
	
	    public PojoCompte mapRow(ResultSet resultSet, int element) throws SQLException {
	    	PojoCompte compte = new PojoCompte();
	    	compte.setIdCompte(resultSet.getInt("idCompte"));
	    	compte.setType(resultSet.getString("type"));
	    	compte.setNumCarte(resultSet.getInt("numCarte"));
	    	compte.setSolde(resultSet.getInt("solde"));
	    	compte.setIdClient(resultSet.getInt("idClient"));
	        return compte;
	    }
}



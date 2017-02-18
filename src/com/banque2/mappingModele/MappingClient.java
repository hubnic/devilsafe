package com.banque2.mappingModele;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.banque2.modele.PojoClient;



	/**
	 * Permet de transformet un resultat sql en objet modele administrateur
	 *
	 */
public final class MappingClient implements RowMapper<PojoClient> {

	    public PojoClient mapRow(ResultSet resultSet, int element) throws SQLException {
	    	PojoClient client = new PojoClient(
	        		resultSet.getInt("identifiant"),
	        		resultSet.getString("nom"),
	        		resultSet.getString("prenom"),
	        		resultSet.getString("courriel"),
	        		resultSet.getString("dateNaissance"),
	        		resultSet.getString("telephone"),
	        		resultSet.getString("adresse"));
	        return client;
	    }
}



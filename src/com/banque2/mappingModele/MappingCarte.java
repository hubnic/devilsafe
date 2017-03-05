package com.banque2.mappingModele;

import com.banque2.modele.PojoCarte;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MappingCarte implements RowMapper<PojoCarte> {

    public PojoCarte mapRow(ResultSet resultSet, int element) throws SQLException {
    	PojoCarte carte = new PojoCarte();
    	carte.setNumCarte(resultSet.getString("numCarte"));
    	carte.setDateExp(resultSet.getString("dateExp"));
    	carte.setCrypto(resultSet.getInt("crypto"));
    	carte.setPrenom(resultSet.getString("prenom"));
    	carte.setNom(resultSet.getString("nom"));
    	carte.setIdCompte(resultSet.getInt("idCompte"));
        return carte;
    }
}
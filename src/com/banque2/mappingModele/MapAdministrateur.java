package com.banque2.mappingModele;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.banque2.modele.PojoAdministrateur;

/**
 * Permet de transformet un resultat sql en objet modele administrateur
 *
 */
public final class MapAdministrateur implements RowMapper<PojoAdministrateur> {

    public PojoAdministrateur mapRow(ResultSet resultSet, int element) throws SQLException {
        PojoAdministrateur administrateur = new PojoAdministrateur(
        		resultSet.getInt("id"),
        		resultSet.getString("nom"),
        		resultSet.getString("prenom"),
        		resultSet.getString("mdp"));
        return administrateur;
    }
	
}

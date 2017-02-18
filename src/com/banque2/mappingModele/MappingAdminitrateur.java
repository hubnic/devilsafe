package com.banque2.mappingModele;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.banque2.modele.PojoAdministrateur;

/**
 * Permet de transformet un resultat sql en objet modele administrateur
 *
 */
public final class MappingAdminitrateur implements RowMapper<PojoAdministrateur> {

    public PojoAdministrateur mapRow(ResultSet resultSet, int element) throws SQLException {
        PojoAdministrateur administrateur = new PojoAdministrateur();
        administrateur.setId(resultSet.getInt("id"));
        administrateur.setNom(resultSet.getString("nom"));
        administrateur.setPrenom(resultSet.getString("prenom"));
        return administrateur;
    }
	
}

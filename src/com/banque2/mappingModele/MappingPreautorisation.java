package com.banque2.mappingModele;

import com.banque2.modele.PojoPreautorisation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MappingPreautorisation implements RowMapper<PojoPreautorisation> {

	@Override
	public PojoPreautorisation mapRow(ResultSet resultSet, int element) throws SQLException {
		PojoPreautorisation preautorisation = new PojoPreautorisation();
		preautorisation.setCredit_id(resultSet.getString("credit_id"));
		preautorisation.setCredit_expiration(resultSet.getString("credit_expiration"));
		preautorisation.setCredit_nom(resultSet.getString("credit_nom"));
		preautorisation.setCredit_prenom(resultSet.getString("credit_prenom"));
		preautorisation.setCredit_cvs(resultSet.getInt("credit_cvs"));
		preautorisation.setSource_id(resultSet.getString("source_id"));
		preautorisation.setMontant(resultSet.getDouble("montant"));
		preautorisation.setPreauth_id(resultSet.getInt("preauth_id"));
		preautorisation.setPreauthStatus(resultSet.getString("preauth_status"));
		preautorisation.setPreauth_date(resultSet.getTimestamp("date").toLocalDateTime());
		return preautorisation;
	}

}

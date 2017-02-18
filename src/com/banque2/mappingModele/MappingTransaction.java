package com.banque2.mappingModele;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.banque2.modele.PojoTransaction;

public final class MappingTransaction implements RowMapper<PojoTransaction> {

    public PojoTransaction mapRow(ResultSet resultSet, int element) throws SQLException {
    	PojoTransaction transaction = new PojoTransaction();
    		transaction.setIdTransaction(resultSet.getInt("idTransaction"));
    		transaction.setIdCompteClient(resultSet.getInt("idCompteClient"));
    		transaction.setIdCompteDestinataire(resultSet.getInt("idCompteDestinataire"));
    		transaction.setMontant(resultSet.getInt("montant"));
    		transaction.setDate(resultSet.getString("date"));
    		transaction.setDescription(resultSet.getString("description"));
        return transaction;
    }
}
package com.banque2.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.banque2.mappingModele.MapAdministrateur;
import com.banque2.modele.PojoAdministrateur;



public class ServiceDaoAdministrateur {
	
	
private JdbcTemplate jdbcTemplate;
	
	public ServiceDaoAdministrateur(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void saveOrUpdate(PojoAdministrateur administrateur) {
		
			String sql = "SELECT * FROM ADMINISTRATEUR WHERE id= 1";
			jdbcTemplate.execute(sql);
	}
	
	public PojoAdministrateur getAdministrateur(int adminId) {
		String sql = "SELECT * FROM administrateur WHERE id=" + adminId;
		
		List<PojoAdministrateur> result = jdbcTemplate.query(sql,new MapAdministrateur());
		
		if(result.isEmpty()){
			return null;
		}
		else{
			return result.get(0);
		}	

	}
	

}

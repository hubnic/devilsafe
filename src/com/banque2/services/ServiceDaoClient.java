package com.banque2.services;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.banque2.mappingModele.MappingAdministrateur;
import com.banque2.modele.PojoAdministrateur;
import com.banque2.modele.PojoClient;

public class ServiceDaoClient {
	
	private JdbcTemplate jdbcTemplate;
	
	public ServiceDaoClient(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void create(PojoClient client) {
		
		String sql = "";
		jdbcTemplate.execute(sql);
}

public void update(PojoAdministrateur administrateur) {
	
	String sql = "";
	jdbcTemplate.execute(sql);
}

public void delete(PojoAdministrateur administrateur) {
	
	String sql = "";
	jdbcTemplate.execute(sql);
}

public PojoAdministrateur getClient(int clientId) {
	
	String sql = "SELECT * FROM client WHERE id=" + clientId;
	
	List<PojoAdministrateur> result = jdbcTemplate.query(sql,new MappingAdministrateur());
	
	if(result.isEmpty()){
		return null;
	}
	else{
		return result.get(0);
	}	

}

}

package com.banque2.services;

import org.springframework.jdbc.core.JdbcTemplate;

import com.banque2.modele.PojoClient;

public class ServiceDaoCompte {

	private JdbcTemplate jdbcTemplate;
	
	public boolean createAccount(PojoClient client, String type){
		String sql ="";
		jdbcTemplate.execute(sql);
		return true;
	}
	public boolean deleteAccount(PojoClient client, String type){
		String sql ="";
		jdbcTemplate.execute(sql);
		return true;
	}
	
}

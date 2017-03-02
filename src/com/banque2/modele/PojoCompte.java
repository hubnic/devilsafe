package com.banque2.modele;

import java.util.List;

public class PojoCompte {
	
	private int idCompte;
	private String idBanque;
	private String type;
	private float solde;
	private int idClient;
	private List<PojoTransaction> transactions;
	private List<PojoPreautorisation> preautorisation;
	

	public PojoCompte() {}
	public int getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public float getSolde() {
		return solde;
	}
	public void setSolde(float solde) {
		this.solde = solde;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}


	
	public String getIdBanque() {
		return idBanque;
	}
	public void setIdBanque(String idBanque) {
		this.idBanque = idBanque;
	}

	public List<PojoPreautorisation> getPreautorisation() {
		return preautorisation;
	}
	public void setPreautorisation(List<PojoPreautorisation> preautorisation) {
		this.preautorisation = preautorisation;
	}
	@Override
	public String toString() {
		return "PojoCompte [idCompte=" + idCompte + ", idBanque=" + idBanque + ", type=" + type + ", solde=" + solde
				+ ", idClient=" + idClient + ", transactions=" + transactions + ", preautorisation=" + preautorisation
				+ "]";
	}
	public List<PojoTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<PojoTransaction> transactions) {
		this.transactions = transactions;
	}
	
	

}

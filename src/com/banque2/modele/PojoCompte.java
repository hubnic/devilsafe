package com.banque2.modele;

import java.util.List;

public class PojoCompte {
	
	private int idCompte;
	private String type;
	private int numCarte;
	private double solde;
	private int idClient;
	private List<PojoTransaction> transactions;
	
	

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
	public int getNumCarte() {
		return numCarte;
	}
	public void setNumCarte(int numCarte) {
		this.numCarte = numCarte;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	@Override
	public String toString() {
		return "PojoCompte [idCompte=" + idCompte + ", type=" + type + ", numCarte=" + numCarte + ", solde=" + solde
				+ ", idClient=" + idClient + "]";
	}
	
	public List<PojoTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<PojoTransaction> transactions) {
		this.transactions = transactions;
	}
	
	

}

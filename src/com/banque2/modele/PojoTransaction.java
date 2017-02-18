package com.banque2.modele;

public class PojoTransaction {
	
	private int idTransaction;
	private int idCompteClient;
	private int idCompteDestinataire;
	private double montant;
	private String date;
	private String description;
	
	public PojoTransaction() {}
	
	public int getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}
	public int getIdCompteClient() {
		return idCompteClient;
	}
	public void setIdCompteClient(int idCompteClient) {
		this.idCompteClient = idCompteClient;
	}

	public int getIdCompteDestinataire() {
		return idCompteDestinataire;
	}

	public void setIdCompteDestinataire(int idCompteDestinataire) {
		this.idCompteDestinataire = idCompteDestinataire;
	}

	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PojoTransaction [idTransaction=" + idTransaction + ", idCompteClient=" + idCompteClient
				+ ", idCompteDestinataire=" + idCompteDestinataire + ", montant=" + montant + ", date=" + date
				+ ", description=" + description + "]";
	}


	
	
}

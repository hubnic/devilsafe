package com.banque2.modele;

public class PojoCarte {
	
	private int numCarte;
	private String dateExp;
	private int crypto;
	private String prenom;
	private String nom;
	private int idCompte;
	
	public PojoCarte() {
	}

	@Override
	public String toString() {
		return "PojoCarte [numCarte=" + numCarte + ", dateExp=" + dateExp + ", crypto=" + crypto + ", prenom=" + prenom
				+ ", nom=" + nom + ", idCompte=" + idCompte + "]";
	}

	public int getNumCarte() {
		return numCarte;
	}

	public void setNumCarte(int numCarte) {
		this.numCarte = numCarte;
	}

	public String getDateExp() {
		return dateExp;
	}

	public void setDateExp(String dateExp) {
		this.dateExp = dateExp;
	}

	public int getCrypto() {
		return crypto;
	}

	public void setCrypto(int crypto) {
		this.crypto = crypto;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	
}

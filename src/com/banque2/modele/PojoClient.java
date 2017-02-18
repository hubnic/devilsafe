package com.banque2.modele;

public class PojoClient {
	
	private int identifiant;
	private String nom;
	private String prenom;
	private String courriel;
	private String dateNaissance;
	private String telephone;
	private String adresse;
	private String mdp;
	private String prefixe;


	
	
	public PojoClient(){}




	public PojoClient(int id, String nom, String prenom, String courriel, String dateNaissance, String telephone,
			String adresse, String mdp, String prefixe) {
		this.identifiant = id;
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.adresse = adresse;
		this.mdp = mdp;
		this.prefixe = prefixe;
	}

	public PojoClient(int id, String nom, String prenom, String courriel, String dateNaissance, String telephone,
			String adresse) {
		this.identifiant = id;
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.adresse = adresse;
	}



	@Override
	public String toString() {
		return "PojoClient [id=" + identifiant + ", nom=" + nom + ", prenom=" + prenom + ", courriel=" + courriel
				+ ", dateNaissance=" + dateNaissance + ", telephone=" + telephone + ", adresse=" + adresse + ", mdp="
				+ mdp + ", prefixe=" + prefixe + "]";
	}




	public int getIdentifiant() {
		return identifiant;
	}




	public void setId(int id) {
		this.identifiant = id;
	}




	public String getNom() {
		return nom;
	}




	public void setNom(String nom) {
		this.nom = nom;
	}




	public String getPrenom() {
		return prenom;
	}




	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}




	public String getCourriel() {
		return courriel;
	}




	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}




	public String getDateNaissance() {
		return dateNaissance;
	}




	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}




	public String getTelephone() {
		return telephone;
	}




	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}




	public String getAdresse() {
		return adresse;
	}




	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}




	public String getMdp() {
		return mdp;
	}




	public void setMdp(String mdp) {
		this.mdp = mdp;
	}




	public String getPrefixe() {
		return prefixe;
	}




	public void setPrefixe(String prefixe) {
		this.prefixe = prefixe;
	}
	

}

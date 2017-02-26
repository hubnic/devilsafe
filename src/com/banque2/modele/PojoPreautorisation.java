package com.banque2.modele;

/**
 * Created by tinic on 2/26/17.
 */
public class PojoPreautorisation {

    private String credit_id;
    private String credit_expiration;
    private String credit_nom;
    private String credit_prenom;
    private int credit_cvs;
    private String source_id;
    private double montant;
    private int preauth_id;

    public PojoPreautorisation() {}

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public String getCredit_expiration() {
        return credit_expiration;
    }

    public void setCredit_expiration(String credit_expiration) {
        this.credit_expiration = credit_expiration;
    }

    public String getCredit_nom() {
        return credit_nom;
    }

    public void setCredit_nom(String credit_nom) {
        this.credit_nom = credit_nom;
    }

    public String getCredit_prenom() {
        return credit_prenom;
    }

    public void setCredit_prenom(String credit_prenom) {
        this.credit_prenom = credit_prenom;
    }

    public int getCredit_cvs() {
        return credit_cvs;
    }

    public void setCredit_cvs(int credit_cvs) {
        this.credit_cvs = credit_cvs;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getPreauth_id() {
        return preauth_id;
    }

    public void setPreauth_id(int preauth_id) {
        this.preauth_id = preauth_id;
    }

    @Override
    public String toString() {
        return "PojoPreautorisation{" +
                "credit_id='" + credit_id + '\'' +
                ", credit_expiration='" + credit_expiration + '\'' +
                ", credit_nom='" + credit_nom + '\'' +
                ", credit_prenom='" + credit_prenom + '\'' +
                ", credit_cvs=" + credit_cvs +
                ", source_id='" + source_id + '\'' +
                ", montant=" + montant +
                '}';
    }
}

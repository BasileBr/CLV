package fr.essant.basilebyvanu.clv.Objet;

public class Chapitre {

    private int temps;
    private String nom;
    private int numero;
    private String lien;



    public Chapitre(String nom, int temps, int numero, String lien) {
        this.temps = temps;
        this.nom = nom;
        this.numero = numero;
        this.lien = lien;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }
}

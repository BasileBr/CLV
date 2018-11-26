package fr.essant.basilebyvanu.clv.Objet;

public class Chapitre {

    private int temps;
    private String nom;
    private int numero;



    public Chapitre(String nom, int temps, int numero) {
        this.temps = temps;
        this.nom = nom;
        this.numero = numero;
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
}

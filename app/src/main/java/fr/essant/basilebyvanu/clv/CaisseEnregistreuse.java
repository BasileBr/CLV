package fr.essant.basilebyvanu.clv;

public class CaisseEnregistreuse {

    private int total;


    public CaisseEnregistreuse() {
        total = 0;
    }

    public void empty(){
        total = 0;
    }

    public int add(int nombre){
        total = total + nombre;
        return total;
    }
    public int sub(int nombre){
        total = total - nombre;
        return total;
    }
    public int mult(int nombre){
        total = total * nombre;
        return total;
    }
    public int div(int nombre){
        total = total / nombre;
        return total;
    }
}

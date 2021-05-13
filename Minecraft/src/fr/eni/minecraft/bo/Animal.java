package fr.eni.minecraft.bo;

public class Animal extends Pnj{

    //Defini pour un animal sa pension actuelle
    //Pas dans le constructeur car quand on cree un animal il n'a pas forcement de pension
    private Pension pensionActuelle;
    /**
     * Construteur d'objet venant du parent Pnj
     * @param atk point d'attaque
     * @param pdv point de vie
     */
    public Animal(String nom, int atk, int pdv) {
        super(nom,atk, pdv);
    }

    //Si c'est un Animal la FONCTION abstraite sera differente de Monstre
    @Override
    public void direBonjour() {
        System.out.println("Bonjour");
    }

    protected void setPensionActuelle(Pension pension){
        this.pensionActuelle = pension;
    }

    public Pension getPensionActuelle() {
        return pensionActuelle;
    }
}

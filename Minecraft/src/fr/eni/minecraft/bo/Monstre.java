package fr.eni.minecraft.bo;

public class Monstre extends Pnj {
    //Tous les Monstres auront 3 de speed
    private static int speed = 3;

    /**
     * Construteur d'objet venant du parent Pnj
     * @param atk point d'attaque
     * @param pdv point de vie
     */
    public Monstre(String nom, int atk, int pdv) {
        super(nom, atk, pdv);
    }
    //Si c'est un Monstre la FONCTION abstraite sera differente de Animal
    @Override
    public void direBonjour() {
        System.out.println("GWEEWFBEF");
    }


}

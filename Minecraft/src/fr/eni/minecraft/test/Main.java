package fr.eni.minecraft.test;

import fr.eni.minecraft.bo.Animal;
import fr.eni.minecraft.bo.Monstre;
import fr.eni.minecraft.bo.Pension;

public class Main {

    public static void main(String[] args) {

        //Creation D'OBJET MONSTRE
        Monstre araignee = new Monstre("Spide",3,10);
        Monstre zombie = new Monstre("Zombie",2,15);
        Animal vache = new Animal("Cow", 10 ,100);
        Animal cochon = new Animal("Cochon", 10, 20);


        Pension garderie = new Pension();
        garderie.ajouter(vache);
        garderie.ajouter(cochon);

        //Ici la fonction ToSting de Pension est pris en logique par Java mais garderie.toSting() fonctionne aussi
        System.out.println(garderie);

        // Demande ou se trouve vache (dans quelle pension)
        System.out.println(vache.getPensionActuelle());

        araignee.direBonjour();
        vache.direBonjour();
    }


}

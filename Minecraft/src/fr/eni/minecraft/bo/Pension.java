package fr.eni.minecraft.bo;

import java.util.ArrayList;

public class Pension {

    //Cree un objet tableau sans limit
    //Peut utiliser Animal car elle est dans le meme PACKAGE
    private ArrayList<Animal> tabPension  ;

    //Cree un tableau vide
    public Pension() {
        this.tabPension =  new ArrayList<Animal>();
    }

    //Cree une FONCTION pour faire garderie.ajouter(vache)
    public void ajouter(Animal animal){
        this.tabPension.add(animal);
        animal.setPensionActuelle(this);
    }



    //Atention utilisation de StringBuilder !
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append( "Pension : \n");
        //Pour chaque Animal dans mon tableau je veux que tu append a StringBuilder a
        //Donc pour Chaque a dans tabPension tu m'ajoutes  a au StringBuilder
        for (Animal a : tabPension ) {
            str.append(a.toString() + "\n");
        }
        return str.toString();
    }
}

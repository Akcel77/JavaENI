package fr.eni.minecraft.bo;

public abstract class Pnj {
    //Abtract empeche de creer un objet Pnj

    //attribut de classe pouvant etre modifiees lors de la creation d'objet
    // Attention si on cree des attributs dans une class parent
    // On doit pouvoir les modifier dans une class enfant donc en PROTECTED
    protected int atk;
    protected int pdv;
    protected String nom;

    protected Pnj(String nom, int atk, int pdv) {
        this.nom = nom;
        this.atk = atk;
        this.pdv = pdv;
    }

    //GETTERS
    //Recupere les infos en foncion de l'instance presente
    //zombie.getAtk() => recupere l'attaque de zombie
    public int getAtk() {
        return atk;
    }
    public int getPdv() {
        return pdv;
    }

    public String getNom() {
        return nom;
    }

    //SETTERS
    //Modifie les infos en fonction de l'instance presente
    //zombie.setAtk(20) => Modifie l'attaque de l'instance presente a 20
    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    //FONCTION
    //Permet de mettre a jour les pdv du pnj attaquer - l'attaque du pnj attaquant
    public void attaque(Pnj pnj){
        pnj.setPdv( pnj.getPdv() - this.getAtk() );
        System.out.println(this.getNom() + " attaque " + pnj.getNom());
        if (pnj.getPdv() <= 0){
            System.out.println("T'es Mort");
        }
    }
    //Affiche pour chaque PNJ nom , atk , pdv
    @Override
    public String toString() {
        return String.format("%s  : ATK : %d / PDV :%d ", this.getNom(), this.getAtk() ,this.getPdv());
    }


    public abstract void direBonjour();
}

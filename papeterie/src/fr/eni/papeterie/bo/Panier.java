package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private List<Ligne> lignesPanier = new ArrayList<>();
    private float montant;

    public Panier() {
    }

    //GETTER AND SETTER
    public List<Ligne> getLignesPanier() {
        return lignesPanier;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    /**
     *
     * @param id
     * @return une ligne en fonction de l'id
     */
    public Ligne getLigne(int id){
        return lignesPanier.get(id);
    }

    /**
     * Ajoute une ligne contenant article et qte
     * @param article
     * @param qte
     */
    public void addLigne(Article article, int qte){
        Ligne ligneAdd = new Ligne(article, qte);
        lignesPanier.add(ligneAdd);
    }

    /**
     * Recupere l'id de la ligne et set un nouveau montant
     * @param id
     * @param newQte nouveau montant
     */
    public void updateLigne(int id, int newQte){
        this.getLigne(id).setQte(newQte);
    }

    /**
     * Recupere une ligne en fonction de son id puis la supprime
     * @param id
     */
    public void removeLigne(int id){
        lignesPanier.remove(id);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Panier : \n");
        for (Ligne ligne :lignesPanier){
            if(ligne!= null){
                str.append("ligne " + lignesPanier.indexOf(ligne) + " :\t").append(ligne.toString()).append("\n");
            }else break;
        }
        str.append("\nValeur du panier : " + getMontant()).append("\n\n");
        return str.toString();
    }
}

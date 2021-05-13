package fr.eni.papeterie.bo;

public class Stylo extends Article{

    public static final String TYPE_STYLO = "Stylo";
    private String couleur;

    public Stylo() {
    }

    public Stylo(Integer idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock, String couleur) {
        super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
        setCouleur(couleur);
    }
    public Stylo(String reference, String marque, String designation, float prixUnitaire, int qteStock, String couleur) {
        super(reference, marque, designation, prixUnitaire, qteStock);
        setCouleur(couleur);
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return String.format("%s Stylo [Couleur=%s]", super.toString(), getCouleur());
    }
}

package fr.eni.papeterie.bo;

public class Ramette extends Article{

    public static final String TYPE_RAMETTE = "Ramette";
    private int grammage;

    public Ramette(){
    }

    public Ramette(Integer idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock, int grammage) {
        super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
        setGrammage(grammage);
    }

    public Ramette(String reference, String marque, String designation, float prixUnitaire, int qteStock, int grammage) {
        super(reference, marque, designation, prixUnitaire, qteStock);
        setGrammage(grammage);
    }



    public int getGrammage() {
        return grammage;
    }

    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }

    @Override
    public String toString() {

        return String.format("%s Ramette [Grammage=%s]", super.toString(), getGrammage())+ "\n";


    }
}

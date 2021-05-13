package fr.eni.papeterie.bo;

public abstract class Article {
    protected Integer idArticle;
    protected String reference;
    protected String marque;
    protected String designation;
    protected float prixUnitaire;
    protected int qteStock;

    public Article() {
    }

    public Article(String reference, String marque, String designation, float prixUnitaire, int qteStock) {
        this.reference = reference;
        this.marque = marque;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.qteStock = qteStock;
    }

    public Article(Integer idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock) {
        this.idArticle = idArticle;
        this.reference = reference;
        this.marque = marque;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.qteStock = qteStock;
    }

    //GETTERS AND SETTERS
    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    /**
     * To String
     * @return A String
     * idArticle = ID
     * reference = reference
     * marque = marque
     */
    @Override
    public String toString() {
        return "Article [idArticle="+ idArticle +
                ", \treference=" + reference +
                ", \tmarque=" + marque +
                ", \tdesignation=" + designation+
                ", \tprixUnitaire=" + prixUnitaire +
                ", \tqteStock=" + qteStock + "]";
    }
}

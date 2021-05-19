package fr.eni.papeterie.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Article {
    protected Integer idArticle;
    protected String reference;
    protected String marque;
    protected String designation;
    protected float prixUnitaire;
    protected int qteStock;

    public Article() {
    }

    public Article(Integer idArticle,String marque, String reference,  String designation, float prixUnitaire, int qteStock) {
        this.idArticle = idArticle;
        this.marque = marque;
        this.reference = reference;

        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.qteStock = qteStock;
    }

    public Article(String marque,String reference,  String designation, float prixUnitaire, int qteStock) {
        this.marque = marque;
        this.reference = reference;
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
        return "Article [idArticle="+ idArticle  +
                ", \tmarque=" + marque +
                ", \treference=" + reference +
                ", \tdesignation=" + designation+
                ", \tprixUnitaire=" + prixUnitaire +
                ", \tqteStock=" + qteStock + "]";
    }
}

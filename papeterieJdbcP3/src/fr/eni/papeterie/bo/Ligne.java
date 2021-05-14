package fr.eni.papeterie.bo;

public class Ligne {
    protected int qte;
    protected Article article;

    public Ligne( Article article,int qte) {
        this.qte = qte;
        this.article = article;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public float getPrix()
    {
        return article.getPrixUnitaire();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Ligne [").append(" qte=").append(getQte()).append(", prix=").append(getPrix()).append(", ");
        if (article != null) {
            str.append("article=");
            str.append(getArticle().toString());
        }
        str.append("]");
        return str.toString();
    }
}

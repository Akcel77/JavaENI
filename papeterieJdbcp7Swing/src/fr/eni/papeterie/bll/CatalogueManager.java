package fr.eni.papeterie.bll;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAO;
import fr.eni.papeterie.dal.DAOFactory;

import java.util.List;

public class CatalogueManager {
    //LIAISON ENTRE IHM ET DAL


    private static DAO<Article> articleDAO;

    /**
     * Instatiation de notre articleDAO
     * @throws BLLException / message
     */
    public CatalogueManager() throws BLLException {
        articleDAO = DAOFactory.getArticleDAO();
    }


    /**
     * Recupere la liste des articles du catalogues
     * @return les articles
     * @throws BLLException / message
     */
    public List<Article> getCatalogue() throws BLLException{
        List<Article> articles = null;
        try {
            articles = articleDAO.selectAll();
        }catch (DALException e){
            e.printStackTrace();
            throw new BLLException("Probleme de recuperation ", e);
        }
        return articles;
    }


    /**
     * Check si les attributs sont pas null pour update un objet
     * @param article article a valider
     * @throws BLLException / message
     */
    public void validerArticle(Article article) throws BLLException{
        boolean isValide = true;
        StringBuilder str = new StringBuilder();

        //1er Verif si l'article est ok
        if (article==null){
            throw new BLLException("Article inconnu");
        }

        if(article.getReference() == null ){
            str.append("Merci d'ajouter une reference article" + "\n");
            isValide = false;
        }
        if(article.getMarque() == null ){
            str.append("Merci d'ajouter une marque article"+ "\n");
            isValide = false;
        }
        if(article.getDesignation() == null ){
            str.append("Merci d'ajouter une designation article"+ "\n");
            isValide = false;
        }
        if(article instanceof Ramette && ((Ramette)article).getGrammage() <=0){
            str.append("Merci d'ajouter une valeur de grammage positive"+ "\n");
            isValide = false;
        }
        if(article instanceof Stylo){
            //ici on cast article Stylo
            Stylo s = (Stylo) article;
            if(s.getCouleur()==null){
                str.append("Merci d'ajouter une couleur de Stylo" + "\n");
                isValide = false;
            }
        }
        if(!isValide){
            throw new BLLException(str.toString());
        }
    }

    /**
     * Verifie si un article a tous les criteres pour etre update puis l'update
     * @param article a mettre a jour
     * @throws BLLException / message
     */
    public void updateArticle(Article article) throws BLLException{
        try {
            validerArticle(article);
            articleDAO.update(article);
        }catch (DALException e){
            throw new BLLException("Update de l'article impossible :" + article, e);
        }
    }

    /**
     * Ajoute un article si l'id de celui ci n'est pas deja reference
     * @param article a ajouter
     * @throws BLLException / message
     */
    public void addArticle(Article article) throws BLLException{

        try{
            validerArticle(article);
            articleDAO.insert(article);
        }catch (DALException e){
            throw new BLLException("Ajout d'article echoue", e);
        }
    }

    public void removeArticle(Article article) throws BLLException{
        try{
            articleDAO.delete(article);
        }catch (DALException e){
            throw new BLLException("Suppression de l'article impossible :", e);
        }
    }

}

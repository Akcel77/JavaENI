package fr.eni.papeterie.dal;

import fr.eni.papeterie.bo.Article;


public class DAOFactory {

    public static DAO<Article> getArticleDAO(){
        DAO<Article> articleDAO=null;

        try{
            articleDAO=(DAO<Article>) Class.forName("fr.eni.papeterie.dal.jdbc.ArticleDAOJdbcImpl").newInstance();
        }catch (IllegalAccessException | ClassNotFoundException | InstantiationException e){
            e.printStackTrace();
        }
        return  articleDAO;
    }
}

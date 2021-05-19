package fr.eni.papeterie.dal;

import fr.eni.papeterie.bo.Article;

import java.util.List;

public interface ArticleDAO {
    //Select un article par son id
    public Article selectById(int id) throws DALException;

    //Select tout les articles
    public List<Article> selectAll() throws DALException;

    //Modif un article
    public void update(Article data) throws DALException;

    //Insert un nouvel article
    public void insert(Article data) throws DALException;

    //Delete un article
    public void delete(int id) throws DALException;
}

package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl implements DAO<Article> {
    // REQUETE SQL
    private static final String sqlSelectById = "SELECT * FROM `dbo.articles` WHERE `idArticle` = ?";
    private static final String sqlSelectAll = "SELECT * FROM `dbo.articles`";
    private static final String sqlUpdate = "UPDATE `dbo.articles` SET reference =? , marque =?, designation =?, prixUnitaire =?, qteStock =?, couleur =?, grammage =? WHERE idArticle =?";
    private static final String sqlInsert = "INSERT INTO `dbo.articles`(reference, marque, designation, prixUnitaire, qteStock, couleur, grammage, type)  VALUES(?,?,?,?,?,?,?,?)";
    private static final String sqlDelete = "DELETE FROM `dbo.articles` WHERE `idArticle` =?";


    private static final String TYPE_STYLO = "STYLO";
    private static final String TYPE_RAMETTE = "RAMETTE";



    /**
     * Select dans la DB un article par son identifiant
     * Puis parcours chaque valeur de cet identifiant
     * @param id identifiant de l'article
     * @return chaque valeurs de l'article choisi
     */
    @Override
    public Article selectById(Article id) throws DALException {
        Connection myConn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Article article = null;

        try {
            //Preparation du statement
            myConn = JdbcTools.getConnection();
            stmt = myConn.prepareStatement(sqlSelectById);

            //Ajout des valeurs
            stmt.setInt(1, id.getIdArticle());
            //Recuperation du resultat
           rs = stmt.executeQuery();

            // Recuperation de chaque valeur de l'id
            while(rs.next()){
                int idArticle = rs.getInt("idArticle");
                String reference = rs.getString("reference");
                String marque = rs.getString("marque");
                String designation = rs.getString("designation");
                float pu = rs.getFloat("prixUnitaire");
                int qte = rs.getInt("qteStock");
                String couleur = rs.getString("couleur");
                int grammage = rs.getInt("grammage");
                String type = rs.getString("type");


                if(rs.getString(8) != null) {
                    article = (new Stylo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5),
                            rs.getInt(6), rs.getString(8)));
                } else {
                    article = (new Ramette(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5),
                            rs.getInt(6), rs.getInt(7)));
                }
            }
        }catch(SQLException e){
            throw new DALException("selectById failed - id = " + id, e);
        }finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (stmt != null){
                    stmt.close();
                }
                if(myConn!=null){
                    myConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return article;
    }

    /**
     * Select dans la DB tout article
     * @return chaque valeurs de du tableau cree
     */
    @Override
    public List<Article> selectAll() throws DALException {
        Connection myConn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Article> articlesList = new ArrayList<>();

        try {
            myConn = JdbcTools.getConnection();
            //Creation du statement
            stmt = myConn.createStatement();
            rs = stmt.executeQuery(sqlSelectAll);
            Article article = null;

            // Recuperation de chaque valeur de l'id
            while (rs.next()) {
                if (rs.getString(8) != null) {
                    article = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(),
                            rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
                            rs.getString("couleur"));
                }
                else{
                    article = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(),
                            rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
                            rs.getInt("grammage"));
                }

                articlesList.add(article);
            }
        }catch(SQLException e){
            throw new DALException("selectAll failed - ", e);
        }finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (stmt != null){
                    stmt.close();
                }
                if(myConn!=null){
                    myConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return articlesList;
    }


    /**
     * Modifie les attributs d’un article
     * @param article a update
     */
    @Override
    public void update(Article article) throws DALException {
        Connection myConn =null;
        PreparedStatement stmt = null;

        try{
            //Preparation du statement
            myConn = JdbcTools.getConnection();
            stmt = myConn.prepareStatement(sqlUpdate);

            //ID par valeurs
            stmt.setString(1,article.getReference());
            stmt.setString(2,article.getMarque());
            stmt.setString(3,article.getDesignation());
            stmt.setFloat(4,article.getPrixUnitaire());
            stmt.setInt(5,article.getQteStock());
            stmt.setInt(8, article.getIdArticle());


            // En fonction de l'article
            if (article instanceof Stylo){
                Stylo s = (Stylo) article;
                stmt.setString(6,(s.getCouleur()));
                stmt.setString(7, null);
            }
            if (article instanceof Ramette){
                Ramette r = (Ramette) article;
                stmt.setString(6, null);
                stmt.setInt(7,(r.getGrammage()));
            }
            //Mise a jour de l'article
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new DALException("Update article failed - " + article, e);
        }finally {
            try {
                if (stmt != null){
                    stmt.close();
                }
                if(myConn!=null){
                    myConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Ajout d'artcile
     * @param article represente l'article a ajouter
     */
    @Override
    public void insert(Article article) throws DALException {
        Connection myConn = null;
        PreparedStatement stmt = null;

        try{
            myConn = JdbcTools.getConnection();
            stmt = myConn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            //ID par valeurs
            stmt.setString(1,article.getReference());
            stmt.setString(2,article.getMarque());
            stmt.setString(3,article.getDesignation());
            stmt.setFloat(4,article.getPrixUnitaire());
            stmt.setInt(5,article.getQteStock());

            // En fonction de l'article
            if (article instanceof Stylo) {
                Stylo s = (Stylo) article;
                stmt.setString(6, (s.getCouleur()));
                stmt.setString(7, null);
                stmt.setString(8, TYPE_STYLO);
            }
            if (article instanceof Ramette){
                Ramette r = (Ramette) article;
                stmt.setString(6, null);
                stmt.setInt(7,(r.getGrammage()));
                stmt.setString(8, TYPE_RAMETTE);
            }

            //Update ID
            int row = stmt.executeUpdate();
            if (row == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    article.setIdArticle(rs.getInt(1));
                }
            }

        }catch(SQLException e){
            throw new DALException("Insert article failed - " + article, e);
        }finally {
            try {
                if (stmt != null){
                    stmt.close();
                }
                if(myConn!=null){
                    myConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(Article id) throws DALException {
        Connection myConn = null;
        PreparedStatement stmt = null;
        try{
            //Preparation du statement
            myConn = JdbcTools.getConnection();
            stmt = myConn.prepareStatement(sqlDelete);
            //Ajout des valeurs
            stmt.setInt(1,id.getIdArticle());
            //Recupere le nombre d'article supprimes
            stmt.executeUpdate();

        }catch (SQLException e) {
            throw new DALException("Delete article failed - id=" + id, e);
        }finally {
            try {
                if (stmt != null){
                    stmt.close();
                }
                if(myConn!=null){
                    myConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

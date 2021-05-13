package fr.eni.papeterie.dal.jdbc;

import com.sun.xml.internal.bind.v2.TODO;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl {
    private static final String TYPE_STYLO = "STYLO";
    private static final String TYPE_RAMETTE = "RAMETTE";



    //Declaration constante et Variable
    private final String URL = "jdbc:mysql://localhost:3306/papeterie_db";
    private final String LOGIN = "root";
    private final String PWD = "";

    private Connection myConn = null;
    private Statement statement = null;


    //Chargement du driver
     static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //Constructor
    public ArticleDAOJdbcImpl(){
    }

    //Connection a la DB
    public Connection getConnection() throws SQLException {
        if (myConn==null){
            myConn = DriverManager.getConnection(URL, LOGIN, PWD);
        }
        return myConn;
    }

    //Deconnection de la DB
    public void closeConnection(){
        if(myConn!=null){
            try {
                myConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            myConn=null;
        }
    }

    /**
     * Select dans la DB un article par son identifiant
     * Puis parcours chaque valeur de cet identifiant
     * @param id identifiant de l'article
     * @return chaque valeurs de l'article choisi
     */
    public Article selectById(int id) throws DALException {
        Article article = null;
        Connection myConn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        //LIGNE SQL
        String sqlCommand ="SELECT * FROM `dbo.articles` WHERE `idArticle` = ?";

        try {
            //Connection a la DB
            myConn = getConnection();
            //Preparation du statement
            stmt = myConn.prepareStatement(sqlCommand);

            //Ajout des valeurs
            stmt.setInt(1,id);
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
            //Checking de la connection et close si jamais l'operation est fini
            try {
                if (stmt !=null){
                    stmt.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            closeConnection();
        }
        return article;
    }

    /**
     * Select dans la DB tout article
     * @return chaque valeurs de du tableau cree
     */
    public List<Article> selectAll() throws DALException {
        Connection myConn = null;
        Statement stmt = null;
        List<Article> articlesList = new ArrayList<>();
        //LIGNE SQL
        String sql = "SELECT * FROM `dbo.articles`";
        try {
            myConn =getConnection();
            //Creation du statement
            stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
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
            //Checking de la connection et close si jamais l'operation est fini
            try {
                if (stmt !=null){
                    stmt.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            closeConnection();
        }
        return articlesList;
    }


    /**
     * Modifie les attributs d’un article
     * @param article a update
     */
    public void update(Article article) throws DALException {
        Connection myConn = null;
        PreparedStatement stmt = null;


        String sql =    "" +
                        "UPDATE `dbo.articles` " +
                        "SET reference =? , marque =?, designation =?, prixUnitaire =?, qteStock =?, couleur =?, grammage =?" +
                        " WHERE idArticle =?";
        try{
            myConn = getConnection();
            //Preparation du statement
            stmt = myConn.prepareStatement(sql);

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
            //Checking de la connection et close si jamais l'operation est fini
            try {
                if (stmt !=null){
                    stmt.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            closeConnection();
        }
    }

    /**
     * Ajout d'artcile
     * @param article represente l'article a ajouter
     */
    public void insert(Article article) throws DALException {
        Connection myConn = null;
        PreparedStatement stmt = null;
        String sql = "" +
                "INSERT INTO `dbo.articles`(reference, marque, designation, prixUnitaire, qteStock, couleur, grammage, type) " +
                "VALUES(?,?,?,?,?,?,?,?)";


        try{
            myConn = getConnection();
            //Preparation du statement
            stmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //ID par valeurs
            stmt.setString(1,article.getReference());
            stmt.setString(2,article.getMarque());
            stmt.setString(3,article.getDesignation());
            stmt.setFloat(4,article.getPrixUnitaire());
            stmt.setInt(5,article.getQteStock());

            // En fonction de l'article
            if (article instanceof Stylo){
                Stylo s = (Stylo) article;
                stmt.setString(6,(s.getCouleur()));
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
            //Checking de la connection et close si jamais l'operation est fini
            try {
                if (stmt !=null){
                    stmt.close();
                }
            }catch (SQLException e){
                throw new DALException("close failed - ", e);
            }
            closeConnection();
        }

    }

    public void delete(int id) throws DALException {
        Connection myConn = null;
        PreparedStatement stmt = null;

        String sql =    "" +
                        "DELETE FROM `dbo.articles` " +
                        "WHERE idArticle =?";
        try{
            myConn = getConnection();
            //Preparation du statement
            stmt = myConn.prepareStatement(sql);
            //Ajout des valeurs
            stmt.setInt(1,id);
            //Recupere le nombre d'article supprimes
            stmt.executeUpdate();

        }catch (SQLException e) {
            throw new DALException("Delete article failed - id=" + id, e);
        }finally {
            //Checking de la connection et close si jamais l'operation est fini
            try {
                if (stmt !=null){
                    stmt.close();
                }
            }catch (SQLException e){
                throw new DALException("close failed - ", e);
            }
            closeConnection();
        }
    }

}

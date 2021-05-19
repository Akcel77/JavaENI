package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.ihm.ecrCatalogue.EcranCatalogue;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.util.List;

public class ArticleController {

    private EcranArticle ecranArticle;

    //Attributs
    private int idxCatalogue;
    private CatalogueManager manager;
    private List<Article> articles;
    private static ArticleController instance;

    //Constructor

    public ArticleController() {
        try{
            //Instanciation
            manager = new CatalogueManager();
            //Recuperation du Catalogue
            articles = manager.getCatalogue();
        }catch (BLLException e){
            e.printStackTrace();
        }
    }

    //Functions

    /**
     * Instancie l'instance ArticleContreoller if null
     * @return l'instance
     */
    public static synchronized ArticleController get(){
        if (instance == null){
            instance = new ArticleController();
        }
        return instance;
    }

    /**
     * Creation de l'application au niveau de l'ecran
     * Avec affiche du premier Article ref afficherPremierArticle()
     */
    public void startApp() throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        ecranArticle = new EcranArticle();
        afficherPremierArcticle();
        ecranArticle.setVisible(true);

        EcranCatalogue ecranCatalogue = new EcranCatalogue();
        ecranCatalogue.setVisible(true);
    }

    /**
     * Affiche le premier Article si le catalogue a ou moins 1 article ref afficherArticle()
     * Sinon affiche un nouvel article ref afficherNouveau()
     */
    public void afficherPremierArcticle(){
        if(articles.size()>0){
            idxCatalogue = 0;
            ecranArticle.afficherArticle(articles.get(idxCatalogue));
        }else{
            idxCatalogue = -1;
            nouveau();
        }
    }

    /**
     * Affiche l'article precedent si l'index Catalogue est superieur a 0 ref afficherArticle()
     **/
    public void precedent(){
        if(idxCatalogue >0){
            idxCatalogue--;
            ecranArticle.afficherArticle(articles.get(idxCatalogue));
        }
    }

    public void suivant(){
        if(idxCatalogue< articles.size() -1){
            idxCatalogue++;
            ecranArticle.afficherArticle(articles.get(idxCatalogue));
        }
    }

    /**
     * Recupere la taille du catalogue puis affiche le nouvel Article ref afficherNouveau
     */
    public void nouveau(){
        Article article = null;
        ecranArticle.getStyloRadio().setEnabled(true);
        ecranArticle.getRametteRadio().setEnabled(true);
        if(ecranArticle.getRametteRadio().isSelected()){

            article = new Ramette("","","",0.0f,0,0);
        }else{
            article = new Stylo("","","",0.0f,0,"");
        }
        ecranArticle.afficherArticle(article);
    }

    /**
     * Instancie l'objet Article ref getArticle()
     * Si l'objet est null l'ajoute au manager puis au catalogue
     * Sinon Update l'aticle du catalogue
     */
    public void enregistrer(){
        Article articleOnScreen = ecranArticle.getArticle();
        try{
            if(articleOnScreen.getIdArticle()!=null){
                manager.addArticle(articleOnScreen);
                System.out.println("Article : " + articleOnScreen);
                articles.add(articleOnScreen);
                ecranArticle.afficherArticle(articleOnScreen);
            }else{
                manager.addArticle(articleOnScreen);
                articles.add(articleOnScreen);
                articles.set(idxCatalogue  , articleOnScreen );


            }
        }catch (BLLException ee){
            ecranArticle.infoErreur("Article non enregistre");
            ee.printStackTrace();
        }
    }

    /**
     * Supprime un article si celui ci existe,
     * Si l'article est supprime du catalogue recupere l'article precedent et l'affiche  ref afficherArticle()
     * Si aucun article precedent affiche nouvel article ref afficherNouveau()
     */
    public void supprimer(){
        try{
            manager.removeArticle(articles.get(idxCatalogue));
            articles.remove(idxCatalogue);
        }catch (BLLException e){
            ecranArticle.infoErreur("Article non supprime");
        }

        //Recupere l'article precedent si son index est inferieur a la taille du catalogue
        if(idxCatalogue < articles.size()){
            ecranArticle.afficherArticle(articles.get(idxCatalogue));
        }else if(idxCatalogue>0){
            idxCatalogue--;
            ecranArticle.afficherArticle(articles.get(idxCatalogue));
        }else{
            nouveau();
            if(idxCatalogue == 0){
                //1. Create the frame.
                JFrame frame = new JFrame("ATTENTION");

                //2. Optional: What happens when the frame closes?
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                //4. Size the frame.
                frame.pack();

                //5. Show it.
                frame.setVisible(true);
                JOptionPane.showMessageDialog(frame, "Aucun article a supprimer");
            }
        }
    }


    public List<Article> getArticles() {
        return articles;
    }
}


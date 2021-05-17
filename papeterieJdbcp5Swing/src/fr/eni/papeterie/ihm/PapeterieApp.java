package fr.eni.papeterie.ihm;


import javax.swing.*;

public class PapeterieApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){


            //Run l'application
            @Override
            public void run(){
                try {
                    ArticleController.get().startApp();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

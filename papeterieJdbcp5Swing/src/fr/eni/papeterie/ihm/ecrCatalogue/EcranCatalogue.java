package fr.eni.papeterie.ihm.ecrCatalogue;

import fr.eni.papeterie.ihm.ArticleController;

import javax.swing.*;
import java.awt.*;

public class EcranCatalogue extends JFrame {

    public EcranCatalogue(){
        super("Catalogue");

        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setIconImage(tk.getImage((getClass().getResource("../resources/aim.png"))));

        initComposants();
    }

    private void initComposants(){
        JPanel main = new JPanel();
        main.setOpaque(true);

        main.setLayout(new GridLayout(1,0));
        TableCatalogue tableCatalogue = new TableCatalogue(ArticleController.get().getArticles());

        JScrollPane jScrollPane = new JScrollPane(tableCatalogue);

        main.add(jScrollPane);
        this.setContentPane(main);
    }
}

package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcranArticle extends JFrame {


    //Input
    private JTextField txtRef , txtDesignation, txtMarque, txtStck, txtPrice;
    private JRadioButton rametteRadio, styloRadio;
    private JPanel typePanel, grammagePanel;
    private JCheckBox check80, check100;
    private JComboBox<String> couleurCombo;
    private JTextField txtQte;


    //Buttons
    private JPanel btnPanel;
    private JButton btnPrev;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnDel;
    private JButton btnNext;

    //Id en cours
    private Integer idInRun;

    /**
     * Creation du GUI de l'app
     */
    public EcranArticle(){
        //Ferme l'appli sur la X
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Set la location de base
        setLocationRelativeTo(null);
        //Set la taille de notre app
        setSize(500,400);
        //Set la possibilite de resize
        setResizable(false);
        //Set le title
        setTitle("Catalogue article");
        //Initialise notre IHM ref ihmInit
        ihmInit();
    }

    private void ihmInit(){
        //Instanciation du nouvel objet de Jpanel
        JPanel jPanel = new JPanel();
        //Utilisation du Layout GridBag
        jPanel.setLayout(new GridBagLayout());
        //Instantiation de GridBagConstrains
        GridBagConstraints gbc = new GridBagConstraints();

        //Creation du tableau
        gbc.insets = new Insets(5,5,5,5);

        //Row 1 en 0 0 avec le label Ref
        gbc.gridy = 0;
        gbc.gridx = 0;
        jPanel.add(new JLabel("Ref"),gbc);
        gbc.gridx = 1;
        jPanel.add(getTxtRef(),gbc);

        //Row 2 en 1 0 avec le label Designation
        gbc.gridy = 1;
        gbc.gridx = 0;
        jPanel.add(new JLabel("Designation"), gbc);
        gbc.gridx=1;
        jPanel.add(getTxtDesignation(),gbc);

        //Row 3 en 2 0 avec le label Marque
        gbc.gridy = 2;
        gbc.gridx = 0;
        jPanel.add(new JLabel("Marque"),gbc);
        gbc.gridx=1;
        jPanel.add(getTxtMarque(),gbc);

        //Row 4 en 3 0 avec le label Stock
        gbc.gridy = 3;
        gbc.gridx = 0;
        jPanel.add(new JLabel("Stock"),gbc);
        gbc.gridx=1;
        jPanel.add(getTxtStck(),gbc);

        //Row 5 en 4 0 avec le label Type
        gbc.gridy = 4;
        gbc.gridx = 0;
        jPanel.add(new JLabel("Type"),gbc);
        gbc.gridx=1;
        gbc.gridheight = 1;
        jPanel.add(getTypePanel(),gbc);

        //Row 6 en 5 0 avec le label Grammage
        gbc.gridy = 5;
        gbc.gridx = 0;
        jPanel.add(new JLabel("Grammage"),gbc);
        gbc.gridx=1;
        jPanel.add(getGrammagePanel(),gbc);

        //Row 7 en 6 0 avec le label Couleur
        gbc.gridy = 6;
        gbc.gridx = 0;
        jPanel.add(new JLabel("Couleur"),gbc);
        gbc.gridx=1;
        jPanel.add(getCouleurCombo(),gbc);

        //Row 8 ref to getBtnPanel() en 7 0
        gbc.gridy =7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        jPanel.add(getBtnPanel(), gbc);

        setContentPane(jPanel);
    }


    //GETTERS
    /**
     *
     * @return text reference
     */
    public JTextField getTxtRef() {
        if(txtRef == null){
            txtRef = new JTextField(30);
        }
        return txtRef;
    }

    /**
     *
     * @return text Designation
     */
    public JTextField getTxtDesignation() {
        if(txtDesignation == null){
            txtDesignation = new JTextField(30);
        }
        return txtDesignation;
    }

    /**
     *
     * @return text marque
     */
    public JTextField getTxtMarque() {
        if ( txtMarque == null){
            txtMarque = new JTextField(30);
        }
        return txtMarque;
    }

    /**
     *
     * @return text stock
     */
    public JTextField getTxtStck() {
        if( txtStck == null){
            txtStck = new JTextField(30);
        }
        return txtStck;
    }

    /**
     *
     * @return txt price
     */
    public JTextField getTxtPrice() {
        if ( txtPrice == null){
            txtPrice = new JTextField(30);
        }
        return txtPrice;
    }

    /**
     * Si Ramette est null instancie ramette et disable couleur
     * @return radioButton ramette
     */
    public JRadioButton getRametteRadio() {
        if(rametteRadio == null ){
            rametteRadio = new JRadioButton("Ramette");
            rametteRadio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getCheck80().setEnabled(true);
                    getCheck100().setEnabled(true);
                    getCouleurCombo().setEnabled(false);
                }
            });
        }
        return rametteRadio;
    }

    /**
     * Si Stylo est null instancie stylo et disable grammage
     * @return radioButton stylo
     */
    public JRadioButton getStyloRadio() {
        if(styloRadio == null ){
            styloRadio = new JRadioButton("Stylo");
            styloRadio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getCheck80().setEnabled(false);
                    getCheck100().setEnabled(false);
                    getCouleurCombo().setEnabled(true);
                }
            });
        }
        return styloRadio;
    }


    public JPanel getTypePanel() {
        if(typePanel ==null ){
            typePanel = new JPanel();
            typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));
            typePanel.add(getRametteRadio());
            typePanel.add(getStyloRadio());
            ButtonGroup btnGrp = new ButtonGroup();
            btnGrp.add(getRametteRadio());
            btnGrp.add(getStyloRadio());
        }
        return typePanel;
    }

    public JPanel getGrammagePanel() {
        if(grammagePanel ==null ){
            grammagePanel = new JPanel();
            grammagePanel.setLayout(new BoxLayout(grammagePanel, BoxLayout.Y_AXIS));
            grammagePanel.add(getCheck80());
            grammagePanel.add(getCheck100());
            ButtonGroup btnGrp = new ButtonGroup();
            btnGrp.add(getCheck80());
            btnGrp.add(getCheck100());
        }
        return grammagePanel;
    }

    public JCheckBox getCheck80() {
        if(check80 == null){
            check80 = new JCheckBox("80 gr");
        }
        return check80;
    }

    public JCheckBox getCheck100() {
        if(check100 == null){
            check100 = new JCheckBox("100 gr");
        }
        return check100;
    }

    public JComboBox<String> getCouleurCombo() {
            if(couleurCombo == null){
                String[] colors = {"bleu", "rouge", "noir", "vert"};
                couleurCombo = new JComboBox<String>(colors);
            }
        return couleurCombo;
    }


    /**
     * Creation du BtnPAnel
     * @return les buttons
     */
    public JPanel getBtnPanel() {
        if(btnPanel == null){
            btnPanel = new JPanel();
            btnPanel.setLayout(new FlowLayout());
            btnPanel.add(getBtnPrev());
            btnPanel.add(getBtnNew());
            btnPanel.add(getBtnSave());
            btnPanel.add(getBtnDel());
            btnPanel.add(getBtnNext());
        }
        return btnPanel;

    }

    public void precedent() {
        ArticleController.get().precedent();
    }

    public void nouveau() {
        ArticleController.get().nouveau();
    }

    public void enregistrer() {
        ArticleController.get().enregistrer();
    }

    public void supprimer() {
        ArticleController.get().supprimer();
    }

    public void suivant() {
        ArticleController.get().suivant();
    }

    /**
     * Defini l'article en Stylo ou Ramette
     * Set chaque Txt
     * @return article
     */
    public Article getArticle(){
        Article article = null;
        if(getStyloRadio().isSelected()){
            article = new Stylo();
        }else{
            article = new Ramette();
        }
        try{
            article.setIdArticle(idInRun);
            article.setReference(getTxtRef().getText());
            article.setMarque(getTxtMarque().getText());
            article.setDesignation(getTxtDesignation().getText());
            article.setDesignation(getTxtDesignation().getText());
            article.setPrixUnitaire(Float.parseFloat(getTxtPrice().getText()));
            article.setQteStock(Integer.parseInt(getTxtStck().getText()));
            if(getCouleurCombo().isEnabled()){
                ((Stylo)article).setCouleur((String)getCouleurCombo().getSelectedItem());
            }else{
                ((Ramette)article).setGrammage(getCheck80().isSelected()?80:100);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return article;
    }

    /**
     * Return Erreur
     * @param msg erreur
     */
    public void infoErreur(String msg){
        JOptionPane.showMessageDialog(EcranArticle.this, msg, "", JOptionPane.ERROR_MESSAGE);
    }


    /**
     * Affiche un article en fonction de son ID
     * @param article article en cours
     */
    public void afficherArticle(Article article){
        idInRun = article.getIdArticle();
        getTxtRef().setText(article.getReference() + "");
        getTxtMarque().setText(article.getMarque() + "");
        getTxtDesignation().setText(article.getDesignation() + "");
        getTxtPrice().setText(String.valueOf(article.getPrixUnitaire()) + "");
        getTxtStck().setText(new Integer(article.getQteStock()) + "");

        //Case Stylo
        if(article.getClass().equals(Stylo.class)){
            getStyloRadio().setSelected(true);
            getCouleurCombo().setEnabled(true);
            getCouleurCombo().setSelectedItem(((Stylo) article).getCouleur());

            getCheck80().setEnabled(false);
            getCheck100().setEnabled(false);
        }//Case Ramette
        else{
            getRametteRadio().setSelected(true);
            getCheck80().setEnabled(true);
            getCheck100().setEnabled(true);
            getCheck80().setSelected(((Ramette)article).getGrammage() == 80);
            getCheck100().setSelected(((Ramette)article).getGrammage() == 100);

            getCouleurCombo().setSelectedItem(null);
            getCouleurCombo().setEnabled(false);
        }

        getStyloRadio().setEnabled(article.getIdArticle() == null);
        getRametteRadio().setEnabled(article.getIdArticle() == null);
    }

    //Btn Function

    /**
     * Set l'image du button et ActionListener du bouton previous
     * @return le boutton previous
     */
    public JButton getBtnPrev() {
        if(btnPrev == null ){
            btnPrev = new JButton();
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/Back24.gif"));
            btnPrev.setIcon(imageIcon);
            btnPrev.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.get().precedent();
                }
            });
        }
        return btnPrev;
    }

    /**
     * Set l'image du button et ActionListener du bouton nouveau
     * @return le boutton nouveau
     */
    public JButton getBtnNew() {
        if(btnNew == null ){
            btnNew = new JButton();
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/New24.gif"));
            btnNew.setIcon(imageIcon);
            btnNew.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.get().nouveau();
                }
            });
        }
        return btnNew;
    }

    /**
     * Set l'image du button et ActionListener du bouton Save
     * @return le boutton Save
     */
    public JButton getBtnSave() {
        if(btnSave == null ){
            btnSave = new JButton();
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/Save24.gif"));
            btnSave.setIcon(imageIcon);
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    ArticleController.get().enregistrer();
                }
            });
        }
        return btnSave;
    }

    /**
     * Set l'image du button et ActionListener du bouton Save
     * @return le boutton Save
     */
    public JButton getBtnDel() {
        if(btnDel == null ){
            btnDel = new JButton();
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/Delete24.gif"));
            btnDel.setIcon(imageIcon);
            btnDel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.get().supprimer();
                }
            });
        }
        return btnDel;
    }

    /**
     * Set l'image du button et ActionListener du bouton suivant
     * @return le boutton suivant
     */
    public JButton getBtnNext() {
        if(btnNext == null ){
            btnNext = new JButton();
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/Forward24.gif"));
            btnNext.setIcon(imageIcon);
            btnNext.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.get().suivant();
                }
            });
        }
        return btnNext;
    }
}

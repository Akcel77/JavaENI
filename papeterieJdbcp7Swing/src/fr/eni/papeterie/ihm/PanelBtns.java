package fr.eni.papeterie.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelBtns extends JPanel {

    private List<IntPanelBtnObserver> observerList;
    private JButton btnPrev;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnDel;
    private JButton btnNext;

    public PanelBtns(){
        setLayout(new FlowLayout());
        add(getBtnPrev());
        add(getBtnNew());
        add(getBtnSave());
        add(getBtnDel());
        add(getBtnNext());

        observerList = new ArrayList<IntPanelBtnObserver>();
    }

    public void addPanelBtnObserver(IntPanelBtnObserver observer){
        observerList.add(observer);
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

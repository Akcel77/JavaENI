package fr.eni.papeterie.ihm.ecrCatalogue;

import fr.eni.papeterie.bo.Article;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TableCatalogue extends JTable {
    public static final int COL_IMG = 0;
    public static final int COL_REF= 1;
    public static final int COL_MARQUE = 2;
    public static final int COL_DESIGNATION = 3;
    public static final int COL_PU = 4;
    public static final int COL_STCK= 5;

    private List<Article> articles;

    private static ImageArticleTableCellRenderer tableCellRenderer;

    public TableCatalogue(List<Article> articles){
        super(new TableCatalogueModel(articles));

        setPreferredScrollableViewportSize(new Dimension(500,70));
        setFillsViewportHeight(true);


        this.getColumnModel().getColumn(COL_IMG).setPreferredWidth(50);
        this.getColumnModel().getColumn(COL_REF).setPreferredWidth(100);
        this.getColumnModel().getColumn(COL_MARQUE).setPreferredWidth(100);
        this.getColumnModel().getColumn(COL_DESIGNATION).setPreferredWidth(200);
        this.getColumnModel().getColumn(COL_PU).setPreferredWidth(50);
        this.getColumnModel().getColumn(COL_STCK).setPreferredWidth(50);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableCellRenderer = new ImageArticleTableCellRenderer();
        this.getColumnModel().getColumn(COL_IMG).setCellRenderer(tableCellRenderer);
        this.setRowHeight(30);
    }
}

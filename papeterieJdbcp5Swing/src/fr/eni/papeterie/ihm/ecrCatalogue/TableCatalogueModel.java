package fr.eni.papeterie.ihm.ecrCatalogue;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Stylo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableCatalogueModel extends AbstractTableModel {

    private List<Article> articles;
    private String[] colNames = {"", "Ref", "Marque", "Designation", "PU", "Stock"};

    public TableCatalogueModel(List<Article> articles){
        this.articles = articles;
    }

    public String getColumnName(int col) {
        return colNames[col];
    }

    @Override
    public int getRowCount() {
        return articles.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object val = null;
        switch(columnIndex){
            case TableCatalogue.COL_IMG:
                val = articles.get(rowIndex) instanceof Stylo ? "S":"R";
                break;
            case TableCatalogue.COL_REF:
                val = articles.get(rowIndex).getReference();
                break;
            case TableCatalogue.COL_MARQUE:
                val = articles.get(rowIndex).getMarque();
                break;
            case TableCatalogue.COL_DESIGNATION:
                val = articles.get(rowIndex).getDesignation();
                break;

            case TableCatalogue.COL_PU:
                val = articles.get(rowIndex).getPrixUnitaire();
                break;
            case TableCatalogue.COL_STCK:
                val = articles.get(rowIndex).getQteStock();
                break;
        }
        return val;
    }


}

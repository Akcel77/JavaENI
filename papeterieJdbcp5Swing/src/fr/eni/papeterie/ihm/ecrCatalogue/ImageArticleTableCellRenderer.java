package fr.eni.papeterie.ihm.ecrCatalogue;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ImageArticleTableCellRenderer implements TableCellRenderer {
    private static ImageIcon iconStylo;
    private static ImageIcon iconRamette;

     public ImageArticleTableCellRenderer(){
         iconStylo = new ImageIcon(getClass().getResource("../resources/pencil.gif"));
         iconRamette = new ImageIcon(getClass().getResource("../resources/ramette.gif"));
     }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         String type = (String) value;
         JLabel cmpt = new JLabel();

         if(type.equals("R")){
             cmpt.setIcon(iconRamette);
         }else{
             cmpt.setIcon(iconStylo);
         }
         cmpt.setHorizontalAlignment(SwingConstants.CENTER);
        return cmpt;
    }
}

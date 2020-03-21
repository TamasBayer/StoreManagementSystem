package view.StockControlPanelStockS;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class StockControlButtonsStockS extends JPanel{

	private JButton okButton;
    private JButton cancelButton;
    
    public StockControlButtonsStockS(){
        
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        
        
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        FlowLayout experimentLayout = new FlowLayout();
        
        setLayout(experimentLayout);
        
        add(okButton);
        add(cancelButton);
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class AppChooserPanel extends JPanel{

	
    private JButton mainSystemButton;
    private JButton stockSystemButton;
    
        
    public AppChooserPanel(){
        

    	mainSystemButton = new JButton("Main System");
    	stockSystemButton = new JButton("Stock System");
        
    	setPreferredSize(new Dimension(250, 150));
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        layoutComponents();
    }
    
    
    
    public JButton getMainSystemButton() {
		return mainSystemButton;
	}



	public JButton getStockSystemButton() {
		return stockSystemButton;
	}



	public void layoutComponents(){
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        /////////// First row /////////////////
     
        gc.gridy = 0;
        
        gc.weightx = 1;
        gc.weighty = 0;
        
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 20, 5);
        add(mainSystemButton, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 20, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(stockSystemButton, gc);
    }
        
}

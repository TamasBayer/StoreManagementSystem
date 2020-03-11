package view.StockPanelStockS;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SearchStockStockS extends JPanel{

	private JLabel searchLabel;
    private ImageIcon search;
    private JTextField searchField;
    private JButton searchButton;
    
	public SearchStockStockS(){
	
	    search = new ImageIcon("D:/MyPrograms/eclipse-workspace/Stock Management/Images/search.png");
	    searchLabel = new JLabel(search);
	    searchField = new JTextField(10);
	    searchButton = new JButton("Search");
	    
	    
	    Border innerBorder = BorderFactory.createTitledBorder("Search Stock");
	    Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	    setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	    
	     Font bigFont = searchField.getFont().deriveFont(Font.PLAIN, 15f);
	     searchField.setFont(bigFont);
	   
	    
	    FlowLayout experimentLayout = new FlowLayout();
	    
	    setLayout(experimentLayout);
	      
	    add(searchLabel);
	    add(searchField);
	    add(searchButton);
	      
	}
	
	public JTextField getSearchField() {
	    return searchField;
	}
	
	public JButton getSearchButton() {
	    return searchButton;
	}
}

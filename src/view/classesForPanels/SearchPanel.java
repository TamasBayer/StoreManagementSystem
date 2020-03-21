package view.classesForPanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SearchPanel extends JPanel{

	private JLabel searchLabel;
    private ImageIcon search;
    private JTextField searchField;
    private JComboBox searchCombo;
    private JButton searchButton;
    private Cursor cursor;
    
    public SearchPanel(String[] comboboxElements) {
    	
    	search = new ImageIcon("D:/MyPrograms/eclipse-workspace/Stock Management/Images/search.png");
	    searchLabel = new JLabel(search);
	    searchField = new JTextField(10);
	    searchCombo = new JComboBox();
	    searchButton = new JButton("Search");
	    
	    Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
	    Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	    setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	    
	     Font bigFont = searchField.getFont().deriveFont(Font.PLAIN, 15f);
	     searchField.setFont(bigFont);
	   
	    DefaultComboBoxModel searchModel = new DefaultComboBoxModel();
	    addComboboxElement(comboboxElements, searchModel);
	    searchCombo.setModel(searchModel);
	    searchCombo.setSelectedIndex(0);
	    searchCombo.setEditable(false);
	    
	    FlowLayout experimentLayout = new FlowLayout();
	    
	    setLayout(experimentLayout);
	      
	    add(searchLabel);
	    add(searchField);
	    add(searchCombo);
	    add(searchButton);
	    
	    cursor = new Cursor(Cursor.HAND_CURSOR);
	    searchButton.setCursor(cursor);
	    searchCombo.setCursor(cursor);
	      
    }
    
    public JTextField getSearchField() {
	    return searchField;
	}
	
	public JButton getSearchButton() {
	    return searchButton;
	}

	public JComboBox getSearchCombo() {
		return searchCombo;
	}
	
	private void addComboboxElement(String[] Elements, DefaultComboBoxModel model) {
		for(int i=0; i < Elements.length; i++) {
			model.addElement(Elements[i]);
		}
	}
}

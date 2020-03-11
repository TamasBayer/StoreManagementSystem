package view.classesForPanels;

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
    
    public SearchPanel(String[] comboboxElements) {
    	
    	search = new ImageIcon("D:/MyPrograms/eclipse-workspace/Stock Management/Images/search.png");
	    searchLabel = new JLabel(search);
	    searchField = new JTextField(10);
	    searchCombo = new JComboBox();
	    searchButton = new JButton("Search");
	    
	    
	    Border innerBorder = BorderFactory.createTitledBorder("Search");
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

package view.inventoryPanelMS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import view.classesForPanels.SearchPanel;
import view.classesForPanels.TableModel;


public class InventoryPanel extends JPanel {

	private SearchPanel searchInventory;
	private String[] comboboxEl;
	private TableModel table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private GoodsInfoPanel gIPanel;
    
    
    public InventoryPanel() {
    	
    	columnNames = new String[] {"Goods-ID", "Goods-Name", "Quantity in warehouse"};
        table = new TableModel(columnNames);
        
        table.fillWithData();
        
        comboboxEl = new String[] {"Goods-ID", "Goods-Name"};
        searchInventory = new SearchPanel(comboboxEl);
        searchField = searchInventory.getSearchField();
        searchButton = searchInventory.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchInventory.getSearchCombo();
        gIPanel = new GoodsInfoPanel();
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(searchInventory, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        
        table.newInformationFrameIfClicked(gIPanel);
    
    
	}
}

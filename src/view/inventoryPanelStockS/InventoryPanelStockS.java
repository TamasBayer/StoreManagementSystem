package view.inventoryPanelStockS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import view.classesForPanels.SearchPanel;
import view.classesForPanels.TableModel;


public class InventoryPanelStockS extends JPanel {

	private SearchPanel searchInventory;
	private String[] comboboxEl;
    private TableModel table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private Connection conn;
    private Statement createStatement = null;
    
    public InventoryPanelStockS(){
        
    	columnNames = new String[] {"Goods-ID", "Goods-Name", "Quantity in warehouse"};
        table = new TableModel(columnNames);
        comboboxEl = new String[] {"Goods-ID", "Goods-Name"};
        searchInventory = new SearchPanel(comboboxEl);
        searchField = searchInventory.getSearchField();
        searchButton = searchInventory.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchInventory.getSearchCombo();
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(searchInventory, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
    }
}

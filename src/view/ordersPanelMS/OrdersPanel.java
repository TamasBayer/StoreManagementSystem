package view.ordersPanelMS;

import java.awt.BorderLayout;
import java.awt.Color;

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
import view.inventoryPanelMS.InventoryTable;
import view.inventoryPanelMS.SearchInventory;

public class OrdersPanel extends JPanel{

	private SearchPanel searchOrders;
	private String[] comboboxEl;
	private TableModel table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    
    public OrdersPanel() {
    	
    	columnNames = new String[] {"Order-ID", "Company name", "Order datum"};
        table = new TableModel(columnNames);
        comboboxEl = new String[] {"Order-ID", "Company name", "Order datum"};
        searchOrders = new SearchPanel(comboboxEl);
        searchField = searchOrders.getSearchField();
        searchButton = searchOrders.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchOrders.getSearchCombo();
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(searchOrders, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        
    }
}

package view.readyOrdersPanelMS;

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

import view.ordersPanelMS.OrdersTable;
import view.ordersPanelMS.SearchOrders;

public class ReadyOrdersPanel extends JPanel{

	private SearchReadyOrders searchReadyOrders;
    private ReadyOrdersTable table;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    
    public ReadyOrdersPanel() {
    	
    	table = new ReadyOrdersTable();
    	searchReadyOrders = new SearchReadyOrders();
        searchField = searchReadyOrders.getSearchField();
        searchButton = searchReadyOrders.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchReadyOrders.getSearchCombo();
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(searchReadyOrders, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        
    }
}

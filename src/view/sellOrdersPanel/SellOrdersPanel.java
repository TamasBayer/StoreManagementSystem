package view.sellOrdersPanel;

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

import view.ordersPanel.OrdersTable;
import view.ordersPanel.SearchOrders;

public class SellOrdersPanel extends JPanel{

	private SearchSellOrders searchSellOrders;
    private SellOrdersTable table;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    
    public SellOrdersPanel() {
    	
    	table = new SellOrdersTable();
    	searchSellOrders = new SearchSellOrders();
        searchField = searchSellOrders.getSearchField();
        searchButton = searchSellOrders.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchSellOrders.getSearchCombo();
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(searchSellOrders, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        
    }
}

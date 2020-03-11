package view.readySellOrdersPanelMS;

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

import view.sellOrdersPanelMS.SearchSellOrders;
import view.sellOrdersPanelMS.SellOrdersTable;

public class ReadySellOrdersPanel extends JPanel{

	private SearchReadySellOrders searchReadySellOrders;
    private ReadySellOrdersTable table;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    
    public ReadySellOrdersPanel() {
    	
    	table = new ReadySellOrdersTable();
    	searchReadySellOrders = new SearchReadySellOrders();
        searchField = searchReadySellOrders.getSearchField();
        searchButton = searchReadySellOrders.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchReadySellOrders.getSearchCombo();
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(searchReadySellOrders, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        
    }
}

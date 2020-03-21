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

import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;
import view.ordersPanelMS.OrdersTable;
import view.ordersPanelMS.SearchOrders;
import view.sellOrdersPanelMS.SellOrdersInfoPanel;

public class ReadyOrdersPanel extends JPanel{
	
	private SearchPanel searchReadyOrders;
	private String[] comboboxEl;
	private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private ReadyOrdersInfoPanel infoPanel;
    
    public ReadyOrdersPanel() {
    	
    	columnNames = new String[] {"Order-ID", "Company name", "Order datum"};
        table = new Table(columnNames);
        comboboxEl = new String[] {"Order-ID", "Company name", "Order datum"};
        searchReadyOrders = new SearchPanel(comboboxEl);
        searchField = searchReadyOrders.getSearchField();
        searchButton = searchReadyOrders.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchReadyOrders.getSearchCombo();
        
        infoPanel = new ReadyOrdersInfoPanel();
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(searchReadyOrders, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        
        table.fillWithData();
        table.newInformationFrameIfClicked(infoPanel);
        
    }
}

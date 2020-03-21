package view.ordersPanelMS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;
import view.inventoryPanelMS.InventoryTable;
import view.inventoryPanelMS.SearchInventory;

public class OrdersPanel extends JPanel{

	private SearchPanel searchOrders;
	private String[] comboboxEl;
	private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private OrdersInfoPanel infoPanel;
    
    private JPanel topButtonsPanel;
    private JButton addOrder;
    private JButton editOrder;
    
    public OrdersPanel() {
    	
    	columnNames = new String[] {"Order-ID", "Company name", "Order datum"};
        table = new Table(columnNames);
        comboboxEl = new String[] {"Order-ID", "Company name", "Order datum"};
        searchOrders = new SearchPanel(comboboxEl);
        searchField = searchOrders.getSearchField();
        searchButton = searchOrders.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchOrders.getSearchCombo();
        
        infoPanel = new OrdersInfoPanel();
        
        topButtonsPanel = new JPanel();
        addOrder = new JButton("Add order");
        editOrder = new JButton("Edit order");
        
        topButtonsPanel.setPreferredSize(new Dimension(50, 50));
        searchOrders.setPreferredSize(new Dimension(50, 50));
        
        FlowLayout ItemPanelLayout = new FlowLayout();
        topButtonsPanel.setLayout(ItemPanelLayout);
        topButtonsPanel.add(addOrder);
        topButtonsPanel.add(editOrder);
	    Border innerBorderItem = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderItem = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        topButtonsPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderItem, innerBorderItem));
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        
        add(topButtonsPanel);
        add(searchOrders);
        add(table);
        
        table.fillWithData();
        table.newInformationFrameIfClicked(infoPanel);
        
    }
}

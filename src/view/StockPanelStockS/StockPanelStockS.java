package view.StockPanelStockS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;


public class StockPanelStockS extends JPanel{

	private SearchPanel searchStock;
	private String[] comboboxEl;
	private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private Connection conn;
    private Statement createStatement = null;
    
    public StockPanelStockS(){
    	
    	columnNames = new String[] {"Stock code", "Goods-ID", "Goods-Name", "Quantity in Stock"};
        table = new Table(columnNames);
        comboboxEl = new String[] {"Stock code", "Goods-ID", "Goods-Name"};
    	searchStock = new SearchPanel(comboboxEl);
        searchField = searchStock.getSearchField();
        searchButton = searchStock.getSearchButton();
        rowSorter = table.getRowSorter();
        
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(searchStock, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
    }
}

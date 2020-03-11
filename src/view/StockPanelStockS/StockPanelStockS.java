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


public class StockPanelStockS extends JPanel{

	private SearchStockStockS searchStock;
    private StockTableStockS table;
    private JTextField searchField;
    private JButton searchButton;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private Connection conn;
    private Statement createStatement = null;
    
    public StockPanelStockS(){
    	searchStock = new SearchStockStockS();
        table = new StockTableStockS();
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

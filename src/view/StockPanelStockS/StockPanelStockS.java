package view.StockPanelStockS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.Inventory;
import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;


public class StockPanelStockS extends JPanel{

	private SearchPanel searchStock;
	private String[] comboboxEl;
	private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    
    private Connection conn;
    private Statement createStatement = null;
    
    public StockPanelStockS(){
    	
    	columnNames = new String[] {"Stock code", "Item-ID", "Item name", "Quantity in Stock"};
        table = new Table(columnNames);
        comboboxEl = new String[] {"Stock code", "Item-ID", "Item name"};
    	searchStock = new SearchPanel(comboboxEl);
        searchField = searchStock.getSearchField();
        searchButton = searchStock.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchStock.getSearchCombo();
        
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(searchStock, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        
        search();
    }
    
    private void search() {
    	
    	searchButton.addActionListener(new ActionListener (){
            
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, searchCombo.getSelectedIndex()));
                }
            }
        });
    	
    	searchField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent e) {
	             if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	               searchButton.doClick();
	            }
	         }
	      });
    }
    
    public void fillTableWithData(){
		
		for (int i = table.getModel().getRowCount() - 1; i > -1; i--) {
			table.getModel().removeRow(i);
	     }
	        
		ArrayList<Inventory> list = getStocksQuantity();
	       Object rowData[] = new Object[4];
	       for(int i = 0; i < list.size(); i++ ){
	           rowData[0] = list.get(i).getStockName();
	           rowData[1] = list.get(i).getItemID();
	           rowData[2] = list.get(i).getItemName();
	           rowData[3] = list.get(i).getItemQuantityInStock();
	           
	           table.getModel().addRow(rowData);
	           }; 
	    }
    
    private ArrayList<Inventory> getStocksQuantity(){
    	String sql = "SELECT * FROM Inventory";
    	
    	ArrayList<Inventory> inventory = null;
        loadCreateStatement();
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            inventory = new ArrayList<>();

        while (rs.next()){
        	Inventory actualItem = new Inventory(rs.getString("stockName"), rs.getInt("itemID"), rs.getString("itemName"), rs.getInt("itemQuantity"));
        	inventory.add(actualItem);
            }

        
        } catch (SQLException ex) {
            System.out.println("Error with getStocksQuantity");
            System.out.println(" "+ex);
            }    
        
         return inventory;
}
    
    
    private void loadCreateStatement() {

		if (conn != null && createStatement == null){
	        try {
	             createStatement = conn.createStatement();
	        } catch (SQLException ex) {
	            System.out.println("Error with createStatement");
	            System.out.println(" "+ex);
	        }
		}
	}
    
    public void setConn(Connection conn) {
		this.conn = conn;
	}
}

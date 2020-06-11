package view.inventoryPanelStockS;

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

import model.Goods;
import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;


public class InventoryPanelStockS extends JPanel {

	private SearchPanel searchInventory;
	private String[] comboboxEl;
    private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    
    private Connection conn;
    private Statement createStatement = null;
    
    public InventoryPanelStockS(){
        
    	columnNames = new String[] {"Item-ID", "Item name", "Quantity in warehouse"};
        table = new Table(columnNames);
        comboboxEl = new String[] {"Item-ID", "Item name"};
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
        
        search();
    }
    
    public void loadCreateStatement() {

		
		if (conn != null && createStatement == null){
	        try {
	             createStatement = conn.createStatement();
	        } catch (SQLException ex) {
	            System.out.println("Error with createStatement");
	            System.out.println(" "+ex);

	        }
		}
	        
	}
    
    public ArrayList<Goods> getAllGoods(){
        String sql = "SELECT Goods.itemID, Goods.itemName, SUM (Inventory.itemQuantity) AS QuantityInWarehause FROM Goods, Inventory WHERE Goods.itemID=Inventory.itemID GROUP BY Goods.itemID, Goods.itemName";
        ArrayList<Goods> goods = null;
        try {
        	loadCreateStatement();
            ResultSet rs = createStatement.executeQuery(sql);
            goods = new ArrayList<>();

        while (rs.next()){
        	Goods actualItem = new Goods(rs.getInt("itemID"), rs.getString("itemName"), rs.getInt("QuantityInWarehause"));
        	goods.add(actualItem);
            }
        } catch (SQLException ex) {
            System.out.println("Error with getAllGoods");
            System.out.println(" "+ex);
            }    

         return goods;
}
    
    public void fillTableWithData(){
		
		for (int i = table.getModel().getRowCount() - 1; i > -1; i--) {
			table.getModel().removeRow(i);
	     }
	        
		ArrayList<Goods> list = getAllGoods();
	       Object rowData[] = new Object[3];
	       for(int i = 0; i < list.size(); i++ ){
	           rowData[0] = list.get(i).getItemID();
	           rowData[1] = list.get(i).getItemName();
	           rowData[2] = list.get(i).getItemQuantity();
	           
	           table.getModel().addRow(rowData);
	           }; 
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
    
	public void setConn(Connection conn) {
		this.conn = conn;
	}
}

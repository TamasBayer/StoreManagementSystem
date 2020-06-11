package view.readyOrdersPanelMS;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.OrderedGoods;
import model.Orders;
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
    
    private Connection conn;
    private Statement createStatement = null;
    
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
        
        table.newInformationFrameIfClicked(infoPanel);
        
        
    }
    
    public void setConn(Connection conn) {
		this.conn = conn;
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
    
    public ArrayList<Orders> getAllOrders(){
     	String sql = "SELECT * FROM ReadyOrders";
     	ArrayList<Orders> orders = null;
         loadCreateStatement();
         try {
             ResultSet rs = createStatement.executeQuery(sql);
             orders = new ArrayList<>();

         while (rs.next()){
         	Orders actualOrder = new Orders(rs.getInt("orderID"), rs.getString("orderedFrom"), rs.getString("orderDatum"));
         	orders.add(actualOrder);
             }
         } catch (SQLException ex) {
             System.out.println("Error with getAllOrders");
             System.out.println(" "+ex);
             }    
         
          return orders;
 }
    public ArrayList<OrderedGoods> getOrderedItems(int orderID){
     	String sql = "SELECT * FROM OrderedGoods WHERE orderID="+ orderID +"";
     	ArrayList<OrderedGoods> orderedGoods = null;
         loadCreateStatement();
         try {
             ResultSet rs = createStatement.executeQuery(sql);
             orderedGoods = new ArrayList<>();

         while (rs.next()){
        	OrderedGoods actualOrderedItem = new OrderedGoods(rs.getInt("orderID"), rs.getInt("orderedItemID"), rs.getString("itemName"), rs.getInt("orderedQuantity"), rs.getInt("shippedQuantity"));
         	orderedGoods.add(actualOrderedItem);
             }
         } catch (SQLException ex) {
             System.out.println("Error with getAllOrders");
             System.out.println(" "+ex);
             }    
         
          return orderedGoods;
 }
    
    public void fillTableWithData(){
		
		for (int i = table.getModel().getRowCount() - 1; i > -1; i--) {
			table.getModel().removeRow(i);
	     }
	        
		ArrayList<Orders> list = getAllOrders();
	       Object rowData[] = new Object[3];
	       for(int i = 0; i < list.size(); i++ ){
	           rowData[0] = list.get(i).getOrderID();
	           rowData[1] = list.get(i).getOrderedFrom();
	           rowData[2] = list.get(i).getOrderDatum();
	           
	           table.getModel().addRow(rowData);
	           }; 
	    }
}

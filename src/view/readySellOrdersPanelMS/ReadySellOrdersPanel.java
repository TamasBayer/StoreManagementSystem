package view.readySellOrdersPanelMS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.OrderedGoods;
import model.Orders;
import model.SellOrders;
import model.SoldGoods;
import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;
import view.sellOrdersPanelMS.SearchSellOrders;
import view.sellOrdersPanelMS.SellOrdersInfoPanel;
import view.sellOrdersPanelMS.SellOrdersTable;

public class ReadySellOrdersPanel extends JPanel{
	
	private SearchPanel searchReadySellOrders;
	private String[] comboboxEl;
	private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private ReadySellOrdersInfoPanel infoPanel;
    private Table infoPanelTable;
    private JTextField infoPanelOrderIDField;
    private JTextField infoPanelSoldForField;
    private JTextField infoPanelSellOrderDField;
    
    private JTable jTable;
    
    private Connection conn;
    private Statement createStatement = null;
    
    public ReadySellOrdersPanel() {
    	
    	columnNames = new String[] {"Sell order-ID", "Company name", "Order datum"};
        table = new Table(columnNames);
        comboboxEl = new String[] {"Sell order-ID", "Company name", "Order datum"};
        searchReadySellOrders = new SearchPanel(comboboxEl);
        searchField = searchReadySellOrders.getSearchField();
        searchButton = searchReadySellOrders.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchReadySellOrders.getSearchCombo();
        
        infoPanel = new ReadySellOrdersInfoPanel();
        infoPanelTable = infoPanel.getSellOrdersTable();
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(searchReadySellOrders, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);

        table.newInformationFrameIfClicked(infoPanel);
        jTable = table.getTable();
        
        newOrdersInfoPanelTable();
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
    
    public ArrayList<SellOrders> getAllOrders(){
     	String sql = "SELECT * FROM ReadySellOrders";
     	ArrayList<SellOrders> orders = null;
         loadCreateStatement();
         try {
             ResultSet rs = createStatement.executeQuery(sql);
             orders = new ArrayList<>();

         while (rs.next()){
        	 SellOrders actualOrder = new SellOrders(rs.getInt("sellOrderID"), rs.getString("soldFor"), rs.getString("sellOrderDatum"));
         	orders.add(actualOrder);
             }
         } catch (SQLException ex) {
             System.out.println("Error with getAllOrders");
             System.out.println(" "+ex);
             }    
         
          return orders;
 }
    public ArrayList<SoldGoods> getSoldItems(int orderID){
     	String sql = "SELECT * FROM SoldGoods WHERE sellOrderID="+ orderID +"";
     	ArrayList<SoldGoods> soldGoods = null;
         loadCreateStatement();
         try {
             ResultSet rs = createStatement.executeQuery(sql);
             soldGoods = new ArrayList<>();

         while (rs.next()){
        	 SoldGoods actualSoldItem = new SoldGoods(rs.getInt("sellOrderID"), rs.getInt("soldItemID"), rs.getString("itemName"), rs.getInt("soldQuantity"), rs.getInt("pickedQuantity"));
        	 soldGoods.add(actualSoldItem);
             }
         } catch (SQLException ex) {
             System.out.println("Error with getAllOrders");
             System.out.println(" "+ex);
             }    
         
          return soldGoods;
 }
    
    public void fillTableWithData(){
		
		for (int i = table.getModel().getRowCount() - 1; i > -1; i--) {
			table.getModel().removeRow(i);
	     }
	        
		ArrayList<SellOrders> list = getAllOrders();
	       Object rowData[] = new Object[3];
	       for(int i = 0; i < list.size(); i++ ){
	           rowData[0] = list.get(i).getSellOrderID();
	           rowData[1] = list.get(i).getSoldFor();
	           rowData[2] = list.get(i).getSellOrderDatum();
	           
	           table.getModel().addRow(rowData);
	           }; 
	    }
    
    public void newOrdersInfoPanelTable() {
    	jTable.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	           if (e.getClickCount() == 2) {
	        	   infoPanelOrderIDField = infoPanel.getSellOrderIDField();
	        	   infoPanelSoldForField = infoPanel.getSoldForField();
	        	   infoPanelSellOrderDField = infoPanel.getSellOrderDatumField();
	        	   
	        	   
	        	   
	        	   
	        	   int row = jTable.getSelectedRow();
	          
	        	   int orderID = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	        	   
	        	   infoPanelOrderIDField.setText(table.getModel().getValueAt(row, 0).toString());
	        	   infoPanelSoldForField.setText(table.getModel().getValueAt(row, 1).toString());
	        	   infoPanelSellOrderDField.setText(table.getModel().getValueAt(row, 2).toString());
	        	   
	        	   
	        	   fillInfoPanelTable(orderID);
	              
	           }
	        }
	     });
	}
    
    private void fillInfoPanelTable(int orderID) {
    	

    	for (int i = infoPanelTable.getModel().getRowCount() - 1; i > -1; i--) {
 		   infoPanelTable.getModel().removeRow(i);
	     }
	        
		ArrayList<SoldGoods> list = getSoldItems(orderID);
	       Object rowData[] = new Object[4];
	       for(int i = 0; i < list.size(); i++ ){
	           rowData[0] = list.get(i).getSoldItemID();
	           rowData[1] = list.get(i).getSoldItemName();
	           rowData[2] = list.get(i).getSoldItemQuantity();
	           rowData[3] = list.get(i).getPickedQuantity();
	           
	           infoPanelTable.getModel().addRow(rowData);
	           }; 
    }
}

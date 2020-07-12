package view.stockInOutPanelMS;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.Goods;
import model.Inventory;
import model.StockNames;
import view.classesForPanels.Table;

public class StockInOutPanel extends JPanel {

	private JPanel orderTableFlowLayout;
	private Table ordersTable;
	private String[] ordersColumnNames;
	private JButton bookButton;
	
	private JFrame warningMessageFrame;
	
	private Connection conn;
    private Statement createStatement = null;
	
	public StockInOutPanel() {

		setPreferredSize(new Dimension(1000,600));
		
		ordersColumnNames = new String[] {"In or Out", "Item-ID", "Item name", "Quantity", "Stock"};
		ordersTable = new Table(ordersColumnNames, "StockInOutPanel");
		bookButton = new JButton("Book");
		orderTableFlowLayout = new JPanel();
		
		warningMessageFrame = new JFrame();
		
		AutoFillItemName();
		bookButtonPressed();
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		bookButton.setCursor(cursor);
		
		
		//////// Set Layout ////////
		
		setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        FlowLayout OrderPanelButtonsLayout = new FlowLayout(FlowLayout.RIGHT);
        orderTableFlowLayout.setLayout(OrderPanelButtonsLayout);
        orderTableFlowLayout.add(bookButton);
        
        Border innerBorderButtons = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderButtons = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        orderTableFlowLayout.setBorder(BorderFactory.createCompoundBorder(outerBorderButtons, innerBorderButtons));
        

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 4;
        c.weightx = 1;
        add(ordersTable, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.weightx = 1;
        add(orderTableFlowLayout, c);
	}
	
	
	private void bookButtonPressed(){
		bookButton.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e) {
            
            	if (ordersTable.getTable().isEditing())
            		ordersTable.getTable().getCellEditor().stopCellEditing();
	        	
	        	ArrayList<Inventory> inventoryArray = null;
	        	String stockInOrOut;
	        	String stockName;
	        	int itemID;
	        	String itemName;
	        	int newItemQuantity;
	        	
	        	int missingError = 0;
	        	
	        	int oldQuantity = 0;
	        	int newQuantity = 0;
	        	
	        	
	        	Boolean alreadyInArray = false;
	        	
	        	String regex = "\\d+";
	        	
	        	inventoryArray = new ArrayList<>();
            	
            	for(int row=0; row < 31; row++) {
                	
            		if(ordersTable.getModel().getValueAt(row, 0).toString() != "" && ordersTable.getModel().getValueAt(row, 1).toString() != "") {
                		
            			if(ordersTable.getModel().getValueAt(row, 3).toString().matches(regex)) {
                			
                			if(ordersTable.getModel().getValueAt(row, 4).toString() != "") {
                			
                			stockInOrOut = ordersTable.getModel().getValueAt(row, 0).toString();
                			stockName = ordersTable.getModel().getValueAt(row, 4).toString();
                			itemID = Integer.parseInt(ordersTable.getModel().getValueAt(row, 1).toString());
            	        	itemName = ordersTable.getModel().getValueAt(row, 1).toString();
            	        	newItemQuantity = Integer.parseInt(ordersTable.getModel().getValueAt(row, 3).toString());

            	        	alreadyInArray = false;
            	        	
            	        	for(Inventory inventory : inventoryArray) {
            	        	    if(inventory!=null && itemID == inventory.getItemID() && stockInOrOut == inventory.getStockInOrOut()) {
            	        	    	
            	        	    	inventory.setItemQuantityInStock(inventory.getItemQuantityInStock() + newItemQuantity);
            	        	    	alreadyInArray = true;
            	        	        break;
            	        	    } 
            	        	}
            	        	if(alreadyInArray == false) {
            	        		Inventory actualInventory = new Inventory(stockInOrOut, stockName, itemID, itemName, newItemQuantity);
            	        		inventoryArray.add(actualInventory);
            	        		}
            	        		
                			} else {
                				JOptionPane.showMessageDialog(warningMessageFrame, "Stock Name missing", "Missing", JOptionPane.WARNING_MESSAGE);
                    			missingError = 1;
                    			row = 31;
                			}
                    		
                		} else {
            				JOptionPane.showMessageDialog(warningMessageFrame, "Item quantity missing or not a positive number", "Missing", JOptionPane.WARNING_MESSAGE);
                			missingError = 1;
                			row = 31;
                		}
                	} else {
                	}
                }
            	
            	if(missingError == 0) {
            		
            		for(int i=0; i < inventoryArray.size(); i++) {
            			
            			
                       try {
                    	   
                    	   String sql = "SELECT * FROM Inventory WHERE stockName='"+ inventoryArray.get(i).getStockName() +"' AND itemID="+ inventoryArray.get(i).getItemID() +"";
	                        ResultSet rs = createStatement.executeQuery(sql);
	                        
	                        if (rs.next()) {
	                        	oldQuantity = rs.getInt("itemQuantity");
	                        	
	                        	if(inventoryArray.get(i).getStockInOrOut() == "In") {
	                        		newQuantity = oldQuantity + inventoryArray.get(i).getItemQuantityInStock();
	                        		
	                        		sql = "UPDATE Inventory SET itemQuantity="+ newQuantity +" WHERE stockName='"+ inventoryArray.get(i).getStockName() +"' AND itemID="+ inventoryArray.get(i).getItemID() +"";
	                        		
	                        		PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    				preparedStatement.execute();
                    				
                    				ordersTable.fillWithEmptyRows();
	                        	
	                        	} else {
	                        			newQuantity = oldQuantity - inventoryArray.get(i).getItemQuantityInStock();
	                        			
	                        			if(newQuantity > 0) {
	                        				sql = "UPDATE Inventory SET itemQuantity="+ newQuantity +" WHERE stockName='"+ inventoryArray.get(i).getStockName() +"' AND itemID="+ inventoryArray.get(i).getItemID() +"";
	                        				PreparedStatement preparedStatement = conn.prepareStatement(sql);
	                        				preparedStatement.execute();
	                        				
	                        				ordersTable.fillWithEmptyRows();
	                        			} else if(newQuantity == 0) {
	                        				sql = "DELETE FROM Inventory WHERE stockName='"+ inventoryArray.get(i).getStockName() +"' AND itemID="+ inventoryArray.get(i).getItemID() +"";
	                        				PreparedStatement preparedStatement = conn.prepareStatement(sql);
	                        				preparedStatement.execute();
	                        				
	                        				ordersTable.fillWithEmptyRows();
	                        			} else if(newQuantity < 0) {
	                        				JOptionPane.showMessageDialog(warningMessageFrame, "Out quantity is more than the quantity in Stock", "Missing", JOptionPane.WARNING_MESSAGE);
	                        			}
	                        		}
	                        	} else if(inventoryArray.get(i).getStockInOrOut() == "In"){
	                        		String sqlInsert = "INSERT INTO Inventory VALUES (?,?,?,?)";
	                            	
	                            	PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
	                            	
	    							preparedStatement = conn.prepareStatement(sqlInsert);
	    							preparedStatement.setString(1, inventoryArray.get(i).getStockName());
	    	                        preparedStatement.setInt(2, inventoryArray.get(i).getItemID());
	    	                        preparedStatement.setString(3, inventoryArray.get(i).getItemName());
	    	                        preparedStatement.setInt(4, inventoryArray.get(i).getItemQuantityInStock());
	    	                        preparedStatement.execute();
	    	                        
	    	                        ordersTable.fillWithEmptyRows();
	    	                        
	                        	} else {
	                				JOptionPane.showMessageDialog(warningMessageFrame, "Item in this Stock is not found", "Not found", JOptionPane.WARNING_MESSAGE);

	                        	}
						} catch (SQLException ex) {
		                      System.out.println("Error with in/out");
		                      System.out.println(""+ex);
		                  }
                    }
            	}
		}   	
     });
   }
	
	public void fillTableWithEmptyRows() {
		ordersTable.fillWithEmptyRowsStockInOut();
		ordersTable.comboBoxColumnInOutPanel(getAllGoods(), getAllStocks());
	}
	
	public void fillComboBox() {
		ordersTable.comboBoxColumnInOutPanel(getAllGoods(), getAllStocks());
	}
    
	private void AutoFillItemName() {
		ordersTable.getModel().addTableModelListener(new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (e.getType() == TableModelEvent.UPDATE && column == 1) {
            	
               String itemID = ordersTable.getModel().getValueAt(row, 1).toString();
               
            if(itemID != "") {
            	 String sql = "SELECT itemName FROM Goods WHERE itemID="+ Integer.parseInt(itemID) +"";
		       		try {
		               	loadCreateStatement();
		                ResultSet rs = createStatement.executeQuery(sql);

		                if (rs.next()) {
		                	ordersTable.getTable().setValueAt(rs.getString("itemName"), row, 2);
		                    
		                }
		                
		               } catch (SQLException ex) {
		                   System.out.println("Error with setItemName");
		                   System.out.println(" "+ex);
		                   } 
            } else {
            	ordersTable.getTable().setValueAt("", row, 2);
            }
        }
    }
		});
	}
	
	private ArrayList<Goods> getAllGoods(){
     	String sql = "SELECT * FROM Goods";
     	ArrayList<Goods> goods = null;
         loadCreateStatement();
         try {
             ResultSet rs = createStatement.executeQuery(sql);
             goods = new ArrayList<>();

         while (rs.next()){
         	Goods actualItem = new Goods(rs.getInt("itemID"), rs.getString("itemName"), 0);
         	goods.add(actualItem);
             }
         } catch (SQLException ex) {
             System.out.println("Error with getAllGoods");
             System.out.println(" "+ex);
             }    
         
          return goods;
	}
	
	private ArrayList<StockNames> getAllStocks(){
       String sql = "SELECT * FROM StockNames";
     	ArrayList<StockNames> stocks = null;
         loadCreateStatement();
         try {
             ResultSet rs = createStatement.executeQuery(sql);
             stocks = new ArrayList<>();

         while (rs.next()){
        	 StockNames actualItem = new StockNames(rs.getString("stockName"));
         	stocks.add(actualItem);
             }
         } catch (SQLException ex) {
             System.out.println("Error with getAllGoods");
             System.out.println(" "+ex);
             }    
         
          return stocks;
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

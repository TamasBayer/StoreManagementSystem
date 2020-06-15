package view.pickingForOrdersPanelStockS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.Inventory;
import model.OrderedGoods;
import model.Orders;
import model.SoldGoods;
import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;

public class OrderPickingPanel extends JPanel {
	
	
	private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private Connection conn;
    private Statement createStatement = null;
    
    private JPanel ordersPanel;
    private JPanel orderControlPanel;
    private JLabel itemIDLabel;
    private JTextField itemIDField;
    private JLabel itemNameLabel;
    private JTextField itemNameField;
    private JLabel itemNeededQuantityLabel;
    private JTextField itemNeededQuantityField;
    private JLabel itemBookQuantityFromStockLabel;
    private JTextField itemBookQuantityFromStockField;
    private JLabel itemStockLabel;
    private JTextField itemStockField;
    private JPanel buttonsPanel;
    private FlowLayout buttonsFlowL;
    private JButton bookBtn;
    private JButton orderBackBtn;
    private JButton nextButton;
    private JButton prevButton;
    private JTextField allDifferentItemsField;
    private JButton StockBtn;
    
    private JPanel StockPanel;
    private Table stockTable;
    private String[] columnsName;
    private JButton stockBackBtn; 
    private JPanel backBtnPanel;
    private FlowLayout backBtnFlowL;
    
    private JButton button;
    
    private Cursor cursor;
    
    public OrderPickingPanel() {
			
    	
    	buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,3,10,10));
        
        
        
        
        scrollPane = new JScrollPane();
 
        
        scrollPane.setViewportView(buttonPanel);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        
        
    }
    
    public void setAllSellOrderButton() {
    	ArrayList<Integer> orderIDs = new ArrayList<>(getAllSellOrderID());
        for(int i = 0; i < orderIDs.size(); i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(130, 50));
            button.setText(orderIDs.get(i).toString());
            
            
            
            button.addActionListener(new ActionListener(){

            	int item = 0;
            	
                public void actionPerformed(ActionEvent e) {
                	
                	item = 0;
                	remove(scrollPane);
                	
                	Object o = e.getSource();
             	   JButton b = null;
             	   String buttonText = "";

 
             	     b = (JButton)o;

             	   if(b != null)
             	     buttonText = b.getText();
                	
                	setOrdersPanel();
                	
                	ArrayList<SoldGoods> soldGoods = new ArrayList<>(getOrderedItems(Integer.parseInt(buttonText)));
                	
                	setSellOrderTextFields(soldGoods.get(item).getSoldItemID(), soldGoods.get(item).getSoldItemName(), soldGoods.get(item).getSoldItemQuantity() - soldGoods.get(item).getPickedQuantity(), soldGoods.size(), item);
                	add(ordersPanel, BorderLayout.CENTER);
                	
                	nextButton.addActionListener(new ActionListener(){

                        public void actionPerformed(ActionEvent e) {
                        	
                        	if(item < soldGoods.size()-1) {
                        		item++;
                        	} else {
                        		item = 0;
                        	}
                        	setSellOrderTextFields(soldGoods.get(item).getSoldItemID(), soldGoods.get(item).getSoldItemName(), soldGoods.get(item).getSoldItemQuantity() - soldGoods.get(item).getPickedQuantity(), soldGoods.size(), item);
                        	
                       }
                 });
                	
                	prevButton.addActionListener(new ActionListener(){

                        public void actionPerformed(ActionEvent e) {
                        	
                        	if(item > 0) {
                        		item--;
                        	} else {
                        		item = soldGoods.size()-1;
                        	}
                        	setSellOrderTextFields(soldGoods.get(item).getSoldItemID(), soldGoods.get(item).getSoldItemName(), soldGoods.get(item).getSoldItemQuantity() - soldGoods.get(item).getPickedQuantity(), soldGoods.size(), item);
                        	
                       }
                 });
               }
                
                
         });
            
            buttonPanel.add(button);
            cursor = new Cursor(Cursor.HAND_CURSOR);
            button.setCursor(cursor);
            
        }
    }
    
    
    public void setOrdersPanel() {
    	
    	ordersPanel = new JPanel();
    	
    	Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        ordersPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    	
    	orderControlPanel = new JPanel();
        itemIDLabel = new JLabel("Item-ID:");
        itemIDField = new JTextField(10);
        itemNameLabel = new JLabel("Item name:");
        itemNameField = new JTextField(10);
        itemNeededQuantityLabel = new JLabel("Needed quantity:");
        itemNeededQuantityField = new JTextField(10);
        itemStockLabel = new JLabel("Stock:");
        itemStockField = new JTextField(10);
        itemBookQuantityFromStockLabel = new JLabel("Quantity from stock:");
        itemBookQuantityFromStockField = new JTextField(10);
        
        itemIDField.setEditable(false);
        itemNameField.setEditable(false);
        itemNeededQuantityField.setEditable(false);
        
        
        buttonsPanel = new JPanel();
        buttonsFlowL = new FlowLayout(FlowLayout.RIGHT);
        allDifferentItemsField = new JTextField(3);
        prevButton = new JButton("Prev item");
        nextButton = new JButton("Next item");
        bookBtn = new JButton("Book");
        orderBackBtn = new JButton("Back");
        StockBtn = new JButton("Stock");
        
        
        Font bigFont = allDifferentItemsField.getFont().deriveFont(Font.PLAIN, 15f);
        allDifferentItemsField.setFont(bigFont);
	    allDifferentItemsField.setEditable(false);
	    
	    cursor = new Cursor(Cursor.HAND_CURSOR);
	    prevButton.setCursor(cursor);
	    nextButton.setCursor(cursor);
	    bookBtn.setCursor(cursor);
	    orderBackBtn.setCursor(cursor);
	    StockBtn.setCursor(cursor);
        
        // FlowLayout //
        
        buttonsPanel.setLayout(buttonsFlowL);
	      
        buttonsPanel.setPreferredSize(new Dimension(40,40));
        buttonsPanel.add(allDifferentItemsField);
        buttonsPanel.add(prevButton);
        buttonsPanel.add(nextButton);
        buttonsPanel.add(StockBtn);
        buttonsPanel.add(bookBtn);
        buttonsPanel.add(orderBackBtn);
        
        orderBackBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            	
            	remove(ordersPanel);
                repaint();
            	add(scrollPane, BorderLayout.CENTER);
           }
     });
        
        StockBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            	
            	setStockPanel(itemIDField.getText());
            	remove(ordersPanel);
                repaint();
            	add(StockPanel, BorderLayout.CENTER);
           }
     });
        
        bookBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            	
            	
           }
     });
        
        // Control Panel //
        
        
        orderControlPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        
        /////////// First row /////////////////
        
        gc.gridy = 0;
        
        gc.weightx = 1;
        gc.weighty = 0;
        
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        orderControlPanel.add(itemIDLabel, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        orderControlPanel.add(itemIDField, gc);
        
        /////////// Second row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0 , 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        orderControlPanel.add(itemNameLabel, gc);
        
        gc.gridy = 1;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        orderControlPanel.add(itemNameField, gc);
        
        /////////// Third row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0 , 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        orderControlPanel.add(itemNeededQuantityLabel, gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        orderControlPanel.add(itemNeededQuantityField, gc);
        
        /////////// Fourth row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0 , 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        orderControlPanel.add(itemStockLabel, gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        orderControlPanel.add(itemStockField, gc);
        
		/////////// Fifth row /////////////////
		gc.gridy++;
		  
		gc.weightx = 1;
		gc.weighty =  0;
		  
		gc.gridx = 0;
		gc.insets = new Insets(0, 0 , 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		orderControlPanel.add(itemBookQuantityFromStockLabel, gc);
		  
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		orderControlPanel.add(itemBookQuantityFromStockField, gc);
		
		
		
		ordersPanel.setLayout(new BorderLayout());
		ordersPanel.add(orderControlPanel, BorderLayout.CENTER);
		ordersPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    public void setStockPanel(String itemID) {
    	
    	StockPanel = new JPanel();
    	columnsName = new String[] {"Stock", "Stock quantity"};
        stockTable = new Table(columnsName);
        stockBackBtn = new JButton("Back"); 
        backBtnPanel = new JPanel();
        backBtnFlowL = new FlowLayout(FlowLayout.RIGHT);
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        stockBackBtn.setCursor(cursor);
        
        fillTableWithData(itemID);
        
		 // FlowLayout //
        
        backBtnPanel.setLayout(backBtnFlowL);
	      
        backBtnPanel.setPreferredSize(new Dimension(40,40));
        backBtnPanel.add(stockBackBtn);
        
        stockBackBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            	
            	remove(StockPanel);
                repaint();
            	add(ordersPanel, BorderLayout.CENTER);
           }
     });
        
        StockPanel.setLayout(new BorderLayout());
        StockPanel.add(stockTable, BorderLayout.CENTER);
        StockPanel.add(backBtnPanel, BorderLayout.SOUTH);
    }
    
    	public void fillTableWithData(String itemID){
		
		for (int i = stockTable.getModel().getRowCount() - 1; i > -1; i--) {
			stockTable.getModel().removeRow(i);
	     }
	        
		ArrayList<Inventory> listStock = getStocksQuantity(itemID);
		Object rowDataStock[] = new Object[2];
	       for(int i = 0; i < listStock.size(); i++ ){
	    	rowDataStock[0] = listStock.get(i).getStockName();
	    	rowDataStock[1] = listStock.get(i).getItemQuantityInStock();
	           
	           stockTable.getModel().addRow(rowDataStock);
	           }; 
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
    
	public ArrayList<Integer> getAllSellOrderID(){
    	String sql = "SELECT sellOrderID FROM SellOrders";
    	
    	ArrayList<Integer> orderIDs = null;
        loadCreateStatement();
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            orderIDs = new ArrayList<>();

        while (rs.next()){
        	int actualOrderID =  rs.getInt("sellOrderID");
        	orderIDs.add(actualOrderID);
            }

        
        } catch (SQLException ex) {
            System.out.println("Error with get all sell order ID");
            System.out.println(" "+ex);
            }    
        
         return orderIDs;
}
	
	public ArrayList<SoldGoods> getOrderedItems(int orderID){
     	String sql = "SELECT * FROM SoldGoods WHERE sellOrderID="+ orderID +"";
     	ArrayList<SoldGoods> soldGoods = null;
         loadCreateStatement();
         try {
             ResultSet rs = createStatement.executeQuery(sql);
             soldGoods = new ArrayList<>();

         while (rs.next()){
        	SoldGoods actualItem = new SoldGoods(rs.getInt("sellOrderID"), rs.getInt("soldItemID"), rs.getString("itemName"), rs.getInt("soldQuantity"), rs.getInt("pickedQuantity"));
        	soldGoods.add(actualItem);
             }
         } catch (SQLException ex) {
             System.out.println("Error with getAllOrders");
             System.out.println(" "+ex);
             }    
         
          return soldGoods;
 }
	
	public ArrayList<Inventory> getStocksQuantity(String itemID){
    	String sql = "SELECT stockName, itemQuantity FROM Inventory WHERE itemID = "+ itemID +"";
    	
    	ArrayList<Inventory> inventory = null;
        loadCreateStatement();
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            inventory = new ArrayList<>();

        while (rs.next()){
        	Inventory actualItem = new Inventory(rs.getString("stockName"), 0, "", rs.getInt("itemQuantity"));
        	inventory.add(actualItem);
            }

        
        } catch (SQLException ex) {
            System.out.println("Error with getStocksQuantity");
            System.out.println(" "+ex);
            }    
        
         return inventory;
}
	
	private void setSellOrderTextFields(int itemID, String itemName, int NQuantity, int itemsNumber, int actualItem) {
		
		
        itemIDField.setText("" + itemID);
        itemNameField.setText(itemName);
        itemNeededQuantityField.setText("" + NQuantity);

        allDifferentItemsField.setText(actualItem + 1 + "/" + itemsNumber);
	}
	
	public void bookingItem() {
		
	}
	
	public void loadTextFieldsToBook() {
		
	}
}

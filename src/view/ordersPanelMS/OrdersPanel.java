package view.ordersPanelMS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.Goods;
import model.OrderedGoods;
import model.Orders;
import model.StockNames;
import view.classesForPanels.AddItemOrOrderPanel;
import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;
import view.inventoryPanelMS.InventoryPanel;
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
    private Table infoPanelTable;
    private JTextField infoPanelOrderIDField;
    private JTextField infoPanelCompanyNField;
    private JTextField infoPanelOrderDField;
    private JButton bookButton;
    
    private JPanel topButtonsPanel;
    private JButton addOrderBtn;
    private JButton editOrderBtn;
    private JButton deleteOrderBtn;
    
    private JFrame orderFrame;
    private AddItemOrOrderPanel orderPanel;
    
    private Cursor cursor;
    
    private JTable jTable;


    private JTable jTableAddOrder;
    
    private JTextField orderIDField;
    private JTextField companyNameField;
    private JTextField orderDatumField;
    private Table orderPanelTable;
    
    private Connection conn;
    private Statement createStatement = null;
    
    private int itemPanelNumber;
    
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
        infoPanelTable = infoPanel.getOrdersTable();
        bookButton = infoPanel.getBookButton();
        
        
        topButtonsPanel = new JPanel();
        addOrderBtn = new JButton("Add order");
        editOrderBtn = new JButton("Edit order");
        deleteOrderBtn = new JButton("Delete order");
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        addOrderBtn.setCursor(cursor);
        editOrderBtn.setCursor(cursor);
        deleteOrderBtn.setCursor(cursor);
        
        topButtonsPanel.setPreferredSize(new Dimension(50, 50));
        searchOrders.setPreferredSize(new Dimension(50, 50));
        
        FlowLayout ItemPanelLayout = new FlowLayout();
        topButtonsPanel.setLayout(ItemPanelLayout);
        topButtonsPanel.add(addOrderBtn);
        topButtonsPanel.add(editOrderBtn);
        topButtonsPanel.add(deleteOrderBtn);
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
        
        jTable = table.getTable();
        
        addOrderButtonPressed();
        editOrderButtonPressed();
        deleteButtonPressed();
        newOrdersInfoPanelTable();
        bookButtonPressed();
        search();
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
     	String sql = "SELECT * FROM Orders";
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
    
    public ArrayList<StockNames> getAllStocks(){
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
    
    public void newOrdersInfoPanelTable() {
    	jTable.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	           if (e.getClickCount() == 2) {
	        	   infoPanelOrderIDField = infoPanel.getOrderIDField();
	        	   infoPanelCompanyNField = infoPanel.getCompanyNameField();
	        	   infoPanelOrderDField = infoPanel.getOrderDatumField();
	        	   
	        	   
	        	   
	        	   
	        	   int row = jTable.getSelectedRow();
	          
	        	   int orderID = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	        	   
	        	   infoPanelOrderIDField.setText(table.getModel().getValueAt(row, 0).toString());
	        	   infoPanelCompanyNField.setText(table.getModel().getValueAt(row, 1).toString());
	        	   infoPanelOrderDField.setText(table.getModel().getValueAt(row, 2).toString());
	        	   
	        	   
	        	   fillInfoPanelTable(orderID);
	              
	           }
	        }
	     });
	}
    
    public void fillInfoPanelTable(int orderID) {
    	
    	infoPanelTable.comboBoxColumnStockNames(getAllStocks());
    	
    	
    	for (int i = infoPanelTable.getModel().getRowCount() - 1; i > -1; i--) {
 		   infoPanelTable.getModel().removeRow(i);
	     }
	        
		ArrayList<OrderedGoods> list = getOrderedItems(orderID);
	       Object rowData[] = new Object[4];
	       for(int i = 0; i < list.size(); i++ ){
	           rowData[0] = list.get(i).getOrderedItemID();
	           rowData[1] = list.get(i).getOrderedItemName();
	           rowData[2] = list.get(i).getOrderedItemQuantity();
	           rowData[3] = list.get(i).getShippedQuantity();
	           
	           infoPanelTable.getModel().addRow(rowData);
	           }; 
    }
    
    public void bookButtonPressed(){
    	bookButton.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
            	
            	if (infoPanelTable.getTable().isEditing())
            		infoPanelTable.getTable().getCellEditor().stopCellEditing();
            	int orderID = Integer.parseInt(infoPanelOrderIDField.getText());
            	int itemID = 0;
            	String itemName = "";
            	int orderedQuantity = 0;
            	int alreadyDeliveredQuantity = 0;
            	int currentDelivered = 0;
            	int currentAndAlreadyDeliveredQuantity;
            	String stockName = "";
            	
            	ArrayList<Integer> orderedQuantityArray;
            	ArrayList<Integer> deliveredQuantityArray;
            	
            	JFrame infoFrame = table.getFrame();
            	
            	int orderedQuantityIsDelivered = 0;
            	
            	String regex = "\\d+";
            	
            	String sql;
            	
            	for (int i = 0; i < infoPanelTable.getModel().getRowCount(); i++) {
	        		   if(infoPanelTable.getModel().getValueAt(i, 4) != null && infoPanelTable.getModel().getValueAt(i, 4).toString().matches(regex)) {
	        			   if(infoPanelTable.getModel().getValueAt(i, 5) != null && infoPanelTable.getModel().getValueAt(i, 5).toString() != "") {	        				   
	        				   itemID = Integer.parseInt(infoPanelTable.getModel().getValueAt(i, 0).toString());
	        				   itemName = infoPanelTable.getModel().getValueAt(i, 1).toString();
	        				   orderedQuantity = Integer.parseInt(infoPanelTable.getModel().getValueAt(i, 2).toString());
		        			   alreadyDeliveredQuantity = Integer.parseInt(infoPanelTable.getModel().getValueAt(i, 3).toString());
		        			   currentDelivered = Integer.parseInt(infoPanelTable.getModel().getValueAt(i, 4).toString());
		        			   stockName = infoPanelTable.getModel().getValueAt(i, 5).toString();
		        			   
		        			   currentAndAlreadyDeliveredQuantity = currentDelivered + alreadyDeliveredQuantity;
		        			   
		        			   if(currentAndAlreadyDeliveredQuantity <= orderedQuantity) {
		        				   try {
		        					   sql = "UPDATE OrderedGoods SET shippedQuantity="+ currentAndAlreadyDeliveredQuantity +" WHERE orderID="+ infoPanelOrderIDField.getText() +" AND orderedItemID="+ infoPanelTable.getModel().getValueAt(i, 0).toString() +"";
				                       PreparedStatement preparedStatement = conn.prepareStatement(sql);
				
				                       preparedStatement.execute();
				                       
				                       loadCreateStatement();
						               	sql = "SELECT itemQuantity FROM Inventory WHERE stockName = '"+ stockName +"' AND itemID = "+ itemID +"";
						                ResultSet rs = createStatement.executeQuery(sql);

						                if (rs.next()) {
						                	int newQuantity = rs.getInt("itemQuantity") + currentDelivered;
						                	sql = "UPDATE Inventory SET itemQuantity="+ newQuantity +" WHERE stockName = '"+ stockName +"' AND itemID = "+ itemID +"";
						                    preparedStatement = conn.prepareStatement(sql);
						
						                    preparedStatement.execute();
						                    
						                } else {
						                	sql = "INSERT INTO Inventory VALUES ('"+ stockName +"', "+ itemID +", '"+ itemName +"', "+ currentDelivered +")";
						                    preparedStatement = conn.prepareStatement(sql);
						
						                    preparedStatement.execute();
						                }
				                       
				                       			                      
				                        
		        				   } catch (SQLException ex) {
					                   System.out.println("Error with insert current delivered quantity");
					                   System.out.println(" "+ex);
					                   break;
					                   } 

		        				   try {
						               	sql = "SELECT orderedQuantity, shippedQuantity FROM OrderedGoods WHERE orderID="+ orderID +"";
						                ResultSet rs = createStatement.executeQuery(sql);
						                
						                orderedQuantityArray = new ArrayList<Integer>();
						                deliveredQuantityArray = new ArrayList<Integer>();

						                while (rs.next()) {
						                	orderedQuantityArray.add(rs.getInt("orderedQuantity"));
						                	deliveredQuantityArray.add(rs.getInt("shippedQuantity"));
						                }
						                
						                for(int item=0; item < orderedQuantityArray.size(); item++) {
						                	
						                	
						                	
						                	if(Integer.parseInt(orderedQuantityArray.get(item).toString()) == Integer.parseInt(deliveredQuantityArray.get(item).toString())) {
						                		orderedQuantityIsDelivered++;
						                	}
						                } 
						                if(orderedQuantityArray.size() == orderedQuantityIsDelivered) {
						                	sql = "SELECT * FROM Orders WHERE orderID="+ orderID +"";
							                rs = createStatement.executeQuery(sql);

							                if (rs.next()) {
							                	Orders readyOrder = new Orders(rs.getInt("orderID"), rs.getString("orderedFrom"), rs.getString("orderDatum"));
							                    
							                	sql = "DELETE FROM Orders WHERE orderID = "+ orderID +"";
							                	PreparedStatement preparedStatement = conn.prepareStatement(sql);
						
							                	preparedStatement.execute();
							                	
							                	sql = "INSERT INTO ReadyOrders (orderID, orderedFrom, orderDatum) VALUES (" + readyOrder.getOrderID() +", '" + readyOrder.getOrderedFrom() +"', '" + readyOrder.getOrderDatum() +"')";
							                	preparedStatement = conn.prepareStatement(sql);
						
							                	preparedStatement.execute();
							                	
							                	
							                	fillTableWithData();
							                	infoFrame.dispose();
							                	infoFrame = null;
							                }
							                	
							                
						                } else {
						                	infoPanelTable.getTable().setValueAt("", i, 4);
						                	infoPanelTable.getTable().setValueAt(currentAndAlreadyDeliveredQuantity, i, 3);
						                	
						                }

						               } catch (SQLException ex) {
						                   System.out.println("Error with getOrders quantity");
						                   System.out.println(" "+ex);
						                   } 
		        			    
		        			   } else {
		        				   System.out.println("Current delivered quantity is more than orderd quantity");
		        				   break;
		        			   }
	        			   } else {
	        				   System.out.println("Please select a Stock");
	        				   break;
	        			   }
	        			   
	        		   } 
	       	     }
            	
           }
     });
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
    
    public ArrayList<Goods> getAllGoods(){
        // String sql = "SELECT Goods.itemID, Goods.itemName, SUM (Inventory.itemQuantity) AS QuantityInWarehause FROM Goods, Inventory WHERE Goods.itemID=Inventory.itemID GROUP BY Goods.itemID, Goods.itemName";
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
    
    public void openOrderFrame() {
    	
    	orderPanel = new AddItemOrOrderPanel();
    	orderPanel.layoutComponentsAddOrder(getAllGoods());
    	orderIDField = orderPanel.getOrderIDField();
    	companyNameField = orderPanel.getCompanyNameField();
    	orderDatumField = orderPanel.getOrderDatumField();
    	orderPanelTable = orderPanel.getOrdersTable();
    	
    	JButton orderPanelAddBtn = orderPanel.getAddOrderButton();
    	jTableAddOrder = orderPanelTable.getTable();
    	
	if (orderFrame == null) {
    	   
		  orderFrame = new JFrame();
          
		  orderFrame.setLayout(new BorderLayout());

		  orderFrame.add(orderPanel, BorderLayout.CENTER);
		  orderFrame.setVisible(true);
		  orderFrame.setDefaultCloseOperation(orderFrame.DISPOSE_ON_CLOSE);
		  orderFrame.setResizable(true);
		  orderFrame.pack();
          
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
          Point newLocation = new Point(middle.x - (orderFrame.getWidth() / 2), 
                                        middle.y - (orderFrame.getHeight() / 2));
          orderFrame.setLocation(newLocation);
          
          orderFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	orderFrame = null;
			    }
			});
	    } else {
	    	orderFrame.setVisible(true);
	    }
	
	
	
		orderPanelTable.getModel().addTableModelListener(new TableModelListener() {
		        public void tableChanged(TableModelEvent e) {
		            int row = e.getFirstRow();
		            int column = e.getColumn();
		            if (e.getType() == TableModelEvent.UPDATE && column == 0) {
		            	
		               
		               
		               String itemID = orderPanelTable.getModel().getValueAt(row, 0).toString();
		               
		            if(itemID != "") {
		            	 String sql = "SELECT itemName FROM Goods WHERE itemID="+ Integer.parseInt(itemID) +"";
				       		try {
				               	loadCreateStatement();
				                ResultSet rs = createStatement.executeQuery(sql);

				                if (rs.next()) {
				                	jTableAddOrder.setValueAt(rs.getString("itemName"), row, 1);
				                    
				                }
				                
				                   
				              
				               } catch (SQLException ex) {
				                   System.out.println("Error with setItemName");
				                   System.out.println(" "+ex);
				                   } 
		            } else {
		            	jTableAddOrder.setValueAt("", row, 1);
		            }
		               
		        }
		    }
		
		});
	
		orderPanelAddBtn.addActionListener(new ActionListener(){
			
	        
	        public void actionPerformed(ActionEvent e) {
	            
	        	if (jTableAddOrder.isEditing())
	        		jTableAddOrder.getCellEditor().stopCellEditing();
	        	
	        	ArrayList<OrderedGoods> orderedGoodsArray = null;
	        	ArrayList<OrderedGoods> orderedGoodsArrayFromDB = null;
	        	int orderID = 0;
	        	int orderedItemID;
	        	String itemName;
	        	int orderedQuantity;
	        	int shippedQuantity;
	        	
	        	int missingError = 0;
	        	
	        	Boolean alreadyInArray = false;
	        	
	        	String regex = "\\d+";
	        	
	        	
	        	
	        		if(companyNameField.getText().length() > 2 && orderDatumField.getText().length() > 8) {
	            		
	            		try {
	            			
	            			orderedGoodsArray = new ArrayList<>();

	                        for(int row=0; row < 31; row++) {
	                        	
	                        	if(orderPanelTable.getModel().getValueAt(row, 0).toString() != "") {
	                        		if(orderPanelTable.getModel().getValueAt(row, 2).toString().matches(regex)) {
	                        			orderedItemID = Integer.parseInt(orderPanelTable.getModel().getValueAt(row, 0).toString());
	                    	        	itemName = orderPanelTable.getModel().getValueAt(row, 1).toString();
	                    	        	orderedQuantity = Integer.parseInt(orderPanelTable.getModel().getValueAt(row, 2).toString());
	                    	        	shippedQuantity = 0;
	                    	        	System.out.println("lefutok 1");
	                    	        	alreadyInArray = false;
	                    	        	
	                    	        	for(OrderedGoods orderedGoods : orderedGoodsArray) {
	                    	        	    if(orderedGoods!=null && orderedItemID == orderedGoods.getOrderedItemID()) {
	                    	        	    	
	                    	        	    	orderedGoods.setOrderedItemQuantity(orderedGoods.getOrderedItemQuantity() + orderedQuantity);
	                    	        	    	alreadyInArray = true;
	                    	        	    	System.out.println("lefutok 2");
	                    	        	        break;
	                    	        	    } 
	                    	        	}
	                    	        	if(alreadyInArray == false) {
	                    	        		OrderedGoods actualOrder = new OrderedGoods(orderID, orderedItemID, itemName, orderedQuantity, shippedQuantity);
			                        		orderedGoodsArray.add(actualOrder);
			                        		System.out.println("lefutok 3");
	                    	        	}
		                        		
	                        		} else {
	                        			System.out.println("Ordered item quantity missing or not 0 or a positive number");
	                        			missingError = 1;
	                        			row = 31;
	                        		}
	                    	        	
	                        	} 
	                        		
	                        	
	                        	
		                        	
	                        }
	                        
	                        
	                        
	                        if(missingError == 0 && itemPanelNumber == 1) {
	                        	
	                        	int orderedGoodsArrayLength = orderedGoodsArray.size();
	                        	if(orderedGoodsArrayLength > 0) {
	                        		String sql = "INSERT INTO Orders (orderedFrom, orderDatum) VALUES ('" + companyNameField.getText() +"', '" + orderDatumField.getText() +"')";
			                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			                        preparedStatement.execute();
			                        

			                        String sqlgetOrderID = "SELECT MAX(OrderID) AS LastOrderID FROM Orders";
			                        ResultSet rs = createStatement.executeQuery(sqlgetOrderID);
			                        
			                        if (rs.next()) {
			                        	orderID = rs.getInt("LastOrderID");
			                            
			                        }
		                        	
		                        	for(int i=0; i < orderedGoodsArrayLength; i++) {
			                        	
			                        	
			                        	
			                        	sql = "INSERT INTO OrderedGoods VALUES (?,?,?,?,?)";
				                        preparedStatement = conn.prepareStatement(sql);
				                        preparedStatement.setInt(1, orderID);
				                        preparedStatement.setInt(2, orderedGoodsArray.get(i).getOrderedItemID());
				                        preparedStatement.setString(3, orderedGoodsArray.get(i).getOrderedItemName());
				                        preparedStatement.setInt(4, orderedGoodsArray.get(i).getOrderedItemQuantity());
				                        preparedStatement.setInt(5, orderedGoodsArray.get(i).getShippedQuantity());
				                        preparedStatement.execute();
			                        }
			                        
			                        fillTableWithData();
				            		orderFrame.dispose(); 
				            		orderFrame = null;
	                        	} else {
	                        		System.out.println("Please add min one item to the Order");
	                        	}
	                        	
	                        }
	                        
	                        if(missingError == 0 && itemPanelNumber == 2) {
	                        	
	                        	System.out.println("Edit lefut 1");
	                        	
	                        	orderedGoodsArrayFromDB = new ArrayList<>();
	                        	orderID = Integer.parseInt(orderIDField.getText());
	                        			
	                        	System.out.println(orderID);
	                        		
	                        		String sql = "SELECT * FROM OrderedGoods WHERE orderID="+ orderID +"";
			                        ResultSet rs = createStatement.executeQuery(sql);
			                        
			                        while (rs.next()) {
			                        	OrderedGoods actualOrderFromDB = new OrderedGoods(rs.getInt("orderID"), rs.getInt("orderedItemID"), rs.getString("itemName"), rs.getInt("orderedQuantity"), rs.getInt("shippedQuantity"));
			                        	orderedGoodsArrayFromDB.add(actualOrderFromDB);
			                        	
			                        	System.out.println(orderedGoodsArrayFromDB.size() + " FromDB Size");
			                        	}
			                        
			                        System.out.println(orderedGoodsArrayFromDB.size() + " FromDB Size outside");
			                        
			                        ArrayList<OrderedGoods> orderedGoodsToInsert = new ArrayList<>();
			                        ArrayList<OrderedGoods> orderedGoodsToUpdate = new ArrayList<>();
			                        ArrayList<OrderedGoods> orderedGoodsToDelet = new ArrayList<>();
			                        
			                        Boolean itemFound = false;
			                        
			                        int forCount = 0;
			                        
			                        
			                        for(int i=0; i < orderedGoodsArray.size(); i++) {
			                        	
			                        	if(itemFound == true) {
			                        		i--;
			                        		itemFound = false;
			                        	}
			                        	
			                        	for(int fromDB=0; fromDB < orderedGoodsArrayFromDB.size(); fromDB++) {
			                        		
			                        		
		                        			System.out.println("Ez az forCount: " + forCount);
		                        			forCount++;
			                        		
			                        		if(itemFound == true) {
			                        			
			                        			itemFound = false;
			                        			System.out.println("Ez az i: " + i);
			                        			System.out.println("Ez az fromDB: " + fromDB);
			                        			System.out.println("Ez az orderedFromDB: " + orderedGoodsArrayFromDB.size());
			                        			System.out.println("Ez az orderedArray: " + orderedGoodsArray.size());
			                        			
			                        		}
			                        		if(orderedGoodsArray.get(i).getOrderedItemID() == orderedGoodsArrayFromDB.get(fromDB).getOrderedItemID()) {
			                        			orderedGoodsToUpdate.add(orderedGoodsArray.get(i));
			                        			orderedGoodsArray.remove(i);			                        			
			                        			orderedGoodsArrayFromDB.remove(fromDB);
			                        			itemFound = true;
			                        			fromDB--;
			                        			
			                        			System.out.println("Lefut remove from arrays");
			                        		} 
			                        		System.out.println("Lefut remove from arrays után 1");
			                        	}
			                        	System.out.println("Lefut remove from arrays után 2");
			                        }
			                       
			                        orderedGoodsToDelet.addAll(orderedGoodsArrayFromDB);			                        
			                        
			                        orderedGoodsToInsert.addAll(orderedGoodsArray);

			                        
			                        System.out.println(orderedGoodsArrayFromDB.size() + " 0");
			                        System.out.println(orderedGoodsArray.size() + " 0.1");
			                        
			                        System.out.println(orderedGoodsToDelet.size() + " 1");
			                        System.out.println(orderedGoodsToUpdate.size() + " 2");
			                        System.out.println(orderedGoodsToInsert.size() + " 3");
			                        
			                        for(int i=0; i < orderedGoodsToDelet.size(); i++) {
				                        
		                        		sql = "DELETE FROM OrderedGoods WHERE orderID="+ orderID +" AND orderedItemID="+ orderedGoodsToDelet.get(i).getOrderedItemID() +"";
		                        		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		                    		
				                        preparedStatement.execute();
				                        
				                        System.out.println("Edit lefut delete");
			                        }
	                        		
			                        for(int i=0; i < orderedGoodsToInsert.size(); i++) {
			                        
	                        		sql = "INSERT INTO OrderedGoods VALUES (?,?,?,?,?) ";
	                        		PreparedStatement preparedStatement = conn.prepareStatement(sql);
	                    			
		                        	
			                        preparedStatement = conn.prepareStatement(sql);
			                        preparedStatement.setInt(1, orderID);
			                        preparedStatement.setInt(2, orderedGoodsArray.get(i).getOrderedItemID());
			                        preparedStatement.setString(3, orderedGoodsArray.get(i).getOrderedItemName());
			                        preparedStatement.setInt(4, orderedGoodsArray.get(i).getOrderedItemQuantity());
			                        preparedStatement.setInt(5, orderedGoodsArray.get(i).getShippedQuantity());
			                        preparedStatement.execute();
			                        
			                        System.out.println("Edit lefut insert");
		                        }
			                        
			                        for(int i=0; i < orderedGoodsToUpdate.size(); i++) {
				                        
		                        		sql = "UPDATE OrderedGoods SET orderedQuantity="+ orderedGoodsToUpdate.get(i).getOrderedItemQuantity() +" WHERE orderID="+ orderID +" AND orderedItemID="+ orderedGoodsToUpdate.get(i).getOrderedItemID() +"";
		                        		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		                    		
				                        preparedStatement.execute();
				                        
				                        System.out.println("Edit lefut update");
			                        }
	                        	fillTableWithData();
			            		orderFrame.dispose(); 
			            		orderFrame = null;
	                        }
	                        
	                  } catch (SQLException ex) {
	                      System.out.println("Valami baj van az Item hozzáadásakor");
	                      System.out.println(""+ex);
	                  }
	            		
	            		
	          		            		
	            	} else {
	            		System.out.println("Correct order info missing.");
	            	} 
	        	
	        	
	        	
	        	
	        }


});
    	
    }
    
    public void fillTableWithDataEditButton(){
    	
    	int row = jTable.getSelectedRow();
    	int orderID = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
    	
		for (int i = orderPanelTable.getModel().getRowCount() - 1; i > -1; i--) {
			orderPanelTable.getModel().removeRow(i);
	     }
	    
		
		
		ArrayList<OrderedGoods> list = getOrderedItems(orderID);
	       Object rowData[] = new Object[4];
	       for(int i = 0; i < list.size(); i++ ){
	           rowData[0] = list.get(i).getOrderedItemID();
	           rowData[1] = list.get(i).getOrderedItemName();
	           rowData[2] = list.get(i).getOrderedItemQuantity();
	           rowData[3] = list.get(i).getShippedQuantity();
	           
	           orderPanelTable.getModel().addRow(rowData);
	           }; 
	       
	           if(list.size() < 31) {
	        	  int emptyRows = 31 - list.size();
	        	  
	        	  for(int i=0; i < emptyRows; i++) {
	        		  orderPanelTable.getModel().addRow(new Object[]{"","",""});
	        	  }
	           }
	    }
    
    public void addOrderButtonPressed(){
    	addOrderBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
            	itemPanelNumber = 1;
            	openOrderFrame();
            	
           }
     });
   }
    
    public void editOrderButtonPressed(){
    	editOrderBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
            	

				int row = jTable.getSelectedRow();
            	
            	if(jTable.getSelectionModel().isSelectionEmpty() == false) {
            		itemPanelNumber = 2;
            		openOrderFrame();
                	fillTableWithDataEditButton();
            		
            		orderIDField.setText((table.getModel().getValueAt(row, 0).toString()));
            		companyNameField.setText(table.getModel().getValueAt(row, 1).toString());
                	orderDatumField.setText(table.getModel().getValueAt(row, 2).toString());
            	} else {
            		System.out.println("Nothing is selected");
            	}
           }
     });
   }
    
    private void deleteButtonPressed() {
    	deleteOrderBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	
            	int action = JOptionPane.showConfirmDialog(OrdersPanel.this,
                        "Do you really want to delete this Order?",
                        "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
            	if(action == JOptionPane.OK_OPTION && jTable.getSelectionModel().isSelectionEmpty() == false) {
            		
            		int row = jTable.getSelectedRow();
                                       
                    
                    int idToInt = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
                    System.out.println(idToInt);
                    try {
                        String sql = "DELETE FROM OrderedGoods WHERE orderID = "+ idToInt +"";
                        
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);

                        preparedStatement.execute();
                        
                  } catch (SQLException ex) {
                      System.out.println("Valami baj van az Order törlésekor");
                      System.out.println(""+ex);
                  } 
                    
                    try {
                        String sql = "DELETE FROM Orders WHERE orderID = "+ idToInt +"";
                        
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);

                        preparedStatement.execute();
                        
                        
                  } catch (SQLException ex) {
                      System.out.println("Valami baj van az Order törlésekor");
                      System.out.println(""+ex);
                      System.out.println();
                  } 
                    
                    table.getModel().removeRow(row);
            	}
                
                 }
        });
    }
    
   /* public Orders getOrder(int id){
        String sql = "SELECT * FROM Orders WHERE orderID= "+ id +"";
        Orders order = null;
         loadCreateStatement();
         try {
             ResultSet rs = createStatement.executeQuery(sql);

         while (rs.next()){
        	 order = new Orders(rs.getInt("orderID"), "", "");
         	
             }
         } catch (SQLException ex) {
             System.out.println("Error with getAllGoods");
             System.out.println(" "+ex);
             }    
         
          return order;
 }      */
    
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
    
}

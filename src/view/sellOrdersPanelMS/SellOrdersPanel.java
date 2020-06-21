package view.sellOrdersPanelMS;

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
import model.SellOrders;
import model.SoldGoods;
import view.classesForPanels.AddItemOrOrderPanel;
import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;

public class SellOrdersPanel extends JPanel{

	private SearchPanel searchSellOrders;
	private String[] comboboxEl;
	private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private SellOrdersInfoPanel infoPanel;
    private Table infoPanelTable;
    private JTextField infoPanelOrderIDField;
    private JTextField infoPanelCompanyNField;
    private JTextField infoPanelOrderDField;
    
    private JPanel topButtonsPanel;
    private JButton addOrderBtn;
    private JButton editOrderBtn;
    private JButton deleteOrderBtn;
    
    private JFrame addOrderFrame;
    private AddItemOrOrderPanel AddOrderPanel;
    
    private Cursor cursor;
    
    private JTable jTable;
    
    private JTable jTableAddSellOrder;
    
    private JTextField orderIDField;
    private JTextField companyNameField;
    private JTextField orderDatumField;
    private Table orderPanelTable;
    
    private Connection conn;
    private Statement createStatement = null;
    
    private int itemPanelNumber;
    
    public SellOrdersPanel() {
    	
    	columnNames = new String[] {"Sell order-ID", "Company name", "Order datum"};
        table = new Table(columnNames);
        comboboxEl = new String[] {"Sell order-ID", "Company name", "Order datum"};
        searchSellOrders = new SearchPanel(comboboxEl);
        searchField = searchSellOrders.getSearchField();
        searchButton = searchSellOrders.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchSellOrders.getSearchCombo();
        
        infoPanel = new SellOrdersInfoPanel();
        infoPanelTable = infoPanel.getSellOrdersTable();
        
        topButtonsPanel = new JPanel();
        addOrderBtn = new JButton("Add order");
        editOrderBtn = new JButton("Edit order");
        deleteOrderBtn = new JButton("Delete order");
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        addOrderBtn.setCursor(cursor);
        editOrderBtn.setCursor(cursor);
        deleteOrderBtn.setCursor(cursor);
        
        topButtonsPanel.setPreferredSize(new Dimension(50, 50));
        searchSellOrders.setPreferredSize(new Dimension(50, 50));
        
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
        add(searchSellOrders);
        add(table);
        
        table.fillWithData();
        table.newInformationFrameIfClicked(infoPanel);
        
        jTable = table.getTable();
        
        addOrderButtonPressed();
        editOrderButtonPressed();
        deleteButtonPressed();
        newOrdersInfoPanelTable();
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
    
    public ArrayList<SellOrders> getAllSellOrders(){
     	String sql = "SELECT * FROM SellOrders";
     	ArrayList<SellOrders> sellOrders = null;
         loadCreateStatement();
         try {
             ResultSet rs = createStatement.executeQuery(sql);
             sellOrders = new ArrayList<>();

         while (rs.next()){
        	 SellOrders actualOrder = new SellOrders(rs.getInt("sellOrderID"), rs.getString("soldFor"), rs.getString("sellOrderDatum"));
         	sellOrders.add(actualOrder);
             }
         } catch (SQLException ex) {
             System.out.println("Error with getAllOrders");
             System.out.println(" "+ex);
             }    
         
          return sellOrders;
 }
    public ArrayList<SoldGoods> getSoldItems(int orderID){
     	String sql = "SELECT * FROM SoldGoods WHERE sellOrderID="+ orderID +"";
     	ArrayList<SoldGoods> soldGoods = null;
         loadCreateStatement();
         try {
             ResultSet rs = createStatement.executeQuery(sql);
             soldGoods = new ArrayList<>();

         while (rs.next()){
        	 SoldGoods actualOrderedItem = new SoldGoods(rs.getInt("sellOrderID"), rs.getInt("soldItemID"), rs.getString("itemName"), rs.getInt("soldQuantity"), rs.getInt("pickedQuantity"));
        	soldGoods.add(actualOrderedItem);
             }
         } catch (SQLException ex) {
             System.out.println("Error with getAllSellOrders");
             System.out.println(" "+ex);
             }    
         
          return soldGoods;
 }
    
    public void newOrdersInfoPanelTable() {
    	jTable.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	           if (e.getClickCount() == 2) {
	        	   infoPanelOrderIDField = infoPanel.getSellOrderIDField();
	        	   infoPanelCompanyNField = infoPanel.getCompanyNameField();
	        	   infoPanelOrderDField = infoPanel.getSellOrderDatumField();
	        	   
	        	   int row = jTable.getSelectedRow();
	          
	        	   int orderID = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	        	   System.out.println(orderID);
	        	   
	        	   infoPanelOrderIDField.setText(table.getModel().getValueAt(row, 0).toString());
	        	   infoPanelCompanyNField.setText(table.getModel().getValueAt(row, 1).toString());
	        	   infoPanelOrderDField.setText(table.getModel().getValueAt(row, 2).toString());
	        	   
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
	     });
	}
    
public void fillTableWithData(){
		
		for (int i = table.getModel().getRowCount() - 1; i > -1; i--) {
			table.getModel().removeRow(i);
	     }
	        
		ArrayList<SellOrders> list = getAllSellOrders();
	       Object rowData[] = new Object[3];
	       for(int i = 0; i < list.size(); i++ ){
	           rowData[0] = list.get(i).getSellOrderID();
	           rowData[1] = list.get(i).getSoldFor();
	           rowData[2] = list.get(i).getSellOrderDatum();
	           
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
    	
    	AddOrderPanel = new AddItemOrOrderPanel();
    	AddOrderPanel.layoutComponentsAddSellOrder(getAllGoods());
    	orderIDField = AddOrderPanel.getOrderIDField();
    	companyNameField = AddOrderPanel.getCompanyNameField();
    	orderDatumField = AddOrderPanel.getOrderDatumField();
    	orderPanelTable = AddOrderPanel.getOrdersTable();
    	
    	JButton orderPanelAddBtn = AddOrderPanel.getAddOrderButton();
    	jTableAddSellOrder = orderPanelTable.getTable();
    	
	if (addOrderFrame == null) {
    	   
		  addOrderFrame = new JFrame();
          
		  addOrderFrame.setLayout(new BorderLayout());

		  addOrderFrame.add(AddOrderPanel, BorderLayout.CENTER);
		  addOrderFrame.setVisible(true);
		  addOrderFrame.setDefaultCloseOperation(addOrderFrame.DISPOSE_ON_CLOSE);
		  addOrderFrame.setResizable(true);
		  addOrderFrame.pack();
          
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
          Point newLocation = new Point(middle.x - (addOrderFrame.getWidth() / 2), 
                                        middle.y - (addOrderFrame.getHeight() / 2));
          addOrderFrame.setLocation(newLocation);
          
          addOrderFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	addOrderFrame = null;
			    }
			});
	    } else {
	    	addOrderFrame.setVisible(true);
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
		                	jTableAddSellOrder.setValueAt(rs.getString("itemName"), row, 1);
		                    
		                }
		                
		                   
		              
		               } catch (SQLException ex) {
		                   System.out.println("Error with setItemName");
		                   System.out.println(" "+ex);
		                   } 
            } else {
            	jTableAddSellOrder.setValueAt("", row, 1);
            }
               
        }
    }

});
	
	orderPanelAddBtn.addActionListener(new ActionListener(){
		
        
        public void actionPerformed(ActionEvent e) {
            
        	if (jTableAddSellOrder.isEditing())
        		jTableAddSellOrder.getCellEditor().stopCellEditing();
        	
        	ArrayList<SoldGoods> soldGoodsArray = null;
        	ArrayList<SoldGoods> soldGoodsArrayFromDB = null;
        	int orderID = 0;
        	int soldItemID;
        	String itemName;
        	int soldQuantity;
        	int pickedQuantity;
        	
        	int missingError = 0;
        	
        	Boolean alreadyInArray = false;
        	
        	String regex = "\\d+";
        	
        	
        	
        		if(companyNameField.getText().length() > 2 && orderDatumField.getText().length() > 8) {
            		
            		try {
            			
            			soldGoodsArray = new ArrayList<>();

                        for(int row=0; row < 31; row++) {
                        	
                        	if(orderPanelTable.getModel().getValueAt(row, 0).toString() != "") {
                        		if(orderPanelTable.getModel().getValueAt(row, 2).toString().matches(regex)) {
                        			soldItemID = Integer.parseInt(orderPanelTable.getModel().getValueAt(row, 0).toString());
                    	        	itemName = orderPanelTable.getModel().getValueAt(row, 1).toString();
                    	        	soldQuantity = Integer.parseInt(orderPanelTable.getModel().getValueAt(row, 2).toString());
                    	        	pickedQuantity = 0;
                    	        	System.out.println("lefutok 1");
                    	        	alreadyInArray = false;
                    	        	
                    	        	for(SoldGoods soldGoods : soldGoodsArray) {
                    	        	    if(soldGoods!=null && soldItemID == soldGoods.getSoldItemID()) {
                    	        	    	
                    	        	    	soldGoods.setSoldItemQuantity(soldGoods.getSoldItemQuantity() + soldQuantity);
                    	        	    	alreadyInArray = true;
                    	        	    	System.out.println("lefutok 2");
                    	        	        break;
                    	        	    } 
                    	        	}
                    	        	if(alreadyInArray == false) {
                    	        		SoldGoods actualOrder = new SoldGoods(orderID, soldItemID, itemName, soldQuantity, pickedQuantity);
		                        		soldGoodsArray.add(actualOrder);
		                        		System.out.println("lefutok 3");
                    	        	}
	                        		
                        		} else {
                        			System.out.println("Sold item quantity missing or not 0 or a positive number");
                        			missingError = 1;
                        			row = 31;
                        		}
                    	        	
                        	} 
                        		
                        	
                        	
	                        	
                        }
                        
                        
                        
                        if(missingError == 0 && itemPanelNumber == 1) {
                        	
                        	int soldGoodsArrayLength = soldGoodsArray.size();
                        	if(soldGoodsArrayLength > 0) {
                        		String sql = "INSERT INTO SellOrders (soldFor, sellOrderDatum) VALUES ('" + companyNameField.getText() +"', '" + orderDatumField.getText() +"')";
		                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
		
		                        preparedStatement.execute();
		                        

		                        String sqlgetOrderID = "SELECT MAX(sellOrderID) AS LastOrderID FROM SellOrders";
		                        ResultSet rs = createStatement.executeQuery(sqlgetOrderID);
		                        
		                        if (rs.next()) {
		                        	orderID = rs.getInt("LastOrderID");
		                            
		                        }
	                        	
	                        	for(int i=0; i < soldGoodsArrayLength; i++) {
		                        	
		                        	
		                        	
		                        	sql = "INSERT INTO SoldGoods VALUES (?,?,?,?,?)";
			                        preparedStatement = conn.prepareStatement(sql);
			                        preparedStatement.setInt(1, orderID);
			                        preparedStatement.setInt(2, soldGoodsArray.get(i).getSoldItemID());
			                        preparedStatement.setString(3, soldGoodsArray.get(i).getSoldItemName());
			                        preparedStatement.setInt(4, soldGoodsArray.get(i).getSoldItemQuantity());
			                        preparedStatement.setInt(5, soldGoodsArray.get(i).getPickedQuantity());
			                        preparedStatement.execute();
		                        }
		                        
		                        fillTableWithData();
		                        addOrderFrame.dispose(); 
		                        addOrderFrame = null;
                        	} else {
                        		System.out.println("Please add min one item to the Order");
                        	}
                        	
                        }
                        
                        if(missingError == 0 && itemPanelNumber == 2) {
                        	
                        	System.out.println("Edit lefut 1");
                        	
                        	soldGoodsArrayFromDB = new ArrayList<>();
                        	orderID = Integer.parseInt(orderIDField.getText());
                        			
                        	System.out.println(orderID);
                        		
                        		String sql = "SELECT * FROM SoldGoods WHERE sellOrderID="+ orderID +"";
		                        ResultSet rs = createStatement.executeQuery(sql);
		                        
		                        while (rs.next()) {
		                        	SoldGoods actualOrderFromDB = new SoldGoods(rs.getInt("sellOrderID"), rs.getInt("soldItemID"), rs.getString("itemName"), rs.getInt("soldQuantity"), rs.getInt("pickedQuantity"));
		                        	soldGoodsArrayFromDB.add(actualOrderFromDB);
		                        	
		                        	System.out.println(soldGoodsArrayFromDB.size() + " FromDB Size");
		                        	}
		                        
		                        System.out.println(soldGoodsArrayFromDB.size() + " FromDB Size outside");
		                        
		                        ArrayList<SoldGoods> soldGoodsToInsert = new ArrayList<>();
		                        ArrayList<SoldGoods> soldGoodsToUpdate = new ArrayList<>();
		                        ArrayList<SoldGoods> soldGoodsToDelet = new ArrayList<>();
		                        
		                        Boolean itemFound = false;
		                        
		                        int forCount = 0;
		                        
		                        
		                        for(int i=0; i < soldGoodsArray.size(); i++) {
		                        	
		                        	if(itemFound == true) {
		                        		i--;
		                        		itemFound = false;
		                        	}
		                        	
		                        	for(int fromDB=0; fromDB < soldGoodsArrayFromDB.size(); fromDB++) {
		                        		
		                        		
	                        			System.out.println("Ez az forCount: " + forCount);
	                        			forCount++;
		                        		
		                        		if(itemFound == true) {
		                        			
		                        			itemFound = false;
		                        			System.out.println("Ez az i: " + i);
		                        			System.out.println("Ez az fromDB: " + fromDB);
		                        			System.out.println("Ez az orderedFromDB: " + soldGoodsArrayFromDB.size());
		                        			System.out.println("Ez az orderedArray: " + soldGoodsArray.size());
		                        			
		                        		}
		                        		if(soldGoodsArray.get(i).getSoldItemID() == soldGoodsArrayFromDB.get(fromDB).getSoldItemID()) {
		                        			soldGoodsToUpdate.add(soldGoodsArray.get(i));
		                        			soldGoodsArray.remove(i);			                        			
		                        			soldGoodsArrayFromDB.remove(fromDB);
		                        			itemFound = true;
		                        			fromDB--;
		                        			
		                        			System.out.println("Lefut remove from arrays");
		                        		} 
		                        		System.out.println("Lefut remove from arrays után 1");
		                        	}
		                        	System.out.println("Lefut remove from arrays után 2");
		                        }
		                       
		                        soldGoodsToDelet.addAll(soldGoodsArrayFromDB);			                        
		                        
		                        soldGoodsToInsert.addAll(soldGoodsArray);

		                        
		                        System.out.println(soldGoodsArrayFromDB.size() + " 0");
		                        System.out.println(soldGoodsArray.size() + " 0.1");
		                        
		                        System.out.println(soldGoodsToDelet.size() + " 1");
		                        System.out.println(soldGoodsToUpdate.size() + " 2");
		                        System.out.println(soldGoodsToInsert.size() + " 3");
		                        
		                        for(int i=0; i < soldGoodsToDelet.size(); i++) {
			                        
	                        		sql = "DELETE FROM SoldGoods WHERE sellOrderID="+ orderID +" AND soldItemID="+ soldGoodsToDelet.get(i).getSoldItemID() +"";
	                        		PreparedStatement preparedStatement = conn.prepareStatement(sql);
	                    		
			                        preparedStatement.execute();
			                        
			                        System.out.println("Edit lefut delete");
		                        }
                        		
		                        for(int i=0; i < soldGoodsToInsert.size(); i++) {
		                        
                        		sql = "INSERT INTO SoldGoods VALUES (?,?,?,?,?) ";
                        		PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    			
	                        	
		                        preparedStatement = conn.prepareStatement(sql);
		                        preparedStatement.setInt(1, orderID);
		                        preparedStatement.setInt(2, soldGoodsArray.get(i).getSoldItemID());
		                        preparedStatement.setString(3, soldGoodsArray.get(i).getSoldItemName());
		                        preparedStatement.setInt(4, soldGoodsArray.get(i).getSoldItemQuantity());
		                        preparedStatement.setInt(5, soldGoodsArray.get(i).getPickedQuantity());
		                        preparedStatement.execute();
		                        
		                        System.out.println("Edit lefut insert");
	                        }
		                        
		                        for(int i=0; i < soldGoodsToUpdate.size(); i++) {
			                        
	                        		sql = "UPDATE SoldGoods SET soldQuantity="+ soldGoodsToUpdate.get(i).getSoldItemQuantity() +" WHERE sellOrderID="+ orderID +" AND soldItemID="+ soldGoodsToUpdate.get(i).getSoldItemID() +"";
	                        		PreparedStatement preparedStatement = conn.prepareStatement(sql);
	                    		
			                        preparedStatement.execute();
			                        
			                        System.out.println("Edit lefut update");
		                        }
                        	fillTableWithData();
                        	addOrderFrame.dispose(); 
                        	addOrderFrame = null;
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
	    
		
		
		ArrayList<SoldGoods> list = getSoldItems(orderID);
	       Object rowData[] = new Object[4];
	       for(int i = 0; i < list.size(); i++ ){
	           rowData[0] = list.get(i).getSoldItemID();
	           rowData[1] = list.get(i).getSoldItemName();
	           rowData[2] = list.get(i).getSoldItemQuantity();
	           rowData[3] = list.get(i).getPickedQuantity();
	           
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
            	int action = JOptionPane.showConfirmDialog(SellOrdersPanel.this,
                        "Do you really want to delete this Order?",
                        "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
            	if(action == JOptionPane.OK_OPTION && jTable.getSelectionModel().isSelectionEmpty() == false) {
            		
            		int row = jTable.getSelectedRow();
                                       
                    
                    int idToInt = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
                    System.out.println(idToInt);
                    try {
                        String sql = "DELETE FROM SoldGoods WHERE sellOrderID = "+ idToInt +"";
                        
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);

                        preparedStatement.execute();
                        
                  } catch (SQLException ex) {
                      System.out.println("Valami baj van az Order törlésekor");
                      System.out.println(""+ex);
                  } 
                    
                    try {
                        String sql = "DELETE FROM SellOrders WHERE sellOrderID = "+ idToInt +"";
                        
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

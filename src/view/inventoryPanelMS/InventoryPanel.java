package view.inventoryPanelMS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.Goods;
import model.Inventory;
import model.OrderedGoods;
import model.SoldGoods;
import model.Users;
import view.MainFrame;
import view.classesForPanels.AddItemOrOrderPanel;
import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;


public class InventoryPanel extends JPanel {

	private SearchPanel searchInventory;
	private String[] comboboxEl;
	private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private GoodsInfoPanel gIPanel;
    private JTextField gIPanelItemIDField;
    private JTextField gIPanelItemNameField;
    private Table gIPanelBuyingTable;
    private Table gIPanelstockTable;
    private Table gIPanelSellingTable;
    private Table gIPanelQuantityTable;
    
    private JPanel topButtonsPanel;
    private JButton addItemBtn;
    private JButton editItemBtn;
    private JButton deleteItemBtn;
    
    private JFrame itemFrame;
    private AddItemOrOrderPanel itemPanel;
    
    private Cursor cursor;
    
    private JTable jTable;
    
    private JTextField itemID;
    private JTextField itemName;
    
    private Connection conn;
    private Statement createStatement = null;
    
    private int itemPanelNumber;
    
    public InventoryPanel() {
    	
    	columnNames = new String[] {"Item-ID", "Item name", "Quantity in warehouse"};
        table = new Table(columnNames);
        
        comboboxEl = new String[] {"Item-ID", "Item name"};
        searchInventory = new SearchPanel(comboboxEl);
        searchField = searchInventory.getSearchField();
        searchButton = searchInventory.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchInventory.getSearchCombo();
        gIPanel = new GoodsInfoPanel();
        
        topButtonsPanel = new JPanel();
        addItemBtn = new JButton("Add item");
        editItemBtn = new JButton("Edit item");
        deleteItemBtn = new JButton("Delete item");
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        addItemBtn.setCursor(cursor);
        editItemBtn.setCursor(cursor);
        deleteItemBtn.setCursor(cursor);
        
        topButtonsPanel.setPreferredSize(new Dimension(50, 50));
        searchInventory.setPreferredSize(new Dimension(50, 50));
        
        FlowLayout ItemPanelLayout = new FlowLayout();
        topButtonsPanel.setLayout(ItemPanelLayout);
        topButtonsPanel.add(addItemBtn);
        topButtonsPanel.add(editItemBtn);
        topButtonsPanel.add(deleteItemBtn);
	    Border innerBorderItem = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderItem = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        topButtonsPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderItem, innerBorderItem));
        
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        
        add(topButtonsPanel);
        add(searchInventory);
        add(table);
        
        jTable = table.getTable();
        
        table.newInformationFrameIfClicked(gIPanel);
        
        addItemButtonPressed();
        
        editButtonPressed();
        
        deleteButtonPressed();
        
        search();
        
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
    
    public ArrayList<Goods> getAllGoods(){
        String sql = "SELECT Goods.itemID, Goods.itemName, SUM (Inventory.itemQuantity) AS QuantityInWarehause FROM Goods, Inventory WHERE Goods.itemID=Inventory.itemID GROUP BY Goods.itemID, Goods.itemName";
    
    	ArrayList<Goods> goods = null;
        loadCreateStatement();
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            goods = new ArrayList<>();

        while (rs.next()){
        	Goods actualItem = new Goods(rs.getInt("itemID"), rs.getString("itemName"), rs.getInt("QuantityInWarehause"));
        	goods.add(actualItem);
            }
        
    	sql = "SELECT * FROM Goods WHERE itemID NOT IN(SELECT itemID FROM Inventory)";
    	
    	
    	rs = createStatement.executeQuery(sql);
    	
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
    
    
    
    private void openItemFrame() {
    	
    	
    	itemPanel = new AddItemOrOrderPanel();
        itemPanel.layoutComponentsAddItem();	
        itemID = itemPanel.getNewItemIDField();
        itemName = itemPanel.getNewItemNameField();
        
        JButton itemPanelAddBtn = itemPanel.getAddItemButton();
        
	if (itemFrame == null) {
    	   
		  itemFrame = new JFrame();
          
		  itemFrame.setLayout(new BorderLayout());

		  itemFrame.add(itemPanel, BorderLayout.CENTER);
		  itemFrame.setVisible(true);
		  itemFrame.setDefaultCloseOperation(itemFrame.DISPOSE_ON_CLOSE);
		  itemFrame.setResizable(false);
		  itemFrame.pack();
          
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
          Point newLocation = new Point(middle.x - (itemFrame.getWidth() / 2), 
                                        middle.y - (itemFrame.getHeight() / 2));
          itemFrame.setLocation(newLocation);
          
          itemFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	itemFrame = null;
			    }
			});
	    } else {
	    	itemFrame.setVisible(true);
	    }
	
	
		itemPanelAddBtn.addActionListener(new ActionListener(){
	
	            
	            public void actionPerformed(ActionEvent e) {
	                
	            	if(itemPanelNumber == 1) {
	            		if(itemName.getText().length() > 2) {
		            		
		            		try {
		                        String sql = "INSERT INTO Goods (itemName) VALUES ('" + itemName.getText() +"')";
		                        PreparedStatement preparedStatement = conn.prepareStatement(sql);

		                        preparedStatement.execute();
		                        
		                  } catch (SQLException ex) {
		                      System.out.println("Valami baj van az Item hozzáadásakor");
		                      System.out.println(""+ex);
		                  }
		            		
		            		fillTableWithData();
		            		itemFrame.dispose(); 
		            		itemFrame = null;
		          		            		
		            	} else {
		            		System.out.println("Correct item name missing.");
		            	} 
	            	} else if(itemPanelNumber == 2) {
	            		if(itemName.getText().length() > 2) {
		            		
		            		try {
		            			int idToInt = Integer.parseInt(itemID.getText());
		            			
		                        String sql = "Update Goods SET itemName = '" + itemName.getText() +"' WHERE itemID = " + idToInt + "";
		                       
		                        PreparedStatement preparedStatement = conn.prepareStatement(sql);

		                        preparedStatement.execute();
		                        
		                  } catch (SQLException ex) {
		                      System.out.println("Valami baj van az Item megváltoztatásakor");
		                      System.out.println(""+ex);
		                  }
		          
		            		fillTableWithData();
		            		itemFrame.dispose(); 
		            		itemFrame = null;
		            	} else {
		            		System.out.println("Correct item name missing.");
		            	} 
	        		}
	            	
	            	
	            	
	            }
		
	
	    });
    }
    
    
    public void addItemButtonPressed(){
    	addItemBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {        	
            	itemPanelNumber = 1;
            	openItemFrame();
            }
     });
   }
    
    private void editButtonPressed() {
    	editItemBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	int row = jTable.getSelectedRow();
            	
            	if(jTable.getSelectionModel().isSelectionEmpty() == false) {
            		itemPanelNumber = 2;
            		openItemFrame();
            		
                	
                	itemID.setText(table.getModel().getValueAt(row, 0).toString());
                	itemName.setText(table.getModel().getValueAt(row, 1).toString());
            	} else {
            		System.out.println("Nothing is selected");
            	}
            	
                 }
        });
    }
   
    private void deleteButtonPressed() {
    	deleteItemBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	
            	int action = JOptionPane.showConfirmDialog(InventoryPanel.this,
                        "Do you really want to delete this Item? It will delete from Stock and from Orders also",
                        "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
            	if(action == JOptionPane.OK_OPTION && jTable.getSelectionModel().isSelectionEmpty() == false) {
            		
            		int row = jTable.getSelectedRow();
                    
                    
                    int idToInt = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
                    table.getModel().removeRow(row);
                    try {
                    	String sql = "DELETE FROM Inventory WHERE itemID = " + idToInt + "";
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);

                        preparedStatement.execute();
                        
                        sql = "DELETE FROM SoldGoods WHERE soldItemID = " + idToInt + "";
                        preparedStatement = conn.prepareStatement(sql);

                        preparedStatement.execute();
                        
                        sql = "DELETE FROM OrderedGoods WHERE orderedItemID = " + idToInt + "";
                        preparedStatement = conn.prepareStatement(sql);

                        preparedStatement.execute();
                        
                        
                        sql = "DELETE FROM Goods WHERE itemID = " + idToInt + "";
                        preparedStatement = conn.prepareStatement(sql);

                        preparedStatement.execute();
                        
                        
                        
                  } catch (SQLException ex) {
                      System.out.println("Valami baj van az Item törlésekor");
                      System.out.println(""+ex);
                  } 
          
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
    
    public void newOrdersInfoPanelTable() {
    	jTable.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	           if (e.getClickCount() == 2) {
	        	   gIPanelItemIDField = gIPanel.getItemIDTextField();
	        	   gIPanelItemNameField = gIPanel.getItemNameTextField();
	        	   gIPanelBuyingTable = gIPanel.getBuyingOrdersTable();
	        	   gIPanelstockTable = gIPanel.getStocksTable();
	        	   gIPanelSellingTable = gIPanel.getSellingOrdersTable();
	        	   gIPanelQuantityTable = gIPanel.getQuantityTable();
	        	   
	        	   int row = jTable.getSelectedRow();
	          
	        	   
	        	   gIPanelItemIDField.setText(table.getModel().getValueAt(row, 0).toString());
	        	   gIPanelItemNameField.setText(table.getModel().getValueAt(row, 1).toString());
	        	   
	        	   gIPanelBuyingTable.deleteRows();
	        	   gIPanelstockTable.deleteRows();
	        	   gIPanelSellingTable.deleteRows();
	        	   gIPanelQuantityTable.deleteRows();

	       	        
	       		ArrayList<Inventory> listStock = getStocksQuantity(table.getModel().getValueAt(row, 0).toString());
	       	       Object rowDataStock[] = new Object[2];
	       	       for(int i = 0; i < listStock.size(); i++ ){
	       	    	rowDataStock[0] = listStock.get(i).getStockName();
	       	    	rowDataStock[1] = listStock.get(i).getItemQuantityInStock();

	       	           
	       	           gIPanelstockTable.getModel().addRow(rowDataStock);
	       	           }; 
	       	           
	       	        
	              
	       	    ArrayList<OrderedGoods> listOrdered = getItemFromOrderedGoods(table.getModel().getValueAt(row, 0).toString());
		       	       Object rowDataOrdered[] = new Object[2];
		       	       for(int i = 0; i < listOrdered.size(); i++ ){
		       	    	rowDataOrdered[0] = listOrdered.get(i).getOrderGoodsID();
		       	    	rowDataOrdered[1] = listOrdered.get(i).getOrderedItemQuantity();

		       	           
		       	    	gIPanelBuyingTable.getModel().addRow(rowDataOrdered);
		       	           }; 
		       	           
       	        ArrayList<SoldGoods> listSold = getItemFromSellOrders(table.getModel().getValueAt(row, 0).toString());
	       	       Object rowDataSold[] = new Object[2];
	       	       for(int i = 0; i < listSold.size(); i++ ){
	       	    	rowDataSold[0] = listSold.get(i).getSellOrderGoodsID();
	       	    	rowDataSold[1] = listSold.get(i).getSoldItemQuantity();

	       	           
	       	    	gIPanelSellingTable.getModel().addRow(rowDataSold);
	       	           }; 
	       	           
       	        ArrayList<Integer> listQuantity = getAllQuantity(table.getModel().getValueAt(row, 0).toString());
	       	       Object rowDataQuantity[] = new Object[3];

	       	       rowDataQuantity[0] = listQuantity.get(0);
	       	       rowDataQuantity[1] = listQuantity.get(1);
	       	       rowDataQuantity[2] = listQuantity.get(2);

	       	           
	       	    		gIPanelQuantityTable.getModel().addRow(rowDataQuantity);
	       	            
	              
	           }
	        }
	     });
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
    public ArrayList<OrderedGoods> getItemFromOrderedGoods(String itemID){
    	String sql = "SELECT orderID, orderedQuantity FROM OrderedGoods WHERE orderedItemID = "+ itemID +"";
    	
    	ArrayList<OrderedGoods> orderedItem = null;
        loadCreateStatement();
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            orderedItem = new ArrayList<>();

        while (rs.next()){
        	OrderedGoods actualItem = new OrderedGoods(rs.getInt("orderID"), 0, "", rs.getInt("orderedQuantity"), 0);
        	orderedItem.add(actualItem);
            }

        
        } catch (SQLException ex) {
            System.out.println("Error with getItemFromOrderedGoods");
            System.out.println(" "+ex);
            }    
        
         return orderedItem;
}
    
    public ArrayList<SoldGoods> getItemFromSellOrders(String itemID){
    	String sql = "SELECT soldItemID, soldQuantity FROM SoldGoods WHERE soldItemID = "+ itemID +"";
    	
    	ArrayList<SoldGoods> soldItem = null;
        loadCreateStatement();
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            soldItem = new ArrayList<>();

        while (rs.next()){
        	SoldGoods actualItem = new SoldGoods(rs.getInt("soldItemID"), 0, "", rs.getInt("soldQuantity"), 0);
        	soldItem.add(actualItem);
            }

        
        } catch (SQLException ex) {
            System.out.println("Error with getItemFromSellOrders");
            System.out.println(" "+ex);
            }    
        
         return soldItem;
}
    public ArrayList<Integer> getAllQuantity(String itemID) {
    	
    	ArrayList<Integer> quantity = new ArrayList<Integer>();
        loadCreateStatement();
        String sql;
    	try {
    		sql = "SELECT SUM(soldQuantity) AS soldQuantitySUM FROM SoldGoods WHERE soldItemID = "+ itemID +"";
            
            ResultSet rs = createStatement.executeQuery(sql);

        while (rs.next()){
        	quantity.add(rs.getInt("soldQuantitySUM"));
            }
        
	        sql = "SELECT SUM(orderedQuantity) AS orderedQuantitySUM FROM OrderedGoods WHERE orderedItemID = "+ itemID +"";
	        
	        rs = createStatement.executeQuery(sql);

	    while (rs.next()){
	    	quantity.add(rs.getInt("orderedQuantitySUM"));
        }
	    
		    sql = "SELECT SUM(itemQuantity) AS quantityInWarehaus FROM Inventory WHERE itemID = "+ itemID +"";
	        
	        rs = createStatement.executeQuery(sql);
	
	    while (rs.next()){
	    	quantity.add(rs.getInt("quantityInWarehaus"));
    }
           
            
            
            
      } catch (SQLException ex) {
          System.out.println("Valami baj van az Item megváltoztatásakor");
          System.out.println(""+ex);
      }
    	
    	return quantity;
    }
    
    
}

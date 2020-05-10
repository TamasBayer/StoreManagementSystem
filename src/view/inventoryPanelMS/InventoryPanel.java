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
        
        table.newInformationFrameIfClicked(gIPanel);
        
        addItemButtonPressed();
        
        editButtonPressed();
        
        deleteButtonPressed();
        
        search();
        
        jTable = table.getTable();
        
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
                        "Do you really want to delete this Item?",
                        "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
            	if(action == JOptionPane.OK_OPTION && jTable.getSelectionModel().isSelectionEmpty() == false) {
            		
            		int row = jTable.getSelectedRow();
                    
                    
                    int idToInt = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
                    table.getModel().removeRow(row);
                    try {
                        String sql = "DELETE FROM Goods WHERE itemID = " + idToInt + "";
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);

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
    
    
}
